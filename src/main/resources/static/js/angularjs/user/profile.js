var host = "http://localhost:8080/api/";
app.controller('profileCtrl', function ($scope, $http,$location,$routeParams,graphqlService) {
    $scope.role = $routeParams.profile.toLowerCase();
    $scope.idProfile = $location.path().toLowerCase().includes($routeParams.id);

    $scope.author ={};
    $scope.profile = {};
    $scope.type = 0;

    $scope.findProfile = function(){
        const query =`{
            accountByUsername(username: `+idProfile+`) {
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
                artist{
                  artistId
                  artistName
                }
            }
        }`
        graphqlService.executeQuery(query).then(data => {
            $scope.profile = data.accountByUsername;
            if(role ==='artist'){
                $scope.type=2;
            }else if(role ==='podcaster'){
                $scope.type=3;
            }else{
                $scope.type=1;
            }
            $scope.checkFollowExist();
        })
    }
    

    $scope.follow = function () {
        var url = host + "v1/follow";
        var data = angular.copy($scope.author);
        data.email=$scope.profile.email;
        data.type=$scope.type;
        $http.post(url,data,{
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {

        }).catch(err => {

        });
    }
    $scope.findProfile();

    $scope.unfollow = function () {
        let url = host + "v1/follow?email="+$scope.profile.email+"&type="+$scope.type;
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
            params: { email: $scope.profile.email, type: $scope.type},
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
})