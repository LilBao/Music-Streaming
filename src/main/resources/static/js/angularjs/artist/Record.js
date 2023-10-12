var app = angular.module('myApp',[])
var host = "http://localhost:8080/api";
app.controller('recordCtrl',function($scope,$http){
    $scope.record ={}

    //Call API => create Record
    $('#create-record').click(function(){
        $scope.checkbox();
        var url = host + "/v1/record"
        var data = new FormData();
        data.append('recordingName',$scope.record.name);
        data.append('studio',$scope.record.studio);
        data.append('produce',$scope.record.proceduce);
        data.append('idMv',$scope.record.idMV);
        data.append('mood',$scope.moods);
        data.append('songStyle',$scope.styles);
        data.append('culture',$scope.cultures);
        data.append('instrument',$scope.instruments);
        data.append('versions',$scope.record.version);
        data.append('fileRecord',$scope.recordFile);
        data.append('fileLyrics',$scope.lyricsFile);
        $http.post(url,data,{
            headers: { 
                'Content-Type': undefined,
                'Authorization': 'Bearer ' + getCookie('token') 
            }, 
            transformRequest: angular.identity
        }).then(resp => {
            console.log("success")
        }).catch(error =>{
            console.log(data.get('fileRecord'))
            console.log(data.get('fileLyrics'))
        })

    })
    
    //Get File Audio and File lyrics
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
        });
    };

    //get Value checbox
    $scope.checkbox = function(){
        $scope.cultures="";
        $scope.moods="";
        $scope.styles="";
        $scope.instruments="";
        const selectedValues = [];
        $('input[name="culture"]:checked').each(function() {
            $scope.cultures+=" "+$(this).val();
        });
        $('input[name="mood"]:checked').each(function() {
            $scope.moods+=" "+$(this).val();
        });
        $('input[name="style"]:checked').each(function() {
            $scope.styles+=" "+$(this).val();
        });
        $('input[name="intrument"]:checked').each(function() {
            $scope.instruments+=" "+$(this).val();
        });
        $('input[name="genre"]:checked').each(function() {
            selectedValues.push($(this).val());
        });
        $scope.genre=selectedValues;
    }
})