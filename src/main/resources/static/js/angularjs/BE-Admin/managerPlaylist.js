var apiPlaylist = "http://localhost:8080/api/v1/admin/playlist";
var cookieName = "token";

app.controller("managerPlaylistController", function (graphqlService, $scope, $http, $cookies) {


    $scope.form = {};
    $scope.itemMood = [];
    $scope.itemInstrument = [];
    $scope.itemSongStyle = [];
    $scope.itemCulture = [];
    $scope.itemGenre = [];
    $scope.itemTag = [];
    $scope.listRecordRandom = [];
    $scope.listEpisodeRandom =[];
    $scope.allPlaylist = [];
    $scope.allPlaylistForPodcast = [];
    $scope.resetListRecordRandom = () => {
        $scope.listRecordRandom = {};
    }

    $scope.getAllPlaylist = function () {
        const query = `{
            getAllPlaylist {
              playlistId
              playlistName
              description
              image {
                url
              }
            }
          }`;
        graphqlService.executeQuery(query)
            .then(data => {

                $scope.allPlaylist = data.getAllPlaylist;

            })
            .catch(error => {
                console.log(error);

            });
    }

    $scope.getAllPlaylistPodcast = function () {
        const query = `{
            getAllPodcastPlaylist {
              playlistId
              playlistName
              description
              image {
                url
              }
            }
          }`;
        graphqlService.executeQuery(query)
            .then(data => {

                $scope.allPlaylistForPodcast = data.getAllPodcastPlaylist;

            })
            .catch(error => {
                console.log(error);

            });
    }


    $scope.getAllTags = function () {
        const query = `{
            getAllTag {
              tagId
              tagName
            }
          }`;
        graphqlService.executeQuery(query)
            .then(data => {

                $scope.itemTag = data.getAllTag;

            })
            .catch(error => {
                console.log(error);

            });
    }


    $scope.getAllMoods = function () {
        const query = `{
                getAllMood {
                    moodid
                    moodname
                 }
                }`;
        graphqlService.executeQuery(query)
            .then(data => {

                $scope.itemMood = data.getAllMood;

            })
            .catch(error => {
                console.log(error);

            });
    }


    $scope.getAllInstruments = function () {
        const query = `{
            getAllInstrument {
              instrumentId
              instrumentName
            }
          }`;
        graphqlService.executeQuery(query)
            .then(data => {

                $scope.itemInstrument = data.getAllInstrument;

            })
            .catch(error => {
                console.log(error);

            });
    }

    $scope.getAllSongStyles = function () {
        const query = `{
            getAllSongStyle {
              songStyleId
              songStyleName
            }
          }`;
        graphqlService.executeQuery(query)
            .then(data => {

                $scope.itemSongStyle = data.getAllSongStyle;

            })
            .catch(error => {
                console.log(error);

            });
    }

    $scope.getAllCultures = function () {
        const query = `{
            getAllCulture {
              cultureId
              cultureName
            }
          }`;
        graphqlService.executeQuery(query)
            .then(data => {

                $scope.itemCulture = data.getAllCulture;

            })
            .catch(error => {
                console.log(error);

            });
    }

    $scope.getAllGeners = function () {
        const query = `{
            getAllGener {
              id
              nameGenre
            }
          }`;
        graphqlService.executeQuery(query)
            .then(data => {

                $scope.itemGenre = data.getAllGener;

            })
            .catch(error => {
                console.log(error);

            });
    }

    $scope.showCreatePlaylist = false;

    $scope.getEpisodeRandom = () => {

        var tag = "";
       

        $('input[name="tag"]:checked').each(function () {
            tag = $(this).val()
        });

  

        var valueGet = {

            tag: tag,
         
        };

        $http.get(apiPlaylist + "/episode-random", { params: valueGet }).then(resp => {

            $scope.listEpisodeRandom = resp.data.data;

            $scope.showCreatePlaylist = true;

        }).catch(error => {
            console.log(error)

        })
    }


     $scope.getRecordRandom = () => {

        var mood = "";
        var instrument = "";
        var songStyle = "";
        var culture = "";
        var gener = "";

        $('input[name="mood"]:checked').each(function () {
            mood += "'" + $(this).val() + "',";
        });

        $('input[name="instrument"]:checked').each(function () {
            instrument += $(this).val() + " ";
        });

        $('input[name="songStyle"]:checked').each(function () {
            songStyle += $(this).val() + " ";
        });

        $('input[name="culture"]:checked').each(function () {
            culture += $(this).val() + " ";
        });

        $('input[name="gener"]:checked').each(function () {
            gener += $(this).val() + " ";
        });


        var valueGet = {

            mood: mood,
            instrument: instrument,
            songstyle: songStyle,
            culture: culture,
            nameGenre: gener
        };

        $http.get(apiPlaylist + "/record-random", { params: valueGet }).then(resp => {

            $scope.listRecordRandom = resp.data.data;

            $scope.showCreatePlaylist = true;

        }).catch(error => {
            console.log(error)

        })
    }



    $scope.create = function () {
        var records = angular.copy($scope.listRecordRandom);
        var fileInput = document.getElementById('pic');
        var file = fileInput.files[0];
        var name = $scope.playlistName;

        var formData = new FormData();
        formData.append("file", file);
        formData.append("playlistName", name);
        formData.append("description", $scope.playlistDescription);
        formData.append("listRecord", JSON.stringify(records));


        console.log("form data: " + formData.getAll("playlistName"))


 
        var config = {
            headers: {
                'Authorization': 'Bearer ' + $cookies.get(cookieName),
                'Content-Type': undefined // Let AngularJS set the content type
            },
        };

        $http.post(apiPlaylist, formData, config).then(resp => {
              $scope.resetListRecordRandom();
            showStickyNotification("success", "success", 2000)

        }).catch(error => {
            console.log("Error", error)
        });
    }


    $scope.removeItem = function (item) {

        var index = $scope.listRecordRandom.findIndex(record => record.recordingId == item);
        $scope.listRecordRandom.splice(index, 1);
    }




    $scope.getAllPlaylist();
    $scope.getAllPlaylistPodcast();
    $scope.getAllMoods();
    $scope.getAllInstruments();
    $scope.getAllSongStyles();
    $scope.getAllCultures();
    $scope.getAllGeners();
    $scope.getAllTags();
})

