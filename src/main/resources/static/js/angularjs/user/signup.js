var app = angular.module('myApp', []);
var host = "http://localhost:8080/api";

app.controller('signupCtrl', function($scope, $http) {
    $scope.signupRequest = {};
    
    $scope.successMessage = '';
    $scope.errorMessage = '';

    $scope.signup = function(){
        var url = host + "/v1/accounts/signUp";

        $http.post(url, $scope.signupRequest)
        .then(function (response) {
            // Đăng ký thành công, cập nhật biến successMessage
            $scope.successMessage = "Signup successful. Welcome, " + $scope.sigupRequest.username + "!";
            $scope.errorMessage = null; // Đảm bảo biến errorMessage được xóa đi (nếu có)
        })
        .catch(function (error) {
            // Xử lý lỗi ở đây và cập nhật biến errorMessage
            $scope.errorMessage = 'Signup successful. Welcome' 
            $scope.successMessage = null; // Đảm bảo biến successMessage được xóa đi (nếu có)
        });
    }
});
