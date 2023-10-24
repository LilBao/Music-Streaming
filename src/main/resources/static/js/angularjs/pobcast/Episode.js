var host = "http://localhost:8080/api";
app.controller('episodeInforCtrl', function ($scope, $http,FileService) {
    $scope.podcast = JSON.parse(localStorage.getItem('podcast'));
    var fileAudio = FileService.getFile();
    if(fileAudio ===null){
        window.location.href = "/templates/Postcaster/NavPodcast.html#!/new-episode" 
    }
    $('#audioFile').attr('src', URL.createObjectURL(fileAudio)) ;
    
    $scope.createEpisode = function(){
        let url = host + "/v1/episode";
        var data = new FormData();
        data.append('coverImg',$scope.coverImg);
        data.append('fileAudio',fileAudio);
        data.append('episodeTitle',$scope.episode.episodeTitle);
        data.append('description',$scope.episode.description);
        data.append('publishDate',$scope.episode.publishDate);
        data.append('sessionNumber',$scope.episode.sessionNumber);
        data.append('episodeNumber',$scope.episode.episodeNumber);
        data.append('episodeType',$scope.episode.episodeType);
        data.append('content',$scope.episode.content);
        data.append('podcast',$scope.podcast.podcastId);

        $http.post(url,data,{
            headers: {
                'Content-Type': undefined, 'Authorization': 'Bearer ' + getCookie('token') 
            },
            transformRequest: angular.identity
        }).then(resp => {
            showStickyNotification('Create successfully. Let discover','success',3000)
        })
    }

    $('#img-ep').click(function(){
        $('#fileImgEP').click();
        $('#fileImgEP').change(function(event){
            var file = event.target.files[0];
            if (file) {
                $scope.$apply(function () {
                    $scope.coverImg = file;
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var imageDataUrl = e.target.result;
                        $('#img-ep').attr('src', imageDataUrl);
                    };
                    reader.readAsDataURL(file);
                });
            }
        })
    })

})