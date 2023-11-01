
var apiAccount = "http://localhost:8080/api/v1/admin/account";
var apiRole = "http://localhost:8080/api/v1/admin/role";
var cookieName = "token";
app.controller("tableAccountController", function ($scope, $http, $cookies, $log) {

	$scope.formUser = {};
	$scope.items = [];
	$scope.page = [];
	$scope.currentPage = 0;
	$scope.success = false;
	$scope.author = 'USER';
	$scope.reset = function () {
		$scope.formUser = {};
		$scope.key = null;
	}

	$scope.load_all_User = (role) => {
		$http.get(apiAccount,{params:{role}}).then(resp => {
			$scope.items = resp.data.data.content;
			$scope.utilitiesPage.totalPages(resp.data.data.totalPages);
			$scope.author  = role;
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.goToPage = function (pageNumber) {

		$http.get(apiAccount, 
			{
			params: { page: pageNumber, role : $scope.author}
		    }
		)
			.then(resp => {
				$scope.items = resp.data.data.content;
				$scope.currentPage = pageNumber;
			}).catch(error => {
				console.log("Error", error)
			});
	};

	$scope.utilitiesPage = {

		totalPages(totalPages) {
			$scope.page.length = 0;
			for (var i = 0; i <= totalPages - 1 ; i++) {
				$scope.page.push(i);
			}
		},

		firstPage() {
			$scope.goToPage($scope.page[0]);
		},
		endPage() {
			$scope.goToPage($scope.page[$scope.page.length - 1]);
		}
	}

	$scope.profileById = (idUser) =>{
		$http.get(apiAccount+ `/${idUser}` ).then(resp => {
			$scope.formUser = resp.data.data
			
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.load_all_User('USER');
})
