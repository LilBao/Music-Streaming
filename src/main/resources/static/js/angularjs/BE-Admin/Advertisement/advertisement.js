var cookieName = "token";
var api = "http://localhost:8080/api/v1/admin/advertisement";

app.controller( "advertisementController", function (graphqlService, $scope, $http) {
 
 
    $scope.allAdvertisementRunAndComplete = [];
    $scope.allAdvertisementPendingAndReject = [];
    $scope.statisticsON = {};
    $scope.getAllAdvertisementRunningAndCompleted = function () {
      $http
        .get(api + "/running-completed")
        .then((resp) => {
          $scope.allAdvertisementRunAndComplete = resp.data.data;
        })
        .catch((error) => {
          console.log("Error", error);
        });
    };

    $scope.getAllAdvertisementPendingAndReject = function () {

      $http.get(api+"/pending-reject").then(resp => {

          $scope.allAdvertisementPendingAndReject = resp.data.data;

      }).catch(error => {
          console.log("Error",error);
      })
    };

    $scope.statisticsDetail = function (id) {
       $http.get(api+`/statistics/${id}`).then(resp => {
        $scope.statisticsON = resp.data.data;
       })
    };

    $scope.getAllAdvertisementRunningAndCompleted();
    $scope.getAllAdvertisementPendingAndReject();
  }
);
