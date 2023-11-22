var host = "http://localhost:8080/api/";

app.controller('episode', function ($scope, $http, $routeParams) {
    // show
    $scope.episodeDetail = [];
    $scope.episodeId = $routeParams.id;

    $scope.getPodcast = function (id) {
        $http.get(host + 'v1/episode/' + id)
            .then(function (resp) {
                $scope.episodeDetail = resp.data.data;
                console.log($scope.episodeDetail);
            })
            .catch(function (error) {
                console.error("Error data:", error);
            });
    };
    $scope.getPodcast($scope.episodeId);
    console.log($scope.episodeId);

    // btn back and forward
  $("#back").on("click", function() {
    history.back();
  });
  
  $("#forward").on("click", function() {
    history.forward();
  });
});