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

    $scope.swtitchPodcast = function(id){
        let url = host + "/v1/podcast/"+id;
        $http.get(url).then(resp => {
            localStorage.setItem('podcast',JSON.parse(resp.data.data));
            window.location.href = "./PodcastControl.html#!/home" 
        }).catch(error =>{
            console.log(error);
        })
    }
})