var apiArtist = "http://localhost:8080/api/v1/admin/artist";
var apiRole = "http://localhost:8080/api/v1/admin/role";
var cookieName = "token";





app.controller("profileAccountController", function (graphqlService, $scope, $http, $routeParams) {


    $scope.form = {};
    $scope.album = [];
    $scope.song =[];
    var idArtist = $routeParams.id;

    $scope.edit = function (idArtist) {

        let url = apiArtist + "/" + idArtist;
        $http.get(url).then(resp => {
            $scope.form = resp.data.data;
            console.log( $scope.form)
        }).catch(error => {
            console.log("Error", error)
        });
    }



    const queryAlbum = `  {
        findOneArtist(emailArtist: "${idArtist}") {
          albums{
            albumName
            image{
              url
            }
          }
        }
      }`;

    const querySong = `{
        findOneArtist(emailArtist:  "${idArtist}") {
             writters{
            song{
              image{
                url
              }
              songName
              recordings{
                duration
                tracks{
                  album{
                    albumName
                  }
                }
              }
            }
          }
        }
      }`;



    graphqlService.executeQuery(queryAlbum)
        .then(data => {

            $scope.album = data.findOneArtist.albums;
           
        })
        .catch(error => {
            console.log(error);
        });
    $scope.edit(idArtist);

    graphqlService.executeQuery(querySong)
        .then(data => {

            $scope.song = data.findOneArtist.writters;
          
        })
        .catch(error => {
            console.log(error);
        });

   
});

