var host = "http://localhost:8080/api/";

app.controller('ShowPodcast', function ($scope, $http, $routeParams) {
    // show
    $scope.podcastDetail = [];
    $scope.podcastId = $routeParams.id;

    $scope.getPodcast = function (id) {
        $http.get(host + 'v1/podcast/' + id)
            .then(function (resp) {
                $scope.podcastDetail = resp.data.data;
                console.log($scope.podcastDetail);
            })
            .catch(function (error) {
                console.error("Error fetching podcast data:", error);
            });
    };
    $scope.getPodcast($scope.podcastId);
    console.log($scope.podcastId);

    $scope.episodeAll = {};
    // show episodes
    $scope.getAllEpisode = function () {
        $http.get(host + 'v1/podcast-episode/' + $scope.podcastId)
            .then(function (resp) {
                $scope.episodeAll = resp.data.data;
                console.log($scope.episodeAll);
            })
            .catch(function (error) {
                console.error("Error fetching podcast data:", error);
            });
    };
    $scope.getAllEpisode();

    // btn back and forward
  $("#back").on("click", function() {
    history.back();
  });
  
  $("#forward").on("click", function() {
    history.forward();
  });
});