app.controller('SearchController', ['$scope', '$location', function ($scope, $location) {
    // Watch for changes in the input field
    $scope.$watch('searchInput', function (newVal) {
        // Check if the input value meets your criteria
        if (newVal && newVal.length > 0) {
            // If the input is not empty, navigate to the desired route (search-detail)
            $location.path('/search-detail');
        }
    });

}]);