var host = "http://localhost:8080/api/";
app.controller('myCtrl', function ($scope, $http, $route, audioService, queueService) {
    $('#myModal').modal('show');
    $scope.account = {};
    $scope.playlist = {};
    $scope.listPlaylist = [];
    $scope.listFollow = [];
    $scope.record = {};

    $scope.Owner = function () {
        let url = host + "v1/account";
        $http.get(url, {
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            $scope.account = resp.data.data;
            $scope.account.userType.forEach(e => {
                $scope.findMyPlaylist(e.userTypeId)
            });
            $scope.findMyListFollow();
        })
    }

    $scope.findMyPlaylist = function (userTypeId) {
        let url = host + "v1/my-playlist/" + userTypeId;
        $http.get(url).then(resp => {
            $scope.listPlaylist.push(...resp.data.data);
        })
    }

    $scope.findMyListFollow = function () {
        let url = host + "v1/my-list-follow";
        $http.get(url, {
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            $scope.listFollow.push(...resp.data.data);
        })
    }

    $scope.createPlaylist = function () {
        let url = host + "v1/playlist";
        let data = angular.copy(playlist);
        $http.post(url, data, {
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            if (resp.success === true) {
                showStickyNotification('Create playlist success', 'success', 2000);
            } else {
                showStickyNotification(resp.data.data, 'warning', 2000);
            }

        }).catch(err => {
            showStickyNotification("Create playlist fail", 'danger', 3000);
            console.log(err);
        })
    }

    $scope.deletePlaylist = function (idPlaylist) {
        let url = host + "v1/playlist/" + idPlaylist;
        $http.delete(url).then(resp => {
            showStickyNotification("Delete playlist success", 'success', 3000);
        }).catch(err => {
            showStickyNotification("Delete playlist fail", 'danger', 3000);
        })
    }

    document.getElementById('create-playlist').addEventListener('click', function () {
        $scope.createPlaylist();
    })

    //Playlist
    var playlistChild = document.getElementsByClassName('playlist-child');
    var playlist = document.getElementsByClassName('playlist');
    if (playlistChild.length > 4) {
        playlist.style = 'overflow-y: hidden;'
    }
    var audio = document.getElementById('audio');
    var duration = document.getElementById('duration-audio');
    var mute = document.getElementById('mute');
    var iconVolume = document.getElementById('icon-mute');
    var volumeAudio = document.getElementById('volumeAudio');
    var play = document.getElementById('play');
    var resume = document.getElementById('resume');
    var loop = document.getElementById('loop');
    var download = document.getElementById('download');
    var currentTimes = document.getElementById('current-time');
    var totalTime = document.getElementById('total-time');
    var next = document.getElementById('next');
    var prev = document.getElementById('prev');
    var shuffle = document.getElementById('shuffle');
    volumeAudio.value = audio.volume;

    $scope.selectAudio = function (item, list, index) {
        audioService.setAudio(item.audioFileUrl);
        if (item.lyricsUrl !== null) {
            audioService.setLyricsSrc(item.lyricsUrl);
        }
        audioService.setListPlay(list);
        audioService.setCurrentAudio(index);
        audio.src = audioService.getAudio();
        $scope.record=item;
        if (resume.hidden === false && play.hidden === true) {
            audio.play();
        } else {
            audio.pause();
        }
    }

    if (audioService.getAudio() !== null) {
        audio.src = audioService.getAudio();
    }

    audio.onloadedmetadata = function () {
        totalTime.innerText = time(audio.duration);
        duration.value = 0;
        duration.setAttribute('max', audio.duration);
        volumeAudio.setAttribute('max', 1);
    };

    //thời gian thay đổi của audio
    audio.ontimeupdate = function () {
        if (audio.duration) {
            duration.value = audio.currentTime;
            currentTimes.innerText = time(audio.currentTime);
        }
        //thay đổi lyrics
        const currentTime = audio.currentTime;
        try {
            const listItems = lyricsContainer.getElementsByTagName("li");
            for (const li of listItems) {
                const time = parseFloat(li.getAttribute("data-time"));
                if (!isNaN(time) && currentTime >= time) {
                    li.style.fontWeight = "bold";
                    li.style.color = "red";
                } else {
                    li.style.fontWeight = "normal"; // Bỏ đánh dấm in đậm
                    li.style.color = "white"; // Reset màu chữ
                }
            }
        } catch (error) {

        }
    }

    //end
    if (audioService.getCurrentAudio() === undefined) {
        var currentSongIndex = 0;
    } else {
        currentSongIndex = audioService.getCurrentAudio();
    }

    audio.addEventListener("ended", function () {
        if (isShuffle === true && currentSongIndex < audioService.getListPlay().length) {
            currentSongIndex = Math.floor(Math.random() * (audioService.getListPlay().length));
            audioService.setCurrentAudio(currentSongIndex);
            let source = audioService.getListPlay()[currentSongIndex];
            audio.src = source.audioFileUrl;
            if (resume.hidden === false && play.hidden === true) {
                audio.play();
            } else {
                audio.pause();
            }
        } else {
            Next();
        }
    });

    //thanh duration thay đổi
    duration.addEventListener('change', function () {
        audio.currentTime = duration.value;
    })

    //play
    play.addEventListener('click', function () {
        audio.play();
        resume.hidden = false;
        play.hidden = true;
        //playlist
        $('#btn-playlist-pause').attr('hidden', false);
        $('#btn-playlist-play').attr('hidden', true);
    })

    //pause
    resume.addEventListener('click', function () {
        audio.pause();
        resume.hidden = true;
        play.hidden = false;
        //playlist
        $('#btn-playlist-pause').attr('hidden', true);
        $('#btn-playlist-play').attr('hidden', false);
    })

    //next
    next.addEventListener('click', function () {
        Next();
    })

    function Next() {
        if (isLoopPlaylist === true && currentSongIndex === audioService.getListPlay().length - 1) {
            currentSongIndex = 0;
            audioService.setCurrentAudio(currentSongIndex);
            let source = audioService.getListPlay()[currentSongIndex];
            $scope.record = audioService.getListPlay()[currentSongIndex];
            audio.src = source.audioFileUrl;
            if (resume.hidden === false && play.hidden === true) {
                audio.play();
            } else {
                audio.pause();
            }
        } else if (currentSongIndex === (audioService.getListPlay().length - 1)) {
            //lấy list set vào Queue
            //lưu queue vào listplay

            currentSongIndex = 0;
            audioService.setListPlay();
            audioService.setCurrentAudio(currentSongIndex);
            let source = audioService.getListPlay()[currentSongIndex];
            $scope.record = audioService.getListPlay()[currentSongIndex];
            audio.src = source.audioFileUrl;
            if (resume.hidden === false && play.hidden === true) {
                audio.play();
            } else {
                audio.pause();
            }
        } else {
            currentSongIndex += 1;
            audioService.setCurrentAudio(currentSongIndex);
            let source = audioService.getListPlay()[currentSongIndex];
            $scope.record = audioService.getListPlay()[currentSongIndex];
            audio.src = source.audioFileUrl;
            if (resume.hidden === false && play.hidden === true) {
                audio.play();
            } else {
                audio.pause();
            }
        }
        if (audioService.getListPlay()[currentSongIndex].lyricsUrl !== null) {
            audioService.setLyricsSrc(audioService.getListPlay()[currentSongIndex].lyricsUrl);
            $scope.record = audioService.getListPlay()[currentSongIndex];
        }
        reloadKaraoke();
    }

    //prev
    prev.addEventListener('click', function () {
        var index = audioService.getCurrentAudio();
        if (index === 0) {
            index = audioService.getListPlay().length - 1;
        } else {
            index = audioService.getCurrentAudio() - 1;
        }
        audioService.setCurrentAudio(index);
        var source = audioService.getListPlay()[index];
        $scope.record = audioService.getListPlay()[index];
        audio.src = source.audioFileUrl;
        if (resume.hidden === false && play.hidden === true) {
            audio.play();
        } else {
            audio.pause();
        }
        if (audioService.getListPlay()[index].lyricsUrl !== null) {
            audioService.setLyricsSrc(audioService.getListPlay()[index].lyricsUrl);
        }
        reloadKaraoke();
    })

    //shuffle
    var isShuffle = false;
    shuffle.addEventListener('click', function () {
        let icon = shuffle.children;
        if (shuffle.classList.contains('isShuffle')) {
            shuffle.classList.remove("isShuffle");
            icon[0].style.color = 'white';
            isShuffle = false;
        } else {
            shuffle.classList.add("isShuffle");
            icon[0].style.color = 'green';
            isShuffle = true;
        }
        //playlist
        let iconPlaylist = $('#btn-playlist-shuffle').children();
        if ($('#btn-playlist-shuffle').hasClass('isShuffle')) {
            $('#btn-playlist-shuffle').removeClass("isShuffle");
            iconPlaylist.eq(0).css('color', 'white', 'important');
        } else {
            $('#btn-playlist-shuffle').addClass("isShuffle");
            iconPlaylist.eq(0).css('color', 'green', 'important');
        }
    })

    //mute
    mute.addEventListener('click', function () {
        if (mute.classList.contains('muted')) {
            audio.volume = 1;
            volumeAudio.value = 1
            mute.classList.remove("muted")
            iconVolume.className = "bi bi-volume-up"
            iconVolume.classList.remove = "bi bi-volume-mute"
        } else {
            audio.volume = 0;
            volumeAudio.value = 0
            mute.classList.add("muted");
            iconVolume.className = "bi bi-volume-mute"
            iconVolume.classList.remove = "bi bi-volume-up"
        }
    })

    //volumn
    volumeAudio.addEventListener('change', function () {
        audio.volume = volumeAudio.value;
    })


    //loop
    var isLoopPlaylist = false;
    loop.addEventListener('click', function () {
        let icon = loop.children;
        if (loop.classList.contains('loop')) {
            icon[0].className = "bi bi-repeat-1"
            loop.classList.add("repeat");
            loop.classList.remove("loop");
            audio.loop = true;
        } else if (loop.classList.contains('repeat')) {
            loop.classList.remove("repeat");
            icon[0].style.color = 'white';
            icon[0].className = "bi bi-repeat"
            isLoopPlaylist = false;
            audio.loop = false;
        } else {
            icon[0].style.color = 'green';
            isLoopPlaylist = true;
            loop.classList.add("loop");
            audio.loop = false;
        }
    })


    //download
    download.addEventListener('click', function () {
        var audioLink = document.createElement("a");
        audioLink.href = audio.getAttribute('src');
        audioLink.download = "file.mp3";
        audioLink.click();
    })

    function reloadKaraoke() {
        $route.reload();
    };

    function time(currentTime) {
        const hours = Math.floor(currentTime / 3600);
        const minutes = Math.floor((currentTime % 3600) / 60);
        const seconds = Math.floor((currentTime % 60));

        const hoursStr = hours > 0 ? (hours < 10 ? "0" + hours : hours) : "";
        const minutesStr = minutes < 10 ? "0" + minutes : minutes;
        const secondsStr = seconds < 10 ? "0" + seconds : seconds;

        if (hours > 0) {
            return `${hoursStr}:${minutesStr}:${secondsStr}`;
        } else {
            return `${minutesStr}:${secondsStr}`;
        }
    }
    audio.load();
    $scope.Owner();

})