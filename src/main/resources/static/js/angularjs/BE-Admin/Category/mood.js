
var apiMood = "http://localhost:8080/api/v1/category/mood";
var cookieName = "token";
app.controller("moodController", function ($scope, $http, $cookies, $log) {

	$scope.form = {};
	$scope.items = [];
	$scope.page = [];
	$scope.currentPage = 0;
	$scope.reset = function () {
		$scope.form = {};
		$scope.key = null;
	}

	$scope.load_all = () => {
		$http.get(apiMood).then(resp => {
			$scope.items = resp.data.data.content;
			$scope.utilitiesPage.totalPages(resp.data.data.totalPages );
		}).catch(error => {
			console.log("Error", error)
		});

	}

	$scope.goToPage = function (pageNumber) {
		// Gửi yêu cầu đến máy chủ Spring Boot để lấy dữ liệu trang mới
		$http.get(apiMood + "?page=" + pageNumber)
			.then(resp =>{
				$scope.items = resp.data.data.content;			
				$scope.currentPage = pageNumber;	
			}).catch(error => {
				console.log("Error", error)
			});
	};

	$scope.utilitiesPage = {

		totalPages(totalPages) {
			for (var i = 0; i <= totalPages - 1; i++) {
				$scope.page.push(i);
			}
		},

		firstPage(){
			$scope.goToPage($scope.page[0]);
		},
		endPage(){
			$scope.goToPage($scope.page[$scope.page.length - 1]);
		}
	}



	$scope.create = function () {
		var item = angular.copy($scope.form);
		$http.post(apiMood, item, {
			headers: {
				'Authorization': 'Bearer ' + $cookies.get(cookieName)
			}
		}).then(resp => {
			$scope.load_all();
			$scope.reset();

		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.update = function (key) {
		var item = angular.copy($scope.form);
		var url = category + `/${$scope.form.moodid}`;
		$http.put(apiMood, item, {
			headers: {
				'Authorization': 'Bearer ' + $cookies.get(cookieName)
			}
		}).then(resp => {
			var index = $scope.items.findIndex(item => item.id == $scope.form.id);
			$scope.items[index] = resp.data;
			$scope.load_all();
		}).catch(error => {
			$log.error(error.data);
		});
	}

	$scope.delete = function (key) {
		var url = apiMood + `/${key}`;
		$http.delete(url, {
			headers: {
				'Authorization': 'Bearer ' + $cookies.get(cookieName)
			}
		}).then(resp => {
			var index = $scope.items.findIndex(item => item.moodid == key);
			$scope.items.splice(index, 1);
			$scope.load_all();
			$scope.reset();

		}).catch(error => {
			console.log("Error", error)
		});
	}


	$scope.edit = function (key) {
		let url = apiMood + "/" + key;
		$http.get(url).then(resp => {
			$scope.form = resp.data.data;
			$scope.key = key;
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.load_all();
})
