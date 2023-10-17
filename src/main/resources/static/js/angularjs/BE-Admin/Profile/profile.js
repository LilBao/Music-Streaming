
var apiAdminInfo = "http://localhost:8080/api/v1/admin/profile";
var cookieName = "token";

app.controller("navbarAdmin", function ($scope, $http, $cookies,$log ) {


    $scope.infoAdmin = {};

    $scope.getInfoAdmin = () => {
		$http.get(apiAdminInfo,{
			headers: {
				'Authorization': 'Bearer ' + $cookies.get(cookieName)
			}
		}).then(resp => {
			$scope.infoAdmin = resp.data.data;			
		}).catch(error => {
			console.log("Error", error)
		});
	}

    $scope.getInfoAdmin();
})