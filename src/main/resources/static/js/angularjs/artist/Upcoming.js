app.controller('upComingCtrl', function ($scope, $http) {
    $('#opt').change(function () {
        if ($('#opt').val() === "single") {
            $('#writter').removeAttr('hidden');
            $('#label-collab').removeAttr('hidden');
        } else {
            $('#writter').attr('hidden', 'hidden');
            $('#label-collab').attr('hidden', 'hidden');
        }
    })

    $scope.upcoming = {};
    $scope.artist = {};
    $scope.listArtist = [];

    //artist
    $http.get(host + "/v1/profile", {
        headers: { 'Authorization': 'Bearer ' + getCookie('token') }
    }).then(resp => {
        $scope.artist = resp.data.data;
    }).catch(error => {
        console.log("Not found artist profile")
    })

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
        data.append('artistId', $scope.artist.artistId);
        $http.post(url, data, {
            headers: { 
                'Content-Type': undefined,
                'Authorization': 'Bearer ' + getCookie('token')
            },
            transformRequest: angular.identity
        }).then(resp => {
            $scope.upcoming = {};
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
            $scope.upcoming = {};
            //check xem có thêm người ft chung không
            if (writters.length > 0) {
                writters.each(function () {
                    let artistID = $(this).val();
                    let url = host + "/v1/artist/" + artistID;
                    //create ft chung vào database
                    $http.get(url).then(resp => {
                        $scope.artist = resp.data.data;
                        $scope.createWritter(song, $scope.artist)
                    }).catch(error => {

                    })

                });
            }
            showStickyNotification('Create upcoming success', 'success', 3000);
        }).catch(error => {
            showStickyNotification('Create upcoming fail', 'danger', 3000);
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