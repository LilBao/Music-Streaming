
var apiAccount = "http://localhost:8080/api/v1/admin/account";
var apiRole = "http://localhost:8080/api/v1/admin/role";
var api = "http://localhost:8080/api/v1/admin/statistics";
var cookieName = "token";
app.controller("tableAccountController", function (graphqlService, $scope, $http) {

	$scope.formUser = {};
	$scope.items = [];
	$scope.itemsAccountByRole =[];
	$scope.page = [];
	$scope.currentPage = 0;
	$scope.success = false;
	$scope.author = 'USER';
	$scope.countReport;
	$scope.countWishlist;
	$scope.year = [];
	
	$scope.reset = function () {
		$scope.formUser = {};
		$scope.key = null;
	}



	$scope.load_all_AccountByRole = (role) => {
		const queryAccountByRole = `{
			getAllAccountByRole(role: "${role}") {
			  email
			  password
			  username
			  birthday
			  gender
			  country
			  isVerify
			  isBlocked
			  image{
				url
			  }
			  artist {
				imagesProfile {
				  url
				}
			  }
			  userType{
				nameType
			  }
			}
		  }`;

		graphqlService.executeQuery(queryAccountByRole).then(data => {

			$scope.itemsAccountByRole = data.getAllAccountByRole;
			
		}).catch(error => {
			console.log(error);
		});
			
	}

	$scope.getFollowingAccount = (id) => {
		const queryAccountByRole = `{
			myListFollow(email: "${id}") {
			  followerId
			}
		  }`;
		graphqlService.executeQuery(queryAccountByRole).then(data => {

			$scope.following = data.myListFollow;
			
		}).catch(error => {
			console.log(error);
		});
			
	}


	$scope.getFollowAccount = (id) => {
		const queryAccountByRole = `{
			findYourFollow(roleId: 1, email: "${id}") {
			  followerId
			}
		  }`;
		graphqlService.executeQuery(queryAccountByRole).then(data => {

			$scope.follow = data.findYourFollow;
			
		}).catch(error => {
			console.log(error);
		});
			
	}


	$scope.profileById = (idUser) => {
		$http.get(apiAccount + `/${idUser}`).then(resp => {
			$scope.formUser = resp.data.data;
			console.log(resp.data)
			$scope.countRp(idUser);
			$scope.countWl(idUser);
			$scope.getFollowAccount(idUser);
			$scope.getFollowingAccount(idUser);
		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.countRp = (idUser) => {
		$http.get(apiAccount + `/${idUser}` + "/report").then(resp => {
			$scope.countReport = resp.data.data

		}).catch(error => {
			console.log("Error", error)
		});
	}

	$scope.countWl = (idUser) => {
		$http.get(apiAccount + `/${idUser}` + "/wishlist").then(resp => {
			$scope.countWishlist = resp.data.data
		}).catch(error => {
			console.log("Error", error)
		});
	}

	
	$scope.getStatisticAccount = async function() {
		$scope.countAccount = [];
		try {
			const resp1 = await $http.get(api+"/account");
		
			resp1.data.data.forEach(element => {
				   $scope.year.push(element.year);
				   $scope.countAccount.push(element.count);
			   });
			  const resp2 = await $http.get(api+"/account-role");
			$scope.countAccountByRole = resp2.data.data;
			$scope.accountRoleStatistics();
		    $scope.accountStatistics();
			
		} catch(error){
			console.log(error);
		}

	};
	
	$scope.accountStatistics = function(){
        const ctx = document.getElementById('myChart').getContext('2d');
		console.log($scope.countAccount)
        const myChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: $scope.year,
                datasets: [{
                    label: 'Year',
                    data:  $scope.countAccount,
                    backgroundColor: [
                        "#99ccff"
                    ],    
                }]
            },
        });
     }
	 
	 $scope.accountRoleStatistics = function(){
        const ctx = document.getElementById('accountRole').getContext('2d');
        const myChart = new Chart(ctx, {
            type: 'polarArea',
            data: {
                labels: ['User', 'Artist', 'Podcast','Staff'],
                datasets: [{
                    label: 'Number',
                    data:  $scope.countAccountByRole,
                    backgroundColor: [
                        "#0074D9", "#FF4136", "#2ECC40","#ffff99"
                    ],
         
                }]
            },
        });
     }

	 $scope.chartMapWorld = function(){
		var chart = JSC.chart('chartDiv', { 
			debug: true, 
			type: 'map', 
			legend_visible: false, 
			mapping_projection: false,

			series: [ 
			  { 
				
				map: 'world', 
				palette: JSC.colorToPalette( 
				  'rgb(251, 204, 204)', 
				  { 
					hue: 1, 
					saturation: 0.4, 
					lightness: 0.3 
				  }, 
				  200, 
				  1 
				), 
				opacity: 0.8, 
				defaultPoint: {
					 tooltip: '%name <b>10%</b>',
				} 
			  },
			  
			] 
		  }); 
	 }



	 $scope.getStatisticAccount();
	 $scope.chartMapWorld();
	
	$scope.load_all_AccountByRole('USER');
	
})