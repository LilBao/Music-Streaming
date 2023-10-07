var app = angular.module('myApp',[])
var host = "";
app.controller('recordCtrl',function($scope,$http){
    $scope.record ={}
    $('#create-record').click(function(){
        var url = host + "/api/v1/record"
        var data = new FormData();
        data.append('recordingName',$scope.record.name);
        data.append('audioFileUrl',$scope.recordFile);
        data.append('lyricsUrl',$scope.lyricsFile);
        data.append('studio',$scope.record.studio);
        data.append('produce',$scope.record.proceduce);
        $http.post(url,data,{
            headers: { 'Content-Type': undefined }, 
            transformRequest: angular.identity
        }).then(resp => {

        }).catch(error =>{

        })
    })
    
    $scope.selectFile = function (id) {
        $('#' + id).change(function (event) {
            var file = event.target.files[0];
            if (file) {
                $scope.$apply(function () {
                    if (id === 'record') {
                        $scope.recordFile = file;
                    }
                    if (id === 'lyrics') {
                        $scope.lyricsFile = file;
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

    $scope.listRecords=[]
    $http.get(host+"authorities").then(resp => {
        $scope.records = resp.data;
    })
   
    $scope.indexOf = function(id){
        return $scope.db.findIndex(item => item.id==id)
    }

    $scope.updateRole = function(id){
        var index = $scope.indexOf(id);
        if(index>=0){
            $scope.db.authorities.splice(index,1);
        }else{
            $scope.db.authorities.push(resp.data);
        }
    }
})