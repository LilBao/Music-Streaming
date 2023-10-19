var apiNew = "http://localhost:8080/api/v1/admin/new";
var cookieName = "token";



app.controller("managerBlogController", function ($scope, $http, $cookies, $log, $timeout) {

    $scope.items = [];
    $scope.form = {};
    $scope.success = false;


    $scope.reset = function () {
        $scope.form = {};
        $scope.key = null;
    }



 
  
})

