var host = "http://localhost:8080/api/";
var app = angular.module('myApp', []);
app.controller('profileCtrl', function ($scope, $http) {
    $scope.profile = {}
    $scope.author ={};
    $http.get(host + "v1/account/jvke@gmail.com").then(resp => {
        $scope.profile = resp.data.data;
    })
    $scope.follow = function () {
        var url = host + "v1/follow";
        var data = angular.copy($scope.author);
        data.email=$scope.profile.email;
        data.type=1;
        $http.post(url,data,{
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            console.log(resp.data.data);
        }).catch(err => {
            // Xử lý lỗi ở đây
        });
    }

    $scope.unfollow = function () {
        let url = host + "v1/follow?email="+$scope.profile.email+"&type=1";
        $http.delete(url,{
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            console.log("success")
        }).catch(err => {
            console.log(err)
        })
    }

    $('#btn-follow').click(function () {
        if ($('#btn-follow').hasClass('unfollow')) {
            $('#btn-follow').removeClass('unfollow');
            $('#btn-follow').text("Follow")
        } else {
            $('#btn-follow').addClass('unfollow');
            $('#btn-follow').text("Following")
            $scope.follow();
        }
    })
})