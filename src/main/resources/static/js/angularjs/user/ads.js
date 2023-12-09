var app = angular.module('myApp', []);
var host = "http://localhost:8080/api";
app.controller('ads', function ($scope, $http) {
    $scope.req = {};

    var url = host + "v1/ads-file";
    var data = angular.copy($scope.req);
    $scope.startAdvertising = function (packageType) {
        switch (packageType) {
            case 'Bronze':
                console.log('Selected Bronze package');
                $scope.hiden = true;
                break;
            case 'Silver':
                console.log('Selected Silver package');
                $scope.hiden = true;
                break;
            case 'Gold':
                console.log('Selected Gold package');
                $scope.hiden = true;
                break;
            case 'Platinum':
                console.log('Selected Platinum package');
                $scope.hiden = false;
                break;
            case 'Diamond':
                console.log('Selected Diamond package');
                $scope.hiden = false;
                break;
            case 'Ruby':
                console.log('Selected Ruby package');
                $scope.hiden = false;
                break;
            default:
                console.log('Unknown package type');
        }
    };
    $http.post(url, data).then(function (resp) {
        // setCookie("token", resp.data.data.accessToken,30);
        // showStickyNotification('success', 'success', 3000);
      }).catch(function (error) {
        // showStickyNotification('fail', 'danger', 3000);
        console.log(error)
      });
})
