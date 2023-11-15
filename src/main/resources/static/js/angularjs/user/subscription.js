var host = "http://localhost:8080/api";
app.controller('subscriptionCtrl', function ($scope, $http, $route) {
    $scope.subscriptionId = $route.id;
    $scope.subscription = {};
    // paypal.Buttons({
    //     style: {
    //         disableMaxWidth: true,
    //         color:  'silver',
    //     },
    // }).render('#paypal-button-container');
    $scope.subscription = function () {
        var url = host + "";
        $http.get(url, {
            params: {}
        }).then(resp => {
            $scope.subscription = resp.data.data;
        })
    }

    $scope.paymentPayPal = function (total, subscriptionId) {
        let url = host + "/v1/payment-paypal";
        var data = new FormData();
        data.append('total', total);
        data.append('subscriptionId', Number(subscriptionId));
        $http.post(url, data, {
            headers: {
                'Content-Type': undefined
                , 'Authorization': 'Bearer ' + getCookie('token')
            },
            transformRequest: angular.identity
        }).then(resp => {
            window.location = resp.data.url
        }).catch(err => {

        })
    }

    $scope.paymentVnpay=function(){
        let url = host + "/v1/payment-vnpay";
        var data = new FormData();
        data.append('total', total);
        data.append('subscriptionId', Number(subscriptionId));
        $http.post(url, data, {
            headers: {
                'Content-Type': undefined
                , 'Authorization': 'Bearer ' + getCookie('token')
            },
            transformRequest: angular.identity
        }).then(resp => {
            window.location = resp.data.url
        }).catch(err => {

        })
    }

    $scope.paymentStripe=function(){
        let url = host + "/v1/payment-stripe";
        var data = {};
        data.subscriptionId=$scope.subscriptionId;
        data.prdStripeId=$scope.subscription.prdStripeId;
        data.price=$scope.subscription.price;
        $http.post(url, data, {
            headers: {
                'Authorization': 'Bearer ' + getCookie('token')
            },
        }).then(resp => {
            window.location = resp.data.url
        }).catch(err => {

        })
    }

    $('#btn-payment-paypal').click(function () {
        $scope.paymentPayPal($scope.subscription.price, $scope.subscriptionId);
    })

    $('#btn-payment-stripe').click(function(){
        $scope.paymentStripe();
    })
})