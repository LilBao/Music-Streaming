var host = "http://localhost:8080/api/";
app.controller('playlistCtrl', function ($scope, $http, $routeParams, $location, graphqlService, audioService) {
    // var params = $location.search();
    // $scope.paramA = params.a;
    // $scope.paramB = params.b;
    $scope.playlist = {};
    $scope.playlistId = $routeParams.id;
    $scope.playlistRecord = {};
    $scope.playlistPodcast = {};
    $scope.listRecommended = [];
    $scope.listAudioPlaylist = [];
    $scope.wishList = [];
    $scope.listLikedSongs = [];

    $scope.findPlaylist = function () {
        if ($scope.playlistId !== undefined) {
            const query = `{
            playlistById(playlistId:`+ $scope.playlistId + `){
                playlistId
                playlistName
                isPublic
                description
                image {
                    accessId
                    url
                }
                usertype {
                    userTypeId
                    nameType
                }
                playlistRecords {
                    dateAdded
                    recording {
                        recordingId
                        duration
                        recordingName
                        audioFileUrl
                        tracks{
                            album{
                                albumId
                                albumName
                            }
                        }
                        song {
                            image {
                                url
                            }
                            writters{
                                artist{
                                artistName
                                }
                            }
                        }
                    }
                }
                playlistPodcast {
                dateAdded
                episode {
                    episodeId
                    duration
                    episodeTitle
                    fileUrl
                    image{

                        url
                    }
                    podcast{
                        podcastId
                        authorName
                        podcastName
                    }
                }
                }
            }
        }`
            graphqlService.executeQuery(query).then(data => {
                try {
                    $scope.playlist = data.playlistById;
                    $scope.listDateAdded = [...data.playlistById.playlistRecords, ...data.playlistById.playlistPodcast]

                    $scope.listAudioPlaylist = [...data.playlistById.playlistRecords.map(function (item) {
                        return { recording: item.recording };
                    }).map(function (item) {
                        return item.recording;
                    }), ...data.playlistById.playlistPodcast.map(function (item) {
                        return { episode: item.episode };
                    }).map(function (item) {
                        return item.episode;
                    })];
                } catch (error) {

                }
            }).catch(error => {
                console.log(error);
            });
        }
    }
    $scope.findPlaylist();

    $('#btn-playlist-play').click(function () {
        $('#btn-playlist-pause').attr('hidden', false);
        $('#btn-playlist-play').attr('hidden', true);
        if ($scope.listAudioPlaylist[0].recordingId) {
            $scope.selectAudio($scope.listAudioPlaylist[0], 'song', $scope.listAudioPlaylist, 0);
        } else {
            $scope.selectAudio($scope.listAudioPlaylist[0], 'episode', $scope.listAudioPlaylist, 0);
        }
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
        console.log(data)
        $http.put(url, data).then(resp => {
            if ($scope.coverImg !== undefined) {
                $scope.updateImage();
            } else {
                showStickyNotification('Update successfull', 'success', 3000);
            }
        }).catch(err => {
            console.log("")
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
        const query = `{
            recommendedListRecording {
                recordingId
                recordingName
                audioFileUrl
                song{
                  writters{
                    artist{
                      artistId
                      artistName
                    }
                  }
                  image{
                    url
                  }
                }
                tracks{
                  album{
                    albumName
                  }
                }
            }
        }`
        graphqlService.executeQuery(query).then(data => {
            $scope.listRecommended = data.recommendedListRecording
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
            $scope.findPlaylist();
            showStickyNotification('Addition successfull', 'success', 3000);
        })
    }

    $scope.additionEpisode = function (item) {
        let url = host + "v1/playlist-episode";
        $scope.playlistPodcast.episode = item;
        $scope.playlistPodcast.playlist = $scope.playlist;
        let data = angular.copy($scope.playlistRecord);
        $http.post(url, data).then(resp => {
            $scope.findPlaylist();
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

    /*------------------------------*/
    /******************************* */
    /**        Wishlist             */
    /***************************** */
    $scope.MyWishList = function () {
        if ($scope.account.email !== undefined) {
            const query = `{
            myWishlist(email: "mck@gmail.com") {
              wishlistId
              addDate
              usertype {
                userTypeId
                nameType
                startDate
                endDate
                status
                paymentStatus
              }
              recording {
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
                    url
                  }
                  writters{
                    artist{
                      artistName
                    }
                  }
                }
                tracks{
                  album{
                    albumName
                  }
                }
              }
              episode {
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
                image{
                  accessId
                  url
                }
                podcast{
                  podcastId
                  podcastName
                  authorName
                }
              }
            }
        }`
            graphqlService.executeQuery(query).then(data => {
                $scope.wishList = data.myWishlist;
                if ($scope.wishList) {
                    $scope.wishList.forEach(item => {
                        if (item.recording) {
                            $scope.listLikedSongs.push(item.recording);
                        } else {
                            $scope.listLikedSongs.push(item.episode);
                        }
                    });
                }
            }).catch(err => {
                console.log(err)
            })
        }
    }
    $scope.MyWishList();

    $('#btn-wishlist-play').click(function () {
        $('#btn-wishlist-pause').attr('hidden', false);
        $('#btn-wishlist-play').attr('hidden', true);
        if ($scope.listLikedSongs[0].recordingId) {
            $scope.selectAudio($scope.listLikedSongs[0], 'song', $scope.listLikedSongs, 0);
        } else {
            $scope.selectAudio($scope.listLikedSongs[0], 'episode', $scope.listLikedSongs, 0);
        }
        play.click();
    })

    $('#btn-wishlist-shuffle').click(function () {
        let icon = $('#btn-playlist-shuffle').children();
        if ($('#btn-wishlist-shuffle').hasClass('isShuffle')) {
            $('#btn-wishlist-shuffle').removeClass("isShuffle");
            icon.eq(0).css('color', 'white', 'important');
        } else {
            $('#btn-wishlist-shuffle').addClass("isShuffle");
            icon.eq(0).css('color', 'green', 'important');
        }
        shuffle.click();
    })

    $scope.removeWishlist = function (id, index) {
        let url = host + "v1/wishlist/" + id;
        $http.delete(url).then(resp => {
            $scope.listLikedSongs.splice(index, 1);
            showStickyNotification('Removed from liked songs', 'success', 3000);
        }).catch(err => {
            showStickyNotification('Try again', 'warning', 3000);
        })
    }

    $scope.isLiked = function (data) {
        if (data.recordingId) {
            var index = $scope.listLikedSongs.findIndex(item => item.recordingId ===data.recordingId);
        } else {    
            var index = $scope.listLikedSongs.findIndex(item => item.episodeId ===data.episodeId);
        }
        return index !==-1;
    }

    //js
    if (play.hidden == true) {
        $('#btn-playlist-pause').attr('hidden', false);
        $('#btn-playlist-play').attr('hidden', true);
    } else {
        $('#btn-playlist-pause').attr('hidden', true);
        $('#btn-playlist-play').attr('hidden', false);
    }

})