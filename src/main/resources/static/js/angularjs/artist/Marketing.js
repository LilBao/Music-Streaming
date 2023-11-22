var host = "http://localhost:8080/api";
app.controller('homeArtistCtrl', function ($scope, $http,graphqlService,$filter) {
    $scope.artist = {};
    $scope.image = {};
    $scope.listSong = {};
    $scope.listAlbum = {};
    $scope.me = function () {
        $http.get(host + "/v1/profile", {
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            $scope.artist = resp.data.data;
            $scope.getListSongReleased($scope.artist.account.email);
            $scope.getListAlbumReleased();
            $scope.findListSongUpcoming();
            $scope.countFollow();
            $scope.getListNews();
        }).catch(error => {
            console.log("Not found artist profile")
        })
    }
    $scope.me();
    //Top 5 Record listened
    $scope.getListSongReleased = function (email) {
        const query = `{
            getListSongReleased(email: "`+ String(email) + `") {
                recordingName
                duration
                songStyle
                listened
                mood
                culture
                instrument
                versions
                studio
                idMv
                produce
                recordingdate
                songGenres{
                    genre {
                      nameGenre
                    }
                }
                song{
                  image{
                    url
                  }
                  writters{
                    artist{
                      artistName
                    }
                  }
                }
            }
        }`
        graphqlService.executeQuery(query).then(data => {
            $scope.listSong = data.getListSongReleased.slice().sort(function(a, b){
                return b.listened - a.listened;
            });
            $scope.listSong.forEach(element => {
                $scope.listened += element.listened;
            });
        })
    }

    //Top My album listened
    $scope.getListAlbumReleased = function () {
        $http.get(host + "/v1/album-artist-released", {
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            $scope.listAlbum = resp.data.data;
        }).catch(error => {
            console.log(error);
        })
    }

    $scope.selectFile = function (id) {
        $('#' + id).click();
        $('#' + id).change(function (event) {
            var file = event.target.files[0];
            if (file) {
                $scope.$apply(function () {
                    if (id === 'avatar') {
                        $scope.avatarFile = file;
                    }
                    if (id === 'background') {
                        $scope.backgroundFile = file;
                    }

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