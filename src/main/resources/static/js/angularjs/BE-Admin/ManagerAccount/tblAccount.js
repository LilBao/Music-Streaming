
var apiAccount = "http://localhost:8080/api/v1/admin/account";
var apiRole = "http://localhost:8080/api/v1/admin/role";
var cookieName = "token";
app.controller("tableAccountController", function ($scope, $http, $cookies,$log , $timeout) {

	$scope.form = {};
	$scope.items = [];
    $scope.itemRole = [];
	$scope.page = [];
	$scope.currentPage = 0;
	$scope.success = false;

	$scope.reset = function () {
		$scope.form = {};
		$scope.key = null;
	}

    $scope.load_all_Role = function(){
        $http.get(apiRole).then(resp => {
			$scope.itemRole = resp.data.data;			
		}).catch(error => {
			console.log("Error", error)
		});
    }
 

	$scope.load_all_User = (role) => {
		$http.get(apiAccount,{params:{role}}).then(resp => {
			$scope.items = resp.data.data;
		//	$scope.utilitiesPage.totalPages(resp.data.data.totalPages);
			$scope.author  = role;
		}).catch(error => {
			console.log("Error", error)
		});
	}

	// $scope.goToPage = function (pageNumber) {

	// 	$http.get(apiAccount, 
	// 		{
	// 		params: { page: pageNumber, role : $scope.author}
	// 	    }
	// 	)
	// 		.then(resp => {
	// 			$scope.items = resp.data.data.content;
	// 			$scope.currentPage = pageNumber;
	// 		}).catch(error => {
	// 			console.log("Error", error)
	// 		});
	// };
	$scope.goToPage = function (pageNumber) {
		// Gửi yêu cầu đến máy chủ Spring Boot để lấy dữ liệu trang mới
		$http.get(apiAccount  + "?page=" + pageNumber)
			.then(resp =>{
				$scope.items = resp.data.data.content;			
				$scope.currentPage = pageNumber;	
			}).catch(error => {
				console.log("Error", error)
			});
	};

	// $scope.utilitiesPage = {

	// 	totalPages(totalPages) {
	// 		$scope.page.length = 0;
	// 		for (var i = 0; i <= totalPages - 1 ; i++) {
	// 			$scope.page.push(i);
	// 		}
	// 	},

	// 	firstPage() {
	// 		$scope.goToPage($scope.page[0]);
	// 	},
	// 	endPage() {
	// 		$scope.goToPage($scope.page[$scope.page.length - 1]);
	// 	}
	// }

	// 	totalPages(totalPages) {
	// 		for (var i = 0; i <= totalPages - 1; i++) {
	// 			$scope.page.push(i);
	// 		}
	// 	},

	// 	firstPage(){
	// 		$scope.goToPage($scope.page[0]);
	// 	},
	// 	endPage(){
	// 		$scope.goToPage($scope.page[$scope.page.length - 1]);
	// 	}
	// }



    $scope.load_all_Role();
	$scope.load_all_User('USER');
})
