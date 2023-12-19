var host = "http://localhost:8080/api/";

app.controller('SearchController', function ($scope, $http, $cookies, $window) {

  // search browser
  $scope.listPodcast = [];

  $scope.getListPodcast = function () {
    $http.get(host + 'v1/podcast')
      .then(function (resp) {
        $scope.listPodcast = resp.data.data;
      })
      .catch(function (error) {
        console.error("Error fetching list podcast data:", error);
      });
  };
  $scope.getListPodcast();
});