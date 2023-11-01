app.controller('queueCtrl', function ($scope, $http,queueService) {
    $scope.queue = queueService.getQueue();
    
    $scope.enQueue = function(item){
        queueService.enQueue(item);
    }
})