var app = angular.module('myApp', []);
var host = "http://localhost:8080/api";
app.controller('myCtrl', function ($scope, $http) {
    $scope.artist = {}

    $scope.create = function () {
        var url = host + "/v1/artist"
        var item = angular.copy($scope.artist)
        var formData = new FormData();
        formData.append('artist', item);
        formData.append('avatar', $scope.avatarFile);
        formData.append('background', $scope.backgroundFile);

        $http.post(url, formData, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            alert("Dang ky thanh cong");
        }).catch(error => {
            console.log(error)
            alert("Dang ky that bai");
        })
    }

    $("#nextBtn").click(function () {
        if ($("#nextBtn").hasClass("submit")) {
            $scope.create();
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
            console.log($scope.avatarFile)
            console.log($scope.backgroundFile)
        });
    };


})



