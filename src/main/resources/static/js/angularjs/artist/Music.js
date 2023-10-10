var app = angular.module('myApp', []);
var host = "http://localhost:8080/api";
app.controller('musicCtrl', function ($scope, $http) {
    $scope.listSongUpcoming = [];
    $scope.listAlbumUpcoming = [];
    $scope.listRecord = [];
    $scope.record = {};
    $scope.song = {};
    $scope.album = {};
    $scope.track = {};
    $scope.listPitch = [];
    //Get list song has not record
    $http.get(host + "/v1/song/up-coming").then(resp => {
        $scope.listSongUpcoming = resp.data.data;
    }).catch(error = {

    })

    //Get list album has not track
    $http.get(host + "/v1/album/up-coming").then(resp => {
        $scope.listAlbumUpcoming = resp.data.data;
    }).catch(error = {

    })

    //Pitch record
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

    //Find list record not song
    $scope.findListRecordNotSong = function () {
        var url = host + "/v1/my-record";
        $http.get(url).then(resp => {
            $scope.listRecord = resp.data.data;
        }).catch(error => {

        })
    }

    //find song
    $scope.findSong = function (idSong) {
        var url = host + "/v1/song/" + idSong;
        $http.get(url).then(resp => {
            $scope.song = resp.data.data;
        }).catch(error => {

        })
    }

    //find record
    $scope.findRecord = function (idRecord) {
        var url = host + "/v1/record/" + idRecord;
        $http.get(url).then(resp => {
            $scope.record = resp.data.data;
        }).catch(error => {

        })
    }

    //Pitch record vào song
    $scope.pitchSong = function (idRecord, idSong) {
        var url = host + "/v1/record";
        $scope.findRecord(idRecord);
        $scope.findSong(idSong);
        $scope.record.song = $scope.song
        var data = angular.copy($scope.record);
        $http.put(url, data).then(resp = {

        }).catch(error => {

        })
    }
    //update song
    $scope.updateSongPitch = function () {

    }


    //find Album
    $scope.findAlbum = function (idAlbum) {
        var url = host + "/v1/album/" + idAlbum;
        $http.get(url).then(resp => {
            $scope.album = resp.data.data;
        }).catch(error => {

        })
    }

    //Find list record has song
    $scope.findListRecordSong = function () {
        var url = host + "/v1/my-record-not-raw";
        $http.get(url).then(resp => {
            $scope.listRecord = resp.data.data;
        }).catch(error => {

        })
    }

    // //Push Record into List
    $scope.checkPitchList = function () {
        $('input[name="pitch"]:checked').each(function () {
            $scope.record=$scope.findRecord($(this).val());
            $scope.listPitch.push($scope.record);
            console.log($scope.listPitch)
        })
    }

    //Create track
    $scope.createTrack = function (album) {
        var url = host + "/v1/track";
        $scope.track.album = album;
        $scope.listRecord.each(item => {
            $scope.track.record = item;
            var data = angular.copy($scope.track);
            $http.post(url, data).then(resp => {
                console.log("success")
            }).catch(error => {
                console.log("loi")
            })
        })
    }
    
    //Finish pitch
    $('#nextBtn').mousedown(function(){
        if ($('#nextBtn').hasClass('submit')) {
            $('#nextBtn').click(function () {
                $scope.checkPitchList();
              
                console.log($scope.listPitch)
            });

        }
    })

    function resetTabs() {
        var x = document.getElementsByClassName("tab");
        for (var i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        currentTab = 0;
        showTab(currentTab);
        $('#current-tab').innerText = currentTab + 1;
        $("#nextBtn").show();
    }
})