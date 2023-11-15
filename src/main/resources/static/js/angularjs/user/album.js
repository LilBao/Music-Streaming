var host = "http://localhost:8080/api/";

app.controller('album', function ($scope, $http, $routeParams) {
    // show
    $scope.alDetail = [];
    $scope.alId = $routeParams.id;

    $scope.getPodcast = function (id) {
        $http.get(host + 'v1/search/al/' + id)
            .then(function (resp) {
                $scope.alDetail = resp.data.data;
                console.log($scope.alDetail);
            })
            .catch(function (error) {
                console.error("Error fetching podcast data:", error);
            });
    };
    $scope.getPodcast($scope.alId);
    console.log($scope.alId);

    // btn back and forward
  $("#back").on("click", function() {
    history.back();
  });
  
  $("#forward").on("click", function() {
    history.forward();
  });
});