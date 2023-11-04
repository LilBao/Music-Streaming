var host = "http://localhost:8080/api/";
app.controller('playlistCtrl', function ($scope, $http, $routeParams, $location) {
    // var params = $location.search();
    // $scope.paramA = params.a;
    // $scope.paramB = params.b;
    $scope.playlist = {};
    $scope.playlistId = $routeParams.id;
    $scope.playlistRecord = {};
    $scope.playlistPodcast = {};
    $scope.listRecommended = [];
    $scope.listAudioPlaylist = [];

    $scope.findPlaylist = function () {
        let url = host + "v1/playlist/" + $scope.playlistId;
        $http.get(url).then(resp => {
            $scope.playlist = resp.data.data
            console.log($scope.playlist.recording[0])
        }).catch(err => {

        })
    }
    $scope.findPlaylist();
    
    $('#btn-playlist-play').click(function () {
        $('#btn-playlist-pause').attr('hidden', false);
        $('#btn-playlist-play').attr('hidden', true);
     
            $scope.selectSong($scope.playlist.recording[0], $scope.playlist.recording, 0);
            play.click();
        
    })

//pause
$('#btn-playlist-pause').click(function () {
    $('#btn-playlist-pause').attr('hidden', true);
    $('#btn-playlist-play').attr('hidden', false);
    resume.click();
})

$('#btn-playlist-shuffle').click(function () {
    let icon = $('#btn-playlist-shuffle').children();
    if ($('#btn-playlist-shuffle').hasClass('isShuffle')) {
        $('#btn-playlist-shuffle').removeClass("isShuffle");
        icon.eq(0).css('color', 'white', 'important');
    } else {
        $('#btn-playlist-shuffle').addClass("isShuffle");
        icon.eq(0).css('color', 'green', 'important');
    }
    shuffle.click();
})

play.addEventListener('click', function () {
    audio.play();
    resume.hidden = false;
    play.hidden = true;
})

//pause
resume.addEventListener('click', function () {
    audio.pause();
    resume.hidden = true;
    play.hidden = false;
})

$scope.updatePlaylist = function () {
    let url = host + "v1/playlist";
    let data = angular.copy($scope.playlist);
    $http.put(url, data).then(resp => {
        if ($scope.coverImg !== undefined) {
            $scope.updateImage();
        } else {
            showStickyNotification('Update successfull', 'success', 3000);
        }
    }).catch(err => {

    })
}

$scope.updateImage = function () {
    let url = host + "v1/playlist-image"
    let data = new FormData();
    data.append('coverImg', $scope.coverImg);
    data.append('id', +$scope.playlistId);
    $http.put(url, data, {
        headers: {
            'Content-Type': undefined,
            'Authorization': 'Bearer ' + getCookie('token')
        },
        transformRequest: angular.identity
    }).then(resp => {
        showStickyNotification('Update successfull', 'success', 3000);
        $scope.coverImg = undefined;
    }).catch(err => {
        console.log(err);
    })
}

$scope.recommended = function () {
    let url = host + "v1/record-random"
    $http.get(url).then(resp => {
        $scope.listRecommended = resp.data.data;
    }).catch(err => {
        console.log(err)
    })
}
$scope.recommended();
$('#refresh').click(function () {
    $scope.recommended();
})

$scope.additionRecord = function (item) {
    let url = host + "v1/playlist-record";
    let data = angular.copy($scope.playlistRecord);
    data.recording = item;
    data.playlist = $scope.playlist;
    $http.post(url, data).then(resp => {
        showStickyNotification('Addition successfull', 'success', 3000);
    })
}

$scope.additionEpisode = function (item) {
    let url = host + "v1/playlist-episode";
    $scope.playlistPodcast.episode = item;
    $scope.playlistPodcast.playlist = $scope.playlist;
    let data = angular.copy($scope.playlistRecord);
    $http.post(url, data).then(resp => {
        showStickyNotification('Addition successfull', 'success', 3000);
    })
}

$scope.addition = function (type, item, index) {
    if (type === 'record') {
        $scope.additionRecord(item);
    } else {
        $scope.additionEpisode(id);
    }
    $scope.listRecommended.splice(index, 1)
}

$('#btn-update-playlist').click(function () {
    $scope.updatePlaylist();
})

$('.img-playlist').click(function () {
    $('#img-playlist').click();
    $('#img-playlist').change(function (e) {
        var file = e.target.files[0];
        if (file) {
            $scope.coverImg = file;
            $scope.$apply(function () {
                var reader = new FileReader();
                reader.onload = function (e) {
                    var imageDataUrl = e.target.result;
                    $('.img-playlist').attr('src', imageDataUrl);
                };
                reader.readAsDataURL(file);
            })
        }
    })
})
})