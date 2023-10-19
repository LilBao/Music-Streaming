var apiNew = "http://localhost:8080/api/v1/admin/new";
var cookieName = "token";



app.controller("newController", function ($scope, $http, $cookies, $log, $timeout) {

    $scope.items = [];
    $scope.form = {};
    $scope.success = false;


    $scope.reset = function () {
        $scope.form = {};
        $scope.key = null;
    }


    $scope.loadAllLocationImage = () => {
        let url = apiNew + "/storage-for-image"

        $http.get(url).then(resp => {
            $scope.items = resp.data.data;

        }).catch(error => {
            console.log("Error", error)
        });
    }


    $scope.create = function () {

        var fileInput = document.getElementById('img');
        var file = fileInput.files[0];

        var item = angular.copy($scope.form);
        var formData = new FormData();
        formData.append("title", item.title);
        formData.append("content", item.content);
        formData.append("ImageLocation", item.ImageLocation);
        formData.append("img", file);

        var config = {
            headers: {
                'Authorization': 'Bearer ' + $cookies.get(cookieName),
                'Content-Type': undefined
            }
        };

        $http.post(apiNew, formData, config).then(function (response) {
            $scope.success = true
			$timeout( function(){
				$scope.closeAlert();
			}, 2000 );
            $scope.reset();
        }).catch(function (error) {
            console.error("Error", error);
        });
    }
   
	$scope.closeAlert = function(){
        $scope.success = false;
    }

    $scope.loadAllLocationImage();

  

 
  
})

