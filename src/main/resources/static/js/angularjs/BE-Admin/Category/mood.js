
var category = "/mood";

app.controller("moodController", function($scope, $http, $cookies,apiURL) {

    $scope.form = {};
	$scope.items = [];
	$scope.reset = function() {
		$scope.form = {};
		$scope.key = null;
	}

    $scope.load_all = () =>{
        $http.get(apiURL+category).then(resp => {
			$scope.items = resp.data.data.content;
		}).catch(error => {

			console.log("Error", error)
		});
    }

    $scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(apiURL+category, item).then(resp => {
			$scope.items.push(item);
			$scope.load_all();
			$scope.reset();
			console.log("Success", resp)
		}).catch(error => {
			console.log("Error", error)
		});
	}

    $scope.edit = function(key) {
        let url = `apiURL+category+${key}`;
		$http.get(url).then(resp => {
			$scope.form = resp.data.data.content;
			$scope.key = key;
			console.log("Success", resp)
		}).catch(error => {
			console.log("Error", error)
		});
	}


    $scope.load_all();
})
