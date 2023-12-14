var host = "http://localhost:8080/api/";

app.controller('album', function ($scope, $http, $routeParams, graphqlService) {
  // show
  $scope.alDetail = [];
  $scope.listSong = [];
  $scope.alId = $routeParams.id;

  $scope.getAlb = function () {
    let query = `{
        findAlbum(id : ` + $scope.alId + `) {
          albumId
          albumName
          releaseDate
          description
          artist{
            artistId
            artistName
          }
          tracks{
            recording{
              recordingId
              recordingName
              duration
              audioFileUrl
              song{
                songId
                songName
                writters{
                  artist{
                    artistId
                    artistName
                  }
                }
                image{
                  url
                }
              } 
            }
          }
          image{
            url
          }
        }
        }`
    graphqlService.executeQuery(query).then(data => {
      $scope.alDetail = data.findAlbum;
      $scope.listAlb = $scope.alDetail.albums;
      if ($scope.listAlb) {
        $scope.listAlb.forEach(item => {
          item.tracks.forEach(t2 => {
            $scope.listSong.push(t2.recording);
          })
        });
      }
    })
  };
  $scope.getAlb();

  console.log($scope.alId);

  $('#btn-playlist-play').click(function () {
    $('#btn-playlist-pause').attr('hidden', false);
    $('#btn-playlist-play').attr('hidden', true);
      $scope.selectAudio($scope.listSong, 'song', $scope.listSong, 0);
    play.click();
  })

  //pause
  $('#btn-playlist-pause').click(function () {
    $('#btn-playlist-pause').attr('hidden', true);
    $('#btn-playlist-play').attr('hidden', false);
    resume.click();
  })

  // btn back and forward
  $("#back").on("click", function () {
    history.back();
  });

  $("#forward").on("click", function () {
    history.forward();
  });
});