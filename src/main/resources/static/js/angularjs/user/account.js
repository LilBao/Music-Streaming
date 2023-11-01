var app = angular.module('myApp', ['ngCookies']);
app.controller('accountCtrl', function($scope, $cookies) {
    // Kiểm tra xem cookie có tồn tại hay không
    if ($cookies.get('userInfo')) {
      // Lấy thông tin từ cookie
      var userInfo = JSON.parse($cookies.get('userInfo'));
  
      // Gán thông tin từ cookie vào biến $scope để hiển thị trên giao diện
      $scope.username = userInfo.username;
      $scope.email = userInfo.email;
      // Các thông tin khác tương tự
  
      // Các tác vụ khác bạn muốn thực hiện với thông tin tài khoản
    } else {
      // Xử lý trường hợp cookie không tồn tại hoặc không có thông tin tài khoản
      console.log('Không có thông tin tài khoản trong cookie.');
    }
  });