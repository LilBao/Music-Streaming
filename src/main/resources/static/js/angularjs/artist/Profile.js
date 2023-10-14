var app = angular.module('myApp',[]);
var host = "http://localhost:8080/api";
app.controller('profileArtistCtrl',function($scope,$http){
    $scope.artist = {};
    
    $http.get(host+"/v1/profile",{
        headers: { 'Authorization': 'Bearer ' + getCookie('token') }
    }).then(resp => {
        $scope.artist=resp.data.data;
        console.log($scope.artist)
    }).catch(error => {
        console.log("Not found artist profile")
    })

    $scope.updateArtist = function() {
        var url = host + "/v1/artist";
        var formData = new FormData();
        formData.append('artist', $scope.artist); 
        formData.append('avatar', $scope.avatarFile);
        formData.append('background', $scope.backgroundFile);
        $http({
            method: 'POST',
            url: url,
            headers: { 
                'Content-Type': undefined,
                'Authorization': 'Bearer ' + getCookie('token')
            }, 
            data: formData,
            transformRequest: angular.identity
        }).then(function(response) {
            alert("Update Successfully");
        }).catch(function(error) {
            console.log(error);
            alert("Update Failure");
        });
    }

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