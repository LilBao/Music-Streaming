
var apiAccount = "http://localhost:8080/api/v1/admin/account";
var apiRole = "http://localhost:8080/api/v1/admin/role";
var cookieName = "token";
app.controller("tableAccountController", function ($scope, $http) {

	$scope.formUser = {};
	$scope.items = [];
	$scope.page = [];
	$scope.currentPage = 0;
	$scope.success = false;
	$scope.author = 'USER';
	$scope.countReport;
	$scope.countWishlist;

	$scope.reset = function () {
		$scope.formUser = {};
		$scope.key = null;
	}

	$scope.load_all_User = (role) => {
		$http.get(apiAccount,{params:{role}}).then(resp => {
			$scope.items = resp.data.data;
			console.log($scope.items)
	
			$scope.author  = role;
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.profileById = (idUser) =>{
		$http.get(apiAccount+ `/${idUser}` ).then(resp => {
			$scope.formUser = resp.data.data;
			console.log(resp.data)
			$scope.countRp(idUser);
			$scope.countWl(idUser);
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.countRp = (idUser) =>{
		$http.get(apiAccount+ `/${idUser}`+"/report" ).then(resp => {
			$scope.countReport = resp.data.data
		
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.countWl = (idUser) =>{
		$http.get(apiAccount+ `/${idUser}`+"/wishlist" ).then(resp => {
			$scope.countWishlist = resp.data.data		
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.load_all_User('USER');
})