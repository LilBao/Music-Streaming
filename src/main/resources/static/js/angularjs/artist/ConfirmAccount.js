var app = angular.module('myApp', []);
var host = "http://localhost:8080/api";
app.controller('myCtrl', function ($scope, $http) {
    
    $scope.artist = {}
    $scope.createArtist = function() {
        
        var url = host + "/v1/artist";
        var formData = new FormData();
        formData.append('artistName', $scope.artist.artistName); 
        formData.append('fullName', $scope.artist.fullName);
        formData.append('bio', $scope.artist.bio); 
        formData.append('artist.dateOfBirth', $scope.artist.dateOfBirth); 
        formData.append('placeOfBirth', $scope.artist.placeOfBirth); 
        formData.append('avatar', $scope.avatarFile);
        formData.append('background', $scope.backgroundFile);
        $http({
            method: 'POST',
            url: url,
            headers: { 'Content-Type': undefined }, 
            data: formData,
            transformRequest: angular.identity
        }).then(function(response) {
            alert("Dang ky thanh cong");
        }).catch(function(error) {
            console.log(error);
            alert("Dang ky that bai");
        });
    }

    $("#nextBtn").click(function () {
        if ($("#nextBtn").hasClass("submit")) {
            $scope.createArtist();
        }
    })

    $scope.selectFile = function (id) {
        $('#' + id).click();
        $('#' + id).change(function (event) {
            var file = event.target.files[0];
            if (file) {
                $scope.$apply(function () {
                    if (id === 'avatar') {
                        $scope.avatarFile = file;
                    }
                    if (id === 'background') {
                        $scope.backgroundFile = file;
                    }

                    var reader = new FileReader();
                    reader.onload = function (e) {
                        var imageDataUrl = e.target.result;
                        $('.' + id).attr('src', imageDataUrl);
                    };
                    reader.readAsDataURL(file);
                });
            }
        });
    };


})



