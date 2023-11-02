app.controller('queueCtrl', function ($scope, $http,queueService) {
    $scope.queue = queueService.getQueue();
    $scope.listTest = [];
    $scope.enQueue = function(item){
        queueService.enQueue(item);
    }

    $scope.testData = function(){
        $http.get('http://localhost:8080/api/v1/record').then(resp => {
            $scope.listTest= resp.data.data;
        })
    }
    $scope.testData();
    
})