var app = angular.module('myApp',[]);
var host = "http://localhost:8080/api";
app.controller('loginCtrl',function($scope,$http){    
    $scope.loginRequest={};
    $('#login').click(function(){
        var url = host+"/v1/users/login";
        var data = angular.copy($scope.loginRequest);
        $http.post(url,data).then(resp=>{
            setCookie("token",resp.data.data.accessToken);
        })
    })
})