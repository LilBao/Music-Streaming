var apiArtist = "http://localhost:8080/api/v1/admin/artist";
var apiRole = "http://localhost:8080/api/v1/admin/role";
var cookieName = "token";





app.controller("profileAccountController", function ($scope, $http, $cookies, $routeParams) {


    $scope.form = {};


    var idArtist = $routeParams.id;

    $scope.edit = function (idArtist) {
        let url = apiArtist + "/" + idArtist;
        $http.get(url).then(resp => {
            $scope.form = resp.data.data;
            console.log($scope.form)
        }).catch(error => {
            console.log("Error", error)
        });
    }

})

