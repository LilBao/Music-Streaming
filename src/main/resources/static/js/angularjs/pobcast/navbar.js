var host = "http://localhost:8080/api";
app.controller('navCtrl',function($scope,$http){
    $scope.listPodcast = [];

    $scope.findMyListPodcast = function(){
        let url = host + "/v1/my-podcast";
        $http.get(url,{
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            $scope.listPodcast = resp.data.data;
        }).catch(error =>{
            console.log(error);
        })
    }

    $scope.me = function () {
        $http.get(host + "/v1/account", {
            headers: { 'Authorization': 'Bearer ' + getCookie('token') }
        }).then(resp => {
            $scope.account = resp.data.data;
        }).catch(error => {
            console.log("Not found artist profile")
        })
    }
    $scope.me();

    $scope.switchPodcast = function(id){
        let url = host + "/v1/podcast/"+id;
        $http.get(url).then(resp => {
            localStorage.setItem('podcast',JSON.stringify(resp.data.data));
            location.reload();
        }).catch(error =>{
        })
    }
    $scope.findMyListPodcast();
})