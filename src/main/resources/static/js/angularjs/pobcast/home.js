var host = "http://localhost:8080/api";
app.controller('homeCtrl',function($scope,$http){
    $scope.podcast = JSON.parse(localStorage.getItem('podcast'))
    $scope.episodeLatest = {};

    $scope.findEpisodeLatest = function(){
        let url = host+"/v1/latest-episode-podcast/"+$scope.podcast.podcastId;
        $http.get(url).then(resp => {
            $scope.episodeLatest= resp.data.data;
        }).catch(error =>{

        })
    }
    $scope.findEpisodeLatest();    

})