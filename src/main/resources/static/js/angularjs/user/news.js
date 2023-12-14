var apiNews = "http://localhost:8080/api"
var app = angular.module("myApp", ["ngCookies"]);
app.controller('newsController', function ($scope, $cookies, $http) {

    $scope.data = [];
    $scope.forRole = {};

    $scope.dataNews = function (role) {

        $http.get(apiNews + "/v1/news", { params: { createfor: role } }).then(resp => {
            $scope.data = resp.data.data;
            $scope.forRole = role;
            console.log($scope.data); 
        }).catch(error => {
            console.log(error);
        })
    }
    $scope.dataNews("USER");

    $scope.getAccess = function() {
        if ($scope.forRole === 'USER') {
          
        }
    }

});