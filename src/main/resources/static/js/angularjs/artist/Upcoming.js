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
    $scope.artist = {};
    $scope.listArtist = []
    //find List Artist
    $http.get(host + "/v1/artist-verified").then(resp => {
        $scope.listArtist = resp.data.data;
    })



    //create
    $scope.createAlbum = function () {
        var url = host + "/v1/album";
        var data = new FormData();
        data.append('coverImg', $scope.coverImg);
        data.append('albumName', $scope.upcoming.name);
        data.append('releaseDate', new Date($scope.upcoming.releaseDate));
        $http.post(url, data, {
            headers: { 
                'Content-Type': undefined,
                'Authorization': 'Bearer ' + getCookie('token')
            },
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
            headers: { 
                'Content-Type': undefined,
                'Authorization': 'Bearer ' + getCookie('token')
            },
            transformRequest: angular.identity
        }).then(resp => {
            var writters = $('input[name="writter"]:checked')
            var song = resp.data.data;
            //check xem có thêm người ft chung không
            if (writters.length > 0) {
                writters.each(function () {
                    let artistID = $(this).val();
                    let url = host + "/v1/artist/" + artistID;
                    //create ft chung vào database
                    $http.get(url).then(resp => {
                        $scope.artist = resp.data.data;
                        console.log();
                        $scope.createWritter(song, $scope.artist)
                    }).catch(error => {

                    })

                });
            }
            console.log('success')
        }).catch(error => {

        })
    }


    $scope.createWritter = function (song, artist) {
        var url = host + "/v1/writter";
        $scope.writter = {}
        $scope.writter.artist = artist;
        $scope.writter.song = song;
        var data = angular.copy($scope.writter);
        $http.post(url, data, {
        }).then(resp => {
            console.log('success')
        }).catch(error => {

        })
    }

    $scope.findArist = function (id) {
        var url = host + "/v1/artist/" + id;
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