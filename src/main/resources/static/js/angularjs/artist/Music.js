var app = angular.module('myApp', []);
var host = "http://localhost:8080/api";
app.controller('musicCtrl',function($scope,$http){
    $scope.listSongUpcoming=[];
    $scope.listAlbumUpcoming=[];
    $scope.record={};
    $scope.song={};
    //Get list song has not record
    $http.get(host+"/v1/song/up-coming").then(resp =>{
        $scope.listSongUpcoming=resp.data.data;
    }).catch(error = {

    })
    
    //Get list album has not track
    $http.get(host+"/v1/album/up-coming").then(resp =>{
        $scope.listAlbumUpcoming=resp.data.data;
    }).catch(error = {

    })

    //Pitch record
    $scope.pitch = function(type,id){
        if(type === "song"){
            
        }else{

        }
    }

    //Pitch record vào song
    $scope.pitchSong=function(idRecord,idSong){
        var url = host+"/v1/record";
        $scope.findRecord(idRecord);
        $scope.findSong(idSong);
        $scope.record.song = $scope.song
        var data = angular.copy($scope.record);
        $http.put(url,data).then(resp={
            
        }).catch(error => {

        })
    }

    //find record
    $scope.findRecord= function(id){
        var url = host+"/v1/record/"+id;
        $http.get(url).then(resp => {
            $scope.record= resp.data;
        }).catch(error =>{

        })
    }

    //find song
    $scope.findSong= function(id){
        var url = host+"/v1/song/"+id;
        $http.get(url).then(resp => {
            $scope.song= resp.data;
        }).catch(error =>{

        })
    }
})