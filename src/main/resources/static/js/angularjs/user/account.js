var app = angular.module('myApp',[]);
var host = "http://localhost:8080/api";
app.controller('accountCtrl',function($scope,$http){   
    $scope.email = ""; // Khai báo một biến để lưu email

    $scope.AccountByEmail = function () {
        var email = $scope.email;

        // Gửi yêu cầu GET đến API Spring Boot
        $http.get(host + '/' + email)
            .then(function (response) {
                // Xử lý dữ liệu trả về từ API ở đây
                $scope.account = response.data;
                console.log("Dữ liệu nhận được:", $scope.account);
                // Hiển thị thông tin tài khoản hoặc thông báo lỗi
            })
            .catch(function (error) {
                // Xử lý khi có lỗi trong quá trình gửi yêu cầu
                console.log("Có lỗi xảy ra trong quá trình gửi yêu cầu.");
                // Hiển thị thông báo lỗi cho người dùng
            });
    };
 });