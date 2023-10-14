
var category = "/instrument";
var cookieName = "token";
app.controller("instrumentController", function ($scope, $http, $cookies, apiURL,$log) {

	$scope.form = {};
	$scope.items = [];
	$scope.reset = function () {
		$scope.form = {};
		$scope.key = null;
	}

	$scope.load_all = () => {
		$http.get(apiURL + category).then(resp => {
			$scope.items = resp.data.data.content;
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.create = function () {
		var item = angular.copy($scope.form);
		$http.post(apiURL + category, item, {
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

	$scope.update = function() {
		var item = angular.copy($scope.form);
		var url = category +`/${$scope.form.instrumentId}`;
		$http.put(apiURL + url, item, {
			headers: {
				'Authorization': 'Bearer ' + $cookies.get(cookieName)
			}
		}).then(resp => {
			var index = $scope.items.findIndex(item => item.id == $scope.form.instrumentId);
			$scope.items[index] = resp.data;
			$scope.load_all();
		}).catch(error => {
			$log.error(error.data);
		});
	}

	$scope.delete = function(key) {
		var url = apiURL+ category +`/${key}`;
		$http.delete(url,{
			headers: {
				'Authorization': 'Bearer ' + $cookies.get(cookieName)
			}
		}).then(resp => {
			var index = $scope.items.findIndex(item => item.instrumentId == key);
			$scope.items.splice(index, 1);
			$scope.load_all();
			$scope.reset();
			
		}).catch(error => {
			console.log("Error", error)
		});
	}


	$scope.edit = function (key) {
		let url = apiURL + category + "/" + key;
		$http.get(url).then(resp => {
			$scope.form = resp.data.data;
			$scope.key = key;
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.load_all();
})
