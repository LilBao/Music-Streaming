var app = angular.module("myApp", []);
var host = "http://localhost:8080/api";
app.controller('musicCtrl', function ($scope, $http) {
    $scope.listSongUpcoming = [];
    $scope.listAlbumUpcoming = [];
    $scope.listRecord = [];
    $scope.artist = {};
    $scope.record = {};
    $scope.song = {};
    $scope.album = {};
    $scope.track = {};
    $scope.currentDate = new Date();

    //find artist by token email account
    $http.get(host + "/v1/profile", {
        headers: { 'Authorization': 'Bearer ' + getCookie('token') }
    }).then(resp => {
        $scope.artist = resp.data.data;
    }).catch(error => {
        console.log(error)
    })

    //update arrtisst
    $scope.updateArtist = function (data, avatar, background) {
        if (data != null) {
            let url = host + "/v1/artist";
            $http.put(url, data).then(resp => {

            }).catch(error => {

            })
        }
    }

    //Get list song has not record (lấy những bài hát không có record)
    $http.get(host + "/v1/song/up-coming", {
        headers: { 'Authorization': 'Bearer ' + getCookie('token') }
    }).then(resp => {
        $scope.listSongUpcoming = resp.data.data;
    }).catch(error = {

    })

    //Get list album has not track (lấy những album chưa có track nào)
    $http.get(host + "/v1/album/up-coming", {
        headers: { 'Authorization': 'Bearer ' + getCookie('token') }
    }).then(resp => {
        $scope.listAlbumUpcoming = resp.data.data;
    }).catch(error = {

    })

    //Pitch record (Sự kiện để phân biệt đang muốn pitch record hay là album)
    $scope.pitch = function (type, id) {
        resetTabs();
        if (type === "song") {
            $scope.type = "song";
            $scope.findListRecordNotSong();
            $scope.findSong(id);
        } else {
            $scope.type = "album";
            $scope.findListRecordSong();
            $scope.findAlbum(id);
        }
    }

    //Find list record not song (Tìm danh sách các record với field song = null)
    $scope.findListRecordNotSong = function () {
        var url = host + "/v1/my-record";
        $http.get(url, {
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            $scope.listRecord = resp.data.data;
        }).catch(error => {

        })
    }

    //find song (Tìm bài hát theo id bài hát)
    $scope.findSong = function (idSong) {
        var url = host + "/v1/song/" + idSong;
        $http.get(url).then(resp => {
            $scope.song = resp.data.data;
        }).catch(error => {

        })
    }

    //Update song - no image
    $scope.updateSong = function (data) {
        let url = host + "/v1/song"
        $http.put(url, data).then(resp => {
            console.log("update song successfully")
        }).catch(error => {

        })
    }

    //find record (Tìm record theo id record)
    $scope.findRecord = function (idRecord) {
        var url = host + "/v1/record/" + idRecord;
        $http.get(url).then(resp => {
            $scope.record = resp.data.data;
        }).catch(error => {

        })
    }

    //update song (Cập nhật những record được pitch vào song)
    $scope.updateSongPitch = function (song, record) {
        var url = host + "/v1/record";
        record.song = song;
        $http.put(url, record).then(resp => {
            console.log("success");
        }).catch(error => {

        })
    }


    //find Album (Tìm album theo id album)
    $scope.findAlbum = function (idAlbum) {
        var url = host + "/v1/album/" + idAlbum;
        $http.get(url).then(resp => {
            $scope.album = resp.data.data;
        }).catch(error => {

        })
    }

    //update Album
    $scope.updateAlbum = function (data) {
        let url = host + "/v1/album"
        $http.put(url, data).then(resp => {
            console.log("update song successfully")
        }).catch(error => {

        })
    }

    //find track by album (Tìm danh sách track theo id album)
    $scope.findTrackAlbum = function (id) {
        let url = host + "/v1/track-album/" + id;
        $http.get(url).then(resp => {
            $scope.listDetail = resp.data.data;
        })
    }

    //Find list record has song (tìm danh sách record có đính thông tin bài hát)
    $scope.findListRecordSong = function () {
        var url = host + "/v1/my-record-not-raw";
        $http.get(url, {
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            $scope.listRecord = resp.data.data;
        }).catch(error => {

        })
    }

    //Create track (Tạo track mới)
    $scope.createTrack = function (album, record) {
        var url = host + "/v1/track";
        $scope.track.album = album;
        $scope.track.recording = record;
        var item = angular.copy($scope.track);
        $http.post(url, item).then(resp => {
            console.log("success")
        }).catch(error => {
            console.log("loi")
        })
    }
    //list checkbox record kiểm tra
    $scope.listRecordChecked = []
    checkListRecord = function (checkbox) {
        var id = Number(checkbox.value);
        let url = host + "/v1/record/" + id;
        $http.get(url).then(resp => {
            if (checkbox.checked) {
                $scope.listRecordChecked.push(resp.data.data);
            } else {
                var index = -1;
                $scope.listRecordChecked.forEach((item, i) => {
                    if (item.recordingId === id) {
                        index = i;
                    }
                });
                if (index !== -1) {
                    $scope.listRecordChecked.splice(index, 1);
                }
            }
        }).catch(error => {

        })
    }

    //Finish pitch (Hoàn thành việc pitch record vào song hoặc album)
    $('#nextBtn').click(function () {
        if ($('#nextBtn').hasClass('submit')) {
            var data = {};
            $scope.song.description = $scope.descriptions;
            $scope.album.description = $scope.descriptions;
            var dataSong = angular.copy($scope.song);
            var dataAlbum = angular.copy($scope.album);
            var dataArtist = angular.copy($scope.artist)

            $('input[name="pitch"]:checked').each(function () {
                var url = host + "/v1/record/" + $(this).val();
                $http.get(url).then(resp => {
                    data = (resp.data.data);
                    if ($scope.type === "song") {
                        $scope.updateSongPitch(dataSong, data)
                    } else {
                        $scope.createTrack(dataAlbum, data);
                    }
                }).catch(error => {
                })
            })
            if ($scope.type === "song") {
                $scope.updateSong(dataSong);
            } else {
                $scope.updateAlbum(dataAlbum);
            }
            $scope.updateArtist(dataArtist);
        }
    });

    //Get list album released (Tìm các album đã được released và đính kèm bài hát)
    $scope.listAlbumReleased = [];
    $http.get(host + "/v1/album-artist-released", {
        headers: { 'Authorization': 'Bearer ' + getCookie('token') }
    }).then(resp => {
        $scope.listAlbumReleased = resp.data.data;
    }).catch(error => {
        console.log(error);
    })

    //Get list song released (Tìm các song đã được released và đính kèm bài hát)
    $scope.listSongReleased = [];
    $http.get(host + "/v1/song-artist-released", {
        headers: { 'Authorization': 'Bearer ' + getCookie('token') }
    }).then(resp => {
        $scope.listSongReleased = resp.data.data;
    }).catch(error => {
        console.log(error);
    })

    //Get detail album or song (Nhấn xem chi tiết 1 album hoặc 1 bài hát)
    $scope.listDetail = [];
    $scope.detail = function (id, type) {
        if (type === 'album') {
            $scope.findAlbum(id);
            $scope.typeDetail = "album";
            $scope.findListRecordSong();
            let url = host + "/v1/track-album/" + id;
            $http.get(url).then(resp => {
                $scope.listDetail = resp.data.data;
            })
        } else {
            $scope.findSong(id);
            $scope.findListRecordNotSong();
            $scope.typeDetail = "song"
            let url = host + "/v1/record-song/" + id;
            $http.get(url).then(resp => {
                $scope.listDetail = resp.data.data
            })
        }
    }

    //Confirm and remove record out album or song (Xóa record hoặc track theo song,album)
    $scope.removeRecord = function (id, type) {
        if (type === 'song') {
            let url = host + "/v1/record/" + id;
            $http.get(url).then(resp => {
                var data = resp.data.data;
                data.song = null;
                $scope.updateRecord(data);
                let url = host + "/v1/record-song/" + data.songId;
                $http.get(url).then(resp => {
                    $scope.listDetail = resp.data.data
                })
            }).catch(error => {

            })
        } else {
            let url = host + "/v1/track/" + id;
            $http.delete(url).then(resp => {
                $scope.findTrackAlbum(id);
            }).catch(error => {

            })
        }
    }

    //Update record (Sửa thông tin record)
    $scope.updateRecord = function (data) {
        let url = host + "/v1/record";
        $http.put(url, data).then(resp => {
            console.log("success")
        })
    }
    //update cover image song
    $scope.updateCoverImageSong = function (id) {
        var formdata = new FormData();
        formdata.append('coverImg', $scope.coverImg);
        let url = host + "/v1/song-image" + id;
        $http.put(url, formdata, {
            headers: {
                'Content-Type': undefined,
                'Authorization': 'Bearer ' + getCookie('token')
            },
            transformRequest: angular.identity
        }).then(resp => {

        }).catch(error => {

        })
    }

    //update cover image album
    $scope.updateCoverImageAlbum = function (id) {
        var formdata = new FormData();
        formdata.append('coverImg', $scope.coverImg);
        let url = host + "/v1/album-image/" + id;
        $http.put(url, formdata, {
            headers: {
                'Content-Type': undefined,
                'Authorization': 'Bearer ' + getCookie('token')
            },
            transformRequest: angular.identity
        }).then(resp => {

        }).catch(error => {

        })
    }

    //update cover image
    $scope.updateCoverImage = function (type) {
        if (type === 'song') {
            let song = angular.copy($scope.song);
            $scope.updateCoverImageSong(song.songId);
        } else {
            let album = angular.copy($scope.album);
            $scope.updateCoverImageAlbum(album.albumId);
        }
    }

    //update song or album
    $scope.updateReleaseDetail = function (type) {
        if (type === 'song') {
            let song = angular.copy($scope.song);
            song.songName = $('#nameDetail').val();
            song.releaseDay = $('#releaseDetail').val();
            $scope.updateSong(song);
            $scope.updateCoverImage(type);
        } else {
            let album = angular.copy($scope.album);
            album.albumName = $('#nameDetail').val();
            album.releaseDate = $('#releaseDetail').val();
            $scope.updateAlbum(album);
            $scope.updateCoverImage(type);
        }
    }

    //logic update or not
    $('#modified-release').click(function () {
        var icon = $(this).find("i");
        if ($('#modified-release').hasClass("save")) {
            icon.removeClass("bi-check-lg");
            icon.addClass("bi-pencil-fill");
            $('#modified-release').removeClass("save");
            $('#nameDetail').attr('readonly', true);
            $('#releaseDetail').attr('readonly', true);
            $('#addRecord').attr('hidden', true);
            $scope.updateReleaseDetail($scope.typeDetail);
        } else {
            icon.removeClass("bi-pencil-fill");
            icon.addClass("bi-check-lg");
            $('#modified-release').addClass("save");
            $('#nameDetail').removeAttr('readonly');
            $('#releaseDetail').removeAttr('readonly');
            $('#addRecord').removeAttr('hidden');
            $('#coverImg').attr('ng-click', 'selectFile()');
        }
    })

    //add song or album
    $scope.addRecordToSongOrAlbum = function (type,item,record) {
        if (type === 'album') {
            $scope.updateSongPitch(item, record);
            findListRecordSong();
        } else {
            $scope.createTrack(item,record);
            findListRecordSong();
        }
    }


    //JS
    function resetTabs() {
        var x = document.getElementsByClassName("tab");
        for (var i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        currentTab = 0;
        showTab(currentTab);
        $('#current-tab').innerText = currentTab + 1;
        $("#nextBtn").removeClass("submit");
        $("#nextBtn").show();
    }

    //description
    $('#description').on('change', function () {
        $('#result-description').text($(this).val());
        $scope.descriptions = $(this).val();
    });

    $scope.selectFile = function () {
        $('#fileCoverImg').click();
        $('#fileCoverImg').change(function (event) {
            var file = event.target.files[0];
            if (file) {
                $scope.$apply(function () {
                    $scope.coverImg = file;

                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var imageDataUrl = e.target.result;
                        $('#coverImg').attr('src', imageDataUrl);
                    };
                    reader.readAsDataURL(file);
                });
            }
        });
    };
})