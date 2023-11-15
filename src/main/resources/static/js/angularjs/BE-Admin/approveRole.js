
var cookieName = "token";

app.controller("approveRolesController", function (graphqlService, $scope, $http) {


    $scope.form = {};
    $scope.item = [];



    $scope.getAllApproveRoles = function () {
        const query = `{
            getAllIsVerifyArtist {
              artistId
              artistName
              dateOfBirth
              fullName
              placeOfBirth
              bio
              active
              isVerify
              dateStarted
              account {
                username
                email
                image{
                  url
                }
                userType{
                    nameType
                  }
              }   
            }
          }`;
        graphqlService.executeQuery(query)
            .then(data => {
                $scope.item = data.getAllIsVerifyArtist;
                console.log($scope.item)
            })
            .catch(error => {
                console.log(error);

            });
    }

    $scope.getAllApproveRoles();

})

