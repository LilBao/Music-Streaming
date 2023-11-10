var host = "http://localhost:8080/api/";
app.controller('myCtrl', function ($scope, $http, $route, audioService, queueService, graphqlService) {
    $('#myModal').modal('show');
    //variable of sidebar
    $scope.account = {};
    $scope.playlist = {};
    $scope.listPlaylist = [];
    $scope.listFollow = [];

    //variable of current audio
    $scope.audioItem = {};

    //variable of wislish and monitor
    $scope.monitor = {};
    $scope.wishlist = {};

    //variable of Ads
    $scope.listAdvertisment = [];
    $scope.ads = {};
    $scope.officalAds = [];
    var audioAds = new Audio();
    var currentAds = 0;

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
    $scope.Owner();

    $scope.findMyPlaylist = function (userTypeId) {
        let url = host + "v1/my-playlist/" + userTypeId;
        $http.get(url).then(resp => {
            $scope.listPlaylist.push(...resp.data.data);
        })
    }

    $scope.findMyListFollow = function () {
        let query = `{
            myListFollow(email: "`+ $scope.account.email + `") {
                followerId
                authorsAccountB {
                  role{
                    role
                  }
                  account {
                    username
                    image{
                      url
                    }
                    artist{
                      artistName
                      imagesProfile{
                        url
                      }
                    }
                  }
                }
              }
            }`
        graphqlService.executeQuery(query).then(data => {
            $scope.listFollow = data.myListFollow
        })
    }

    $scope.createPlaylist = function () {
        let url = host + "v1/playlist";
        let data = angular.copy(playlist);
        $http.post(url, data, {
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            if (resp.data.success === true) {
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
    var btnWishlist = document.getElementById('btn-wishlist');
    var checkWishlist = document.querySelectorAll('bi bi-heart');

    volumeAudio.value = audio.volume;


    $scope.getCurrentAudio = function (id, type) {
        if (type === 'song') {
            var query = `{
            recordingById(recordingId:`+ id + `) {
                recordingId
                recordingName
                audioFileUrl
                publicIdAudio
                lyricsUrl
                publicIdLyrics
                duration
                songStyle
                listened
                mood
                culture
                instrument
                versions
                studio
                idMv
                produce
                recordingdate
                isDeleted
                emailCreate
                song{
                    songId
                    image{
                        accessId
                        url
                    }
                    writters{
                        artist{
                            artistId
                            artistName
                        }
                    }
                }
              }
        }`} else {
            var query = `{
                episodeById(episodeId: `+ id + `) {
                    episodeId
                    publicIdFile
                    fileUrl
                    episodeTitle
                    description
                    publishDate
                    sessionNumber
                    episodeNumber
                    episodeType
                    content
                    isPublic
                    isDelete
                    listened
                    duration
                    image {
                      accessId
                      url
                    }
                    podcast {
                      podcastId
                      authorName
                      podcastName
                    }
                }
            }`
        }
        graphqlService.executeQuery(query)
            .then(data => {
                if (type === 'song') {
                    $scope.audioItem = data.recordingById;
                } else {
                    $scope.audioItem = data.episodeById;
                }
            })
            .catch(error => {
                console.log(error);
            });
    }

    $scope.selectAudio = function (item, type, list, index) {
        listened = 0;
        if (type === 'song') {
            audioService.setAudio(item.audioFileUrl);
            if (item.lyricsUrl) {
                audioService.setLyricsSrc(item.lyricsUrl);
            }
            $scope.getCurrentAudio(item.recordingId, 'song')
        } else {
            audioService.setAudio(item.fileUrl);
            $scope.getCurrentAudio(item.episodeId, 'episode');
        }
        audioService.setListPlay(list);
        audioService.setCurrentAudio(index);
        audio.src = audioService.getAudio();
        currentAudioIndex = audioService.getCurrentAudio();

        listHistoryAudio.push(item);
        var encryptedData = CryptoJS.AES.encrypt(JSON.stringify(listHistoryAudio), "whoami").toString();
        setCookie("history", encodeURIComponent(encryptedData), 30);

        if (resume.hidden === false && play.hidden === true) {
            audio.play();
        } else {
            audio.pause();
        }
    }

    //check cookie
    if (audioService.getAudio()) {
        audio.src = audioService.getAudio();
    } else {
        try {
            if (getCookie("history")) {
                var decryptedData = CryptoJS.AES.decrypt(decodeURIComponent(getCookie("history")), "whoami");
                var decryptedString = decryptedData.toString(CryptoJS.enc.Utf8);
                var listHistoryAudio = JSON.parse(decryptedString);
                if (listHistoryAudio[listHistoryAudio.length - 1].recordingId) {
                    $scope.selectAudio(listHistoryAudio[listHistoryAudio.length - 1], 'song', listHistoryAudio, listHistoryAudio.length - 1);
                } else {
                    $scope.selectAudio(listHistoryAudio[listHistoryAudio.length - 1], 'episode', listHistoryAudio, listHistoryAudio.length - 1);
                }
            } else {
                var listHistoryAudio = [];
            }
        } catch (error) {

        }
    }

    audio.onloadedmetadata = function () {
        totalTime.innerText = time(audio.duration);
        duration.value = 0;
        duration.setAttribute('max', audio.duration);
        volumeAudio.setAttribute('max', 1);

    };

    //thời gian thay đổi của audio
    var listened;
    audio.ontimeupdate = function () {
        if (audio.duration) {
            duration.value = audio.currentTime;
            currentTimes.innerText = time(audio.currentTime);
        }
        if (audio.currentTime === 0) {
            listened = 0;
            currentAds=0;
            if ($scope.account.userType) {
                if (($scope.account.userType.length === 1 || new Date($scope.account.userType[1].endDate) < new Date())
                    && (Math.floor(Math.random() * 3) + 1 === 3) && $scope.officalAds.length > 0) {
                    resume.click();
                    play.disabled=true;
                    resume.disabled=true;
                    loop.disabled=true;
                    next.disabled=true;
                    prev.disabled=true;
                    shuffle.disabled=true;
                    btnWishlist.disabled=true;
                    duration.disabled=true;
                    $scope.insertAds();
                }
            }
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
    audio.load();

    //end
    if (audioService.getCurrentAudio() === undefined) {
        var currentAudioIndex = 0;
    } else {
        currentAudioIndex = audioService.getCurrentAudio();
    }

    audio.addEventListener("ended", function () {
        if (isShuffle === true && currentAudioIndex < audioService.getListPlay().length) {
            listened = 0;
            currentAudioIndex = Math.floor(Math.random() * (audioService.getListPlay().length));
            audioService.setCurrentAudio(currentAudioIndex);
            let source = audioService.getListPlay()[currentAudioIndex];
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

    //count real time view
    //update listened
    $scope.updateListened = function () {
        $scope.countTime = setInterval(function () {
            listened++;
            if (listened === (Math.floor($scope.audioItem.duration / 3))) {
                if ($scope.audioItem.recordingId) {
                    $scope.Monitoring();

                } else {
                    $scope.Monitoring();
                }
            }
            if (listened === (Math.floor($scope.audioItem.duration / 2))) {
                if ($scope.audioItem.recordingId) {
                    $scope.updateListenedAudio();

                } else {
                    $scope.updateListenedAudio();
                }
            }
        }, 1000);
    }


    //play
    play.addEventListener('click', function () {
        audio.play();
        resume.hidden = false;
        play.hidden = true;
        //playlist
        $('#btn-playlist-pause').attr('hidden', false);
        $('#btn-playlist-play').attr('hidden', true);
        if (resume.hidden === false && play.hidden === true) {
            $scope.updateListened();
        }
    })

    //pause
    resume.addEventListener('click', function () {
        audio.pause();
        resume.hidden = true;
        play.hidden = false;
        clearInterval($scope.countTime);
        //playlist
        $('#btn-playlist-pause').attr('hidden', true);
        $('#btn-playlist-play').attr('hidden', false);
    })

    //next
    next.addEventListener('click', function () {
        Next();
    })

    function Next() {
        listened = 0;
        currentAds = 0;
        if (isLoopPlaylist === true && currentAudioIndex === audioService.getListPlay().length - 1) {
            currentAudioIndex = 0;
            audioService.setCurrentAudio(currentAudioIndex);
            var item = audioService.getListPlay()[currentAudioIndex];
            if (item.recordingId) {
                $scope.selectAudio(item, 'song', audioService.getListPlay(), currentAudioIndex)
            } else {
                $scope.selectAudio(item, 'episode', audioService.getListPlay(), currentAudioIndex)
            }
            if (resume.hidden === false && play.hidden === true) {
                audio.play();
            } else {
                audio.pause();
            }
        } else if (currentAudioIndex === (audioService.getListPlay().length - 1)) {
            //lấy list set vào Queue
            //lưu queue vào listplay

            currentAudioIndex = 0;
            audioService.setListPlay();
            audioService.setCurrentAudio(currentAudioIndex);
            var item = audioService.getListPlay()[currentAudioIndex];
            if (item.recordingId) {
                $scope.selectAudio(item, 'song', audioService.getListPlay(), currentAudioIndex)
            } else {
                $scope.selectAudio(item, 'episode', audioService.getListPlay(), currentAudioIndex)
            }
            if (resume.hidden === false && play.hidden === true) {
                audio.play();
            } else {
                audio.pause();
            }
        } else {
            currentAudioIndex += 1;
            audioService.setCurrentAudio(currentAudioIndex);
            var item = audioService.getListPlay()[currentAudioIndex];
            if (item.recordingId) {
                $scope.selectAudio(item, 'song', audioService.getListPlay(), currentAudioIndex)
            } else {
                $scope.selectAudio(item, 'episode', audioService.getListPlay(), currentAudioIndex)
            }
            if (resume.hidden === false && play.hidden === true) {
                audio.play();
            } else {
                audio.pause();
            }
        }
        if (audioService.getListPlay()[currentAudioIndex].lyricsUrl !== null) {
            audioService.setLyricsSrc(audioService.getListPlay()[currentAudioIndex].lyricsUrl);
            $scope.audioItem = audioService.getListPlay()[currentAudioIndex];
        }
        reloadKaraoke();
    }

    //prev
    prev.addEventListener('click', function () {
        listened = 0;
        currentAds = 0;
        var index = audioService.getCurrentAudio();
        if (index === 0) {
            index = audioService.getListPlay().length - 1;
        } else {
            index = audioService.getCurrentAudio() - 1;
        }
        audioService.setCurrentAudio(index);
        var item = audioService.getListPlay()[index];
        if (item.recordingId) {
            $scope.selectAudio(item, 'song', audioService.getListPlay(), index)
        } else {
            $scope.selectAudio(item, 'episode', audioService.getListPlay(), index)
        }

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
            try {
                audioAds.volume = 1;
            } catch (error) {
                
            }
            volumeAudio.value = 1
            mute.classList.remove("muted")
            iconVolume.className = "bi bi-volume-up"
            iconVolume.classList.remove = "bi bi-volume-mute"
            
        } else {
            audio.volume = 0;
            try {
                audioAds.volume = 0;
            } catch (error) {
                
            }
            volumeAudio.value = 0
            mute.classList.add("muted");
            iconVolume.className = "bi bi-volume-mute"
            iconVolume.classList.remove = "bi bi-volume-up"
            
        }
    })

    //volumn
    volumeAudio.addEventListener('change', function () {
        audio.volume = volumeAudio.value;
        try {
            audioAds.volume = volumeAudio.value;
        } catch (error) {
            
        }
        if(audio.volume>0 || audioAds.volume >0){
            mute.classList.remove("muted")
            iconVolume.className = "bi bi-volume-up"
            iconVolume.classList.remove = "bi bi-volume-mute"
        }else{
            mute.classList.add("muted");
            iconVolume.className = "bi bi-volume-mute"
            iconVolume.classList.remove = "bi bi-volume-up"
        }
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

    $scope.updateListenedAudio = function () {
        let data = angular.copy($scope.audioItem);
        if (data.recordingId) {
            var url = host + "v1/record";
            data.recordingdate = new Date(data.recordingdate);
        } else {
            var url = host + "v1/episode";
            data.publishDate = new Date(data.publishDate);
        }

        data.listened += 1;
        $http.put(url, data).then(resp => {

        }).catch(err => {

        })
    }

    /*------------------------------*/
    /******************************* */
    /**        Monitor             */
    /***************************** */
    $scope.Monitoring = function () {
        var item = angular.copy($scope.monitor);
        var data = angular.copy($scope.audioItem);
        item.account = $scope.account;
        if (data.recordingId) {
            var url = host + "v1/monitor";
            data.recordingdate = new Date(data.recordingdate);
            item.recording = data;
        } else {
            var url = host + "v1/monitor-episode";
            data.publishDate = new Date(data.publishDate);
            item.episode = data;
        }
        $http.post(url, item).then(resp => {

        }).catch(err => {
            console.log(err)
        })
    }

    /*------------------------------*/
    /******************************* */
    /**        Wishlist             */
    /***************************** */

    $scope.checkExisted = function (data, event) {
        //var icon = event.target; 
        let url = host + "v1/exist-my-wishlist"
        if (data.recordingId) {
            var recording = data.recordingId;
            var episode = null;
        } else {
            var recording = null;
            var episode = data.episodeId;
        }
        $http.get(url, {
            params: { episode: episode, recording: recording },
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            console.log(resp.data)
            if (resp.data.data === true) {
                icon.style.color = "green"
            }
        })
    }
    var checkWishlist = document.querySelectorAll('bi bi-heart');
    checkWishlist.forEach(item => {
        $scope.checkExisted(item)
    })

    $scope.addToWishlist = function (data) {
        let item = angular.copy($scope.wishlist);
        if (data.recordingId) {
            data.recordingdate = new Date(data.recordingdate);
            item.recording = data;
            item.episode = null;
        } else {
            data.publishDate = new Date(data.publishDate);
            item.recording = null;
            item.episode = data;
        }
        let url = host + "v1/wishlist"
        $http.post(url, item, {
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            if (resp.data.success === true) {
                showStickyNotification("Add wishlist success", 'success', 3000);
            } else {
                showStickyNotification(resp.data.message, 'warning', 3000);
            }
        }).catch(err => {
            console.log(err)
        })
    }

    /*------------------------------*/
    /******************************* */
    /**       Advertisment          */
    /***************************** */

    //Advertisment with ratio 33% and certainty 1 ads priority 1 and 6/4 (priority 2 / priority 3)
    $scope.findAllListAdsAudio = function () {
        const query = `{
            findAllAdsAudio {
              adId
              title
              content
              url
              targetAudience
              clicked
              listened
              priority
              audioFile
              tag
              image {
                accessId
                url
              }
            }
        }`
        graphqlService.executeQuery(query).then(data => {
            $scope.listAdvertisment = data.findAllAdsAudio;
            $scope.ratioAds($scope.listAdvertisment);
        }).catch(err => {

        })
    }

    $scope.ratioAds = function (list) {
        if (list.length > 0) {
            $scope.officalAds.push(list[0]);
            var priority1Ads = list.filter(ad => ad.priority === 1);
            var ads2 = list.find(item => {
                return item.priority == 2;
            })
            var ads3 = list.find(item => {
                return item.priority == 3;
            })

            var ratio = Math.random();
            if (priority1Ads.length > 0) {
                var random = Math.floor(Math.random() * priority1Ads.length) + 1; //skip ads 0
                var ads1 = priority1Ads[random];
                var twentyPercent = 0.2;
                var sixtyPercent = 0.6;
                let selectedObject = ratio <= twentyPercent ? ads3 : (twentyPercent < ratio < sixtyPercent ? ads2 : ads1);
                $scope.officalAds.push(selectedObject);
            } else {
                if (ads2) {
                    var sixtyPercent = 0.6;
                    let selectedObject = ratio < sixtyPercent ? ads2 : ads3;
                    $scope.officalAds.push(selectedObject);
                }
            }
        }
    }
    $scope.findAllListAdsAudio();

    $scope.insertAds = function () {
        $scope.ads = $scope.officalAds[currentAds];
        audioAds.src = $scope.officalAds[currentAds].audioFile;
        audioAds.play();
    }

    audioAds.addEventListener('ended', function () {
        currentAds++;
        if (currentAds === $scope.officalAds.length) {
            play.disabled=false;
            resume.disabled=false;
            loop.disabled=false;
            next.disabled=false;
            prev.disabled=false;
            shuffle.disabled=false;
            btnWishlist.disabled=false;
            play.click();
            duration.disabled=false;
            currentAds = 0;
            $scope.officalAds = [];
            $scope.ads={};
            $scope.findAllListAdsAudio();
        } else {
            var item = angular.copy($scope.ads);
            item.listened += 1;
            console.log(item)
            $scope.updateAds(item);
            
            $scope.insertAds();
        }
        
    });

    //update listened ads
    $scope.updateAds = function (item) {
        let url = host + "v1/ads";
        $http.put(url, item).then(resp => {
            console.log(resp.data.data)
        }).catch(err => {

        })
    }

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

    function clickHandler(event) {
        event.preventDefault()
    }

})