var app = angular.module('myApp', []);
var host = "http://localhost:8080/api";

app.controller('forgotPasswordCtrl', function ($scope, $http) {
    $scope.email = '';
    $scope.successMessage = '';
    $scope.errorMessage = '';

    $scope.sendResetPasswordEmail = function () {
        var url = host + "/v1/accounts/forgotpassword";
        var email = $scope.email;
        // Kiểm tra nếu email không được nhập
        if (!email) {
            $scope.errorMessage = "Please enter your email.";
            return;
        }

        // Gửi yêu cầu đặt lại mật khẩu dựa trên email
        $http.post(url, { email: email })
            .then(function (response) {
                $scope.successMessage = response.data.message;
                $scope.errorMessage = null; // Đảm bảo biến errorMessage được xóa đi (nếu có)
            })
            .catch(function (error) {
                $scope.errorMessage = "Failed to send email.";
                $scope.successMessage = null; // Đảm bảo biến successMessage được xóa đi (nếu có)
            });
    };
});
