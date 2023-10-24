app.controller('homeCtrl',function($scope,$http){
    $scope.podcast = JSON.parse(localStorage.getItem('podcast'))
})