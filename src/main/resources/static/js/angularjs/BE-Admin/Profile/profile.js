
var apiAdminInfo = "http://localhost:8080/api/v1/admin/profile";
var cookieName = "token";

app.controller("navbarAdmin", function (graphqlService,$scope, $http, $cookies ) {


    $scope.infoAdmin = {};
	$scope.listArtistIsVerify = [];
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


	
    $scope.getAllArtistIsVerify = async function() {
        try {
            let query = `
			{
				getAllIsVerifyArtist {
				  artistId
				  artistName
				  dateStarted
				}
			  }
                    
            `;
    
            const resp = await graphqlService.executeQuery(query);
			$scope.listArtistIsVerify = resp.getAllIsVerifyArtist;
			
        } catch (error) {
            console.error("Error fetching artist:", error);
            throw error; // Rethrow the error to be handled by the calling code
        }
    };  


	$scope.getAllArtistIsVerify ();
    $scope.getInfoAdmin();
})