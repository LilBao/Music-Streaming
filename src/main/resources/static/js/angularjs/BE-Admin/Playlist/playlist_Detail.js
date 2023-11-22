var apiPlaylist = "http://localhost:8080/api/v1/admin/playlist";
var cookieName = "token";

app.filter("sortField",function(input){
    $scope.field = $('nameSong').val();
})


app.controller("playlistDetailController", function (sortService,graphqlService, $scope, $http, $cookies, $routeParams) {

    $scope.arraySort = ["Song Name","Duration"];

    var idPlaylist = $routeParams.id;
    var listPlaylist = {};

    $scope.field = "#";
    $scope.setSortField = function(idSortField){
    
        $scope.field = idSortField;
    }

    $scope.findByPlaylistId = function (idPlaylist) {

        const queryPlaylist = `{
            getPlayListById(idPlaylist: ${idPlaylist}) {
                playlistId
                playlistName
                isPublic
                description
                image {
                  url
                }
                playlistRecords {
                  playlistRecordingId
                  recording {
                    recordingId
                    recordingName
                    audioFileUrl
                    duration
                    song {
                      songName
                      image {
                        url
                      }
                      writters{
                        artist{
                          artistName
                        }
                      }
                    }
                  }
                }
              }
            }`;
        graphqlService.executeQuery(queryPlaylist)
            .then(data => {

                $scope.listPlaylist = data.getPlayListById;
                $scope.totalDuration =0;
                    $scope.listPlaylist.playlistRecords.forEach(element => {
                        $scope.totalDuration += element.recording.duration;
                    });

                 
                
            })
            .catch(error => {
                console.log(error);

            });
    }

    $scope.removeRecordFromPlaylist = function (id) {

        var config = {
            headers: {
                'Authorization': 'Bearer ' + $cookies.get(cookieName),

            }
        }

        $http.delete(apiPlaylist + `/${id}/detail`, config).then(resp => {

            $scope.findByPlaylistId(idPlaylist);
            showStickyNotification("Remove successful", "success", 2000);

        }).catch(function (error) {
            showStickyNotification("Fail remove ", "danger", 2000);
            console.error("Error", error);
        });
    }

    $scope.updatePlaylist = function (id) {
        var config = {
            headers: {
                'Authorization': 'Bearer ' + $cookies.get(cookieName),
                'Content-Type': undefined
            },
            transformRequest: angular.identity
        };


        let fileInput = document.getElementById('imagePlaylist-edit');
        let file = fileInput.files[0];



        var formData = new FormData();
        formData.append("playlistName", $scope.listPlaylist.playlistName);
        formData.append("playlistDescription", $scope.listPlaylist.description);
        formData.append("imageFile", file);




        $http.put(apiPlaylist + `/${id}`, formData, config).then(resp => {
            showStickyNotification(" successful", "success", 2000);

            $scope.findByPlaylistId(idPlaylist);
        }).catch(function (error) {
            showStickyNotification("Fail  ", "danger", 2000);
            console.error("Error", error);
        });
    }


    $scope.setIsPublicPlaylist = function (setPublic) {
        var config = {
            headers: {
                'Authorization': 'Bearer ' + $cookies.get(cookieName),
            }

        };

        $http.put(apiPlaylist + `/${idPlaylist}/ispublic`, config, {
            params: {
                "isPublic": setPublic
            }
        }).then(resp => {
            $scope.findByPlaylistId(idPlaylist);
            showStickyNotification("Successful", "success", 2000);
        }).catch(function (error) {
            showStickyNotification("Fail", "danger", 2000);
            console.error("Error", error);
        });
    };



    $scope.findByPlaylistId(idPlaylist);

    // Change Image
    $("#imagePlaylist-edit").change(function () {
        imagePreview(this);
    });

    function imagePreview(input) {
        //Check if there is a file that has been selected
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#image-preview').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }


    $scope.runMusic = function (audioFileUrl) {
        console.log(audioFileUrl);
        $("#music").attr("src",audioFileUrl);
        $("#music")[0].load();
        $("#music")[0].play();
    }


})

