
var aipCountry = "http://localhost:8080/api/v1/admin/category/country";
var cookieName = "token";
app.controller("countryController", function ($scope, $http, $cookies,$log , $timeout) {

	$scope.form = {};
	$scope.itemCountries = [];
	$scope.page = [];
	$scope.currentPage = 0;
	$scope.success = false;

	$scope.reset = function () {
		$scope.form = {};
		$scope.key = null;
	}

	$scope.load_all = () => {
		$http.get(aipCountry).then(resp => {
			$scope.itemCountries = resp.data.data.content;
			$scope.utilitiesPage.totalPages(resp.data.data.totalPages);
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.goToPage = function (pageNumber) {
		// Gửi yêu cầu đến máy chủ Spring Boot để lấy dữ liệu trang mới
		$http.get(aipCountry + "?page=" + pageNumber)
			.then(resp =>{
				$scope.itemCountries = resp.data.data.content;			
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
		$http.post(aipCountry, item, {
			headers: {
				'Authorization': 'Bearer ' + $cookies.get(cookieName)
			}
		}).then(resp => {
			$scope.load_all();
			$scope.reset();
			$scope.success = true
			$timeout( function(){
				$scope.closeAlert();
			}, 2000 );
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.closeAlert = function(){
        $scope.success = false;
    }

	$scope.update = function() {
		var item = angular.copy($scope.form);
		var url = aipCountry +`/${$scope.form.id}`;
		$http.put( url, item, {
			headers: {
				'Authorization': 'Bearer ' + $cookies.get(cookieName)
			}
		}).then(resp => {
			var index = $scope.itemCountries.findIndex(item => item.id == $scope.form.id);
			$scope.itemCountries[index] = resp.data;
			$scope.load_all();
			$scope.success = true
			$timeout( function(){
				$scope.closeAlert();
			}, 2000 );
		}).catch(error => {
			$log.error(error.data);
		});
	}

	$scope.delete = function(key) {
		var url = aipCountry +`/${key}`;
		$http.delete(url,{
			headers: {
				'Authorization': 'Bearer ' + $cookies.get(cookieName)
			}
		}).then(resp => {
			var index = $scope.itemCountries.findIndex(item => item.id == key);
			$scope.itemCountries.splice(index, 1);
			$scope.load_all();
			$scope.reset();
			$scope.success = true
			$timeout( function(){
				$scope.closeAlert();
			}, 2000 );
		}).catch(error => {
			console.log("Error", error);
		});
	}


	$scope.edit = function (key) {
		let url = aipCountry  + "/" + key;
		$http.get(url).then(resp => {
			$scope.form = resp.data.data;
			$scope.key = key;
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.exportToExcel = function () {
		$http.get(aipCountry + '/export-excel', { responseType: 'arraybuffer' })
			.then(function (response) {
				var blob = new Blob([response.data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
				var url = URL.createObjectURL(blob);
				var a = document.createElement('a');
				console.log(a)
				a.href = url;
				a.download = 'country.xlsx';
				document.body.appendChild(a);
				a.click();
				window.URL.revokeObjectURL(url);
			}, function (error) {
				console.error('Error exporting to Excel', error);
			});
	};

	$scope.load_all();
})
