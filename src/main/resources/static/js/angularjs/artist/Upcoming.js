var app = angular.module('myApp', []);
var host = 'http://localhost:8080/api';
app.controller('upComingCtrl', function ($scope, $http) {
    $('#opt').change(function () {
        if ($('#opt').val() === "single") {
            $('#writter').removeAttr('hidden');
        } else {
            $('#writter').attr('hidden', 'hidden');
        }
    })

    $scope.upcoming = {};
    $scope.artist={};
    var writters = $('input[name="writter"]')

    //create
    $scope.createAlbum = function () {
        var url = host + "/v1/album";
        var data = new FormData();
        data.append('coverImg', $scope.coverImg);
        data.append('albumName', $scope.upcoming.name);
        data.append('releaseDate', new Date($scope.upcoming.releaseDate));
        $http.post(url, data, {
            headers: { 'Content-Type': undefined },
            transformRequest: angular.identity
        }).then(resp => {
            console.log('success')
        }).catch(error => {

        })
    }

    //create single song
    $scope.createSong = function () {
        var url = host + "/v1/song";
        var data = new FormData();
        data.append('coverImg', $scope.coverImg);
        data.append('songName', $scope.upcoming.name);
        data.append('releaseDay', new Date($scope.upcoming.releaseDate));
        $http.post(url, data, {
            headers: { 'Content-Type': undefined },
            transformRequest: angular.identity
        }).then(resp => {
            $scope.createWritter(resp.data,$scope.artist);
            if(writters.length >0){
                writters.each(function () {
                    $scope.findArist($(this).val());
                    $scope.createWritter(resp.data,$scope.artist);
                });
            }
        }).catch(error => {

        })
    }


    $scope.createWritter = function (songId, artistID) {
        var url = host + "/v1/writter";
        $scope.writter = {}
        $scope.writter.artistId = artistID;
        $scope.writter.songId = songId;
        var data = angular.copy($scope.writter);
        $http.post(url, data, {
        }).then(resp => {
            console.log('success')
        }).catch(error => {

        })
    }

    $scope.findArist=function(id){
        var url = host + "/v1/artist/"+id;
        $http.get(url).then(resp => {
            $scope.artist = resp.data;
        }).catch(error => {

        })
    }

    $('#create-song').click(function () {
        if ($('#opt').val() === "single") {
            $scope.createSong();
        } else {
            $scope.createAlbum();
        }
    });

    $scope.selectFile = function (id) {
        $('#' + id).click();
        $('#' + id).change(function (event) {
            var file = event.target.files[0];
            if (file) {
                $scope.$apply(function () {
                    $scope.coverImg = file;
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var imageDataUrl = e.target.result;
                        $('.' + id).attr('src', imageDataUrl);
                    };
                    reader.readAsDataURL(file);
                });
            }
        });
    };
})