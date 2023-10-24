var app = angular.module('myApp',[]);
var host = "http://localhost:8080/api";
app.controller('navbarController',function($scope,$http){
    $scope.listPodcast = [];
    $scope.findMyListPodcast = function(){
        let url = host + "/v1/my-podcast";
        $http.get(url,{
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            $scope.listPodcast = resp.data.data;
        }).catch(error =>{
            console.log(error);
        })
    }

    $scope.swtitchPodcast = function(){
        let url = host + "/v1/my-podcast";
        $http.get(url,{
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            $scope.listPodcast = resp.data.data;
        }).catch(error =>{
            console.log(error);
        })
    }
})