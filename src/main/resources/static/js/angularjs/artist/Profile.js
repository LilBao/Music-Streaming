var host = "http://localhost:8080/api";
app.controller('profileArtistCtrl',function($scope,$http){
    $scope.artist = {};
    
    $http.get(host+"/v1/profile",{
        headers: { 'Authorization': 'Bearer ' + getCookie('token') }
    }).then(resp => {
        $scope.artist=resp.data.data;
    }).catch(error => {
        console.log("Not found artist profile")
    })

    $scope.updateArtist = function() {
        var url = host + "/v1/artist-image";
        var formData = new FormData();
        formData.append('avatar', $scope.avatarFile);
        formData.append('background', $scope.backgroundFile);
        $http.put(url, formData, {
            headers: {
                'Content-Type': undefined,
                'Authorization': 'Bearer ' + getCookie('token')
            },
            transformRequest: angular.identity
        }).then(function(response) {
            alert("Update Successfully");
            $scope.backgroundFile=null;
            $scope.avatarFile=null;
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
            console.log(file)
        });
    };
})