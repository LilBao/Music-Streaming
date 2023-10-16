var host = "http://localhost:8080/api";
app.controller('profileArtistCtrl', function ($scope, $http) {
    $scope.artist = {};

    //Get information artist - lấy thông tin artist đăng nhập
    $http.get(host + "/v1/profile", {
        headers: { 'Authorization': 'Bearer ' + getCookie('token') }
    }).then(resp => {
        $scope.artist = resp.data.data;
    }).catch(error => {
        console.log("Not found artist profile")
    })

    //update artist
    $scope.updateArtist = function (data) {
        let url = host + "/v1/artist";
        $http.put(url, data).then(resp => {
            console.log("success" + resp.data.data.bio)
        }).catch(error => {
            console.log("error")
        })
    }

    //Remove image - xóa ảnh đại diện hoặc hình nền
    $scope.removeImage = function (type) {
        let url = host + "/v1/artist";
        if (type = "avatar") {
            $scope.artist.imagesProfile = null;
        } else {
            $scope.artist.backgroundImage = null;
        }
        var data = angular.copy($scope.artist);
        $http.put(url, data).then(resp => {

        }).catch(error => {

        })
    }

    //Update image - sửa ảnh đại điện hoặc hình nền
    $scope.updateArtistImage = function () {
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
        }).then(function (response) {
            console.log("Update Successfully");
            $scope.backgroundFile = null;
            $scope.avatarFile = null;
        }).catch(function (error) {
            console.log(error);
            console.log("Update Failure");
        });
    }

    //Event update image background - sự kiện thay đổi hình ảnh nền
    $('#modified-background').click(function () {
        var icon = $(this).find("i");
        if ($('#modified-background').hasClass("save")) {
            icon.removeClass("bi-check-lg");
            icon.addClass("bi-pencil-fill");
            $('#modified-background').removeClass("save");
            $('#close-modified-background').remove();
            $scope.updateArtistImage();
        } else {
            icon.removeClass("bi-pencil-fill");
            icon.addClass("bi-check-lg");
            $('#modified-background').addClass("save");
            $('.change-bg').append('<button class="btn" id="close-modified-background"><i class="bi bi-x-lg text-white"></i></button>');
            $scope.selectFile('background');
        }
        $('#close-modified-background').click(function () {
            icon.removeClass("bi-check-lg");
            icon.addClass("bi-pencil-fill");
            $('#modified-background').removeClass("save");
            $('#close-modified-background').remove();
        })
    })

    //Type - phân loại vấn đề muốn xử lý về ảnh
    $scope.processImage = function (type) {
        $scope.typeModifiedPicture = type;
    }

    //Event remove image avatar - Xóa ảnh đại diện hiện tại
    $('#remove-current-image').click(function () {
        $scope.removeImage('avatar');
    })

    //Event remove background - xóa hình nền hiện tại
    $('#remove-current-background').click(function () {
        $scope.removeImage('background');
    })

    //Event update profile picture - update thay đổi hình đại diện của artist
    $('#save-image').click(function () {
        $scope.updateArtistImage();
    })

    //Event modifed bio
    $('#modified-bio').click(function () {
        var icon = $(this).find("i");
        if ($('#modified-bio').hasClass("save")) {
            icon.removeClass("bi-check-lg");
            icon.addClass("bi-pencil-fill");
            $('#bio').attr('readonly', true);
            $('#modified-bio').removeClass("save");
            let data = angular.copy($scope.artist)
            $scope.updateArtist(data);
        } else {
            icon.removeClass("bi-pencil-fill");
            icon.addClass("bi-check-lg");
            $('#bio').removeAttr('readonly');
            $('#modified-bio').addClass("save");
        }
    })

    //upload Image gallery
    $scope.uploadGallery = function () {
        let url = host + "/v1/artist-image";
        var formData = new FormData();
        for (var i = 0; i < $scope.listGallery.length; i++) {
            formData.append('gallery', $scope.listGallery[i]);
        }
        $http.put(url,formData,{
            headers: { 
                'Content-Type': undefined,
                'Authorization': 'Bearer ' + getCookie('token')
            },  
            transformRequest: angular.identity
        }).then(resp=>{
            console.log("success");
        }).catch(error => {
            console.log("error");
        })
    }

    $('#upload-gallery').click(function () {
        $scope.selectMultipleFile('gallery')
    })
    //Event Select file
    $scope.selectFile = function (id) {
        $('#' + id).click();
        $('#' + id).change(function (event) {
            var file = null;
            file = event.target.files[0];
            if (file) {
                $scope.$apply(function () {
                    if (id === 'avatars') {
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


    $scope.selectMultipleFile = function (id) {
        $('#' + id).click();
        $('#' + id).change(function (event) {
            $scope.listGallery = [];
            for (var i = 0; i < event.target.files.length; i++) {
                if (event.target.files[i]) {
                    $scope.listGallery.push(event.target.files[i]);
                }
            }
        })
    };
})