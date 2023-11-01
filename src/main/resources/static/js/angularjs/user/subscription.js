var app = angular.module('myApp', []);
var host = "http://localhost:8080/api";

app.controller('subscriptionCtrl', function ($scope, $http) {
    // paypal.Buttons({
    //     style: {
    //         disableMaxWidth: true,
    //         color:  'silver',
    //     },
    // }).render('#paypal-button-container');
   

    $scope.paymentPayPal = function (value) {
        let url = host + "/v1/payment-paypal?total=" + value;
        $http.post(url).then(resp => {
            window.location = resp.data.url
        }).catch(err => {

        })
    }

    $('#btn-payment-paypal').click(function () {
        $scope.paymentPayPal(100);
    })
})