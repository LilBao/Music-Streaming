var app = angular.module('myApp',[]);
var host = 'http://localhost:8080/api';
app.controller('myCtrl',function($scope,$http){

    $scope.album={};
    $scope.song={};

    $scope.createAlbum= function(){
        var url = host + "/v1/album";
        var data = new FormData();
        data.append('coverImg',$scope.coverImg);
        data.append('coverImg',$scope.album.albumName);
        data.append('coverImg',$scope.album.releaseDate);
        $http.post(host,item,{
            headers: { 'Content-Type': undefined }, 
            transformRequest: angular.identity
        }).then(resp => {

        }).catch(error => {

        })
    }

    $scope.createSong= function(){
        var url = host + "/v1/song";
        var data = new FormData();
        data.append('coverImg',$scope.songName);
        data.append('coverImg',$scope.album.albumName);
        data.append('coverImg',$scope.album.releaseDate);
        $http.post(host,item,{
            headers: { 'Content-Type': undefined }, 
            transformRequest: angular.identity
        }).then(resp => {

        }).catch(error => {

        })
    }


})