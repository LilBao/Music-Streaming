var host = "http://localhost:8080/api/";

app.controller('discography', function ($scope, $http, $routeParams, graphqlService) {
  // show
  $scope.discography = [];
  $scope.listSong = [];
  $scope.artistId = $routeParams.id;

  $scope.getdiscography = function () {
    let query = `{
      findAlByArtist(idArist:`+ $scope.artistId + `){
        artistId
         artistName
        fullName
        albums{
          albumId
          albumName
          releaseDate
          description
          artist{
            artistId
            artistName
            account{
              author{
                role{
                  role
                }
              }
            }
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
      }
    }`
    graphqlService.executeQuery(query).then(data => {
      $scope.discography = data.findAlByArtist;
      
      $scope.listAlb = $scope.discography.albums;
      if ($scope.listAlb) {
        $scope.listAlb.forEach(item => {
          item.tracks.forEach(t2 => {
            $scope.listSong.push(t2.recording); 
          })
        });
      }
    })
  };
  $scope.getdiscography();

  // btn back and forward
  $("#back").on("click", function () {
    history.back();
  });

  $("#forward").on("click", function () {
    history.forward();
  });
});