var host = "http://localhost:8080/api/";
app.controller('profileCtrl', function ($scope, $http,$location,$routeParams,graphqlService) {
    if($routeParams.profile){
        $scope.role = $routeParams.profile;
    }

    
    $scope.idProfile = $routeParams.id;
    $scope.isArtist = $location.path().includes('artist');

    $scope.author ={};
    $scope.profile = {};
    $scope.type = 0;

    $scope.findProfile = function(){
        if(!$scope.isArtist){
            var query =`{
                accountByUsername(username: "`+$scope.idProfile+`") {
                  email
                  username
                  birthday
                  gender
                  country
                  author {
                    authorId
                    role {
                      roleId
                    }
                  }
                  artist {
                    artistId
                    artistName
                  }
                  userType{
                    playlists{
                      playlistId
                    }
                  }
                }
              }`
        }else{
            var query =`{
                artistById(artistId: `+$scope.idProfile+`) {
                    artistId
                    artistName
                    dateOfBirth
                    fullName
                    placeOfBirth
                    bio
                    backgroundImage{
                        url
                    }
                    imagesProfile{
                        url
                    }
                    imagesGallery
                    account{
                        email
                    }
                }
            }`
        }
        
        graphqlService.executeQuery(query).then(data => {
            if($scope.isArtist){
                $scope.profile = data.artistById;
            }else{
                $scope.profile = data.accountByUsername;
            }
            
            if($scope.isArtist){
                $scope.type=2;
            }else if($scope.role ==='podcast'){
                $scope.type=3;
            }else{
                $scope.type=1;
            }
            $scope.checkFollowExist();
        })
    }
    $scope.findProfile();
    

    $scope.follow = function () {
        var url = host + "v1/follow";
        var data = angular.copy($scope.author);
        data.email=$scope.isArtist ? $scope.profile.account.email:$scope.profile.email;
        data.type=$scope.type;
        $http.post(url,data,{
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {

        }).catch(err => {

        });
    }
   

    $scope.unfollow = function () {
        const mail = $scope.isArtist ? $scope.profile.account.email : $scope.profile.email;
        let url = host + "v1/follow?email="+mail+"&type="+$scope.type;
        $http.delete(url,{
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            console.log("success")
        }).catch(err => {
            console.log(err)
        })
    }

    $scope.checkFollowExist =function(){
        let url = host +"v1/check-follow";
        $http.get(url,{
            params: { email: $scope.isArtist ? $scope.profile.account.email:$scope.profile.email, 
                      type: $scope.type},
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            if(resp.data.success == true){
                $('#btn-follow').addClass('unfollow');
                $('#btn-follow').text("Following");
            }
        })
    }

    $('#btn-follow').click(function () {
        if ($('#btn-follow').hasClass('unfollow')) {
            $('#btn-follow').removeClass('unfollow');
            $('#btn-follow').text("Follow")
            $scope.unfollow();
        } else {
            $('#btn-follow').addClass('unfollow');
            $('#btn-follow').text("Following")
            $scope.follow();
        }
    })

    $('#btn-artist-play').click(function () {
        $('#btn-artist-pause').attr('hidden', false);
        $('#btn-artist-play').attr('hidden', true);
        if ($scope.listAudioPlaylist[0].recordingId) {
            $scope.selectAudio($scope.listAudioPlaylist[0], 'song', $scope.listAudioPlaylist, 0);
        } else {
            $scope.selectAudio($scope.listAudioPlaylist[0], 'episode', $scope.listAudioPlaylist, 0);
        }
        play.click();
    })


    //pause
    $('#btn-artist-pause').click(function () {
        $('#btn-artist-pause').attr('hidden', true);
        $('#btn-artist-play').attr('hidden', false);
        resume.click();
    })

    $('#btn-artist-shuffle').click(function () {
        let icon = $('#btn-artist-shuffle').children();
        if ($('#btn-artist-shuffle').hasClass('isShuffle')) {
            $('#btn-artist-shuffle').removeClass("isShuffle");
            icon.eq(0).css('color', 'white', 'important');
        } else {
            $('#btn-artist-shuffle').addClass("isShuffle");
            icon.eq(0).css('color', 'green', 'important');
        }
        shuffle.click();
    })
})