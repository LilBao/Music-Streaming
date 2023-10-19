
var apiSlidePosition = "http://localhost:8080/api/v1/admin/display-slide";
var cookieName = "token";

app.controller("displaySlideController", function ($scope, $http, $cookies,$log ) {



    $scope.SlidePosition = [];


    $scope.getInfoAdmin = () => {
		$http.get(apiSlidePosition,{
			headers: {
				'Authorization': 'Bearer ' + $cookies.get(cookieName)
			}
		}).then(resp => {
			$scope.SlidePosition = resp.data.data;		
            console.log("Error", resp)	
		}).catch(error => {
			console.log("Error", error)
		});
	}

    $scope.getInfoAdmin();
	
	$scope.cl= ()=>{
		$('#file').click();
	}
})