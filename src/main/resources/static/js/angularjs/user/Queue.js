app.controller('queueCtrl', function ($scope, $http,queueService,audioService) {
    $scope.queue = queueService.getQueue();
    $scope.listPlay =[... audioService.getListPlay()];
    $scope.currentIndex = audioService.getCurrentAudio();
    $scope.clearQueue = function(){
        queueService.clearQueue()
    }
    
    $scope.removeFromQueue = function(index){
        queueService.getQueue().splice(index, 1);
    }
})