var apiDashboard = "http://localhost:8080/api/v1/admin/dashboard"
var cookieName = "token";

app.controller("dashboardController", function (graphqlService, $scope, $http) {

    $scope.top4Genre = {};
    $scope.countSongAndEpisode = {};
    $scope.countSubscriptions = {};
    $scope.top1Country = [];
    $scope.date =  Date.now();
    $scope.totalAccount = 0;
    $scope.top10Artist = [];
    $scope.top10Podcast = [];
    $scope.getCount = function () {
      $http.get(apiDashboard+"/count").then(function (response) {
        $scope.countSongAndEpisode = response.data.data;
     
      }).catch(error => {
        console.log(error);
      })
    }
    $scope.getCountSubscription = function () {
        $http.get(apiDashboard+"/subscription").then(function (response) {
          $scope.countSubscriptions = response.data.data;
       
        }).catch(error => {
          console.log(error);
        })
    }    
   
    $scope.getTop1Country = async function () {

      try {
        const top1 = await  $http.get(apiDashboard+"/top1-country");
        const countAccount = await $http.get(apiDashboard+"/count-account");
        $scope.top1Country = top1.data.data;
        $scope.totalAccount = countAccount.data.data
      } catch (error) {
        console.log(error);
      }

    } 

    $scope.chartLine = function(){
        const ctx = document.getElementById('chartline').getContext('2d');
        const myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['sad', 'asd','vcxv','hig'],
                datasets: [
                    {
                    label: 'Number',
                    data: [123,23,54,32],
                    backgroundColor: [
                        "#0074D9",
                    ],
                    borderColor: [
                        "#0074D9",
                    ],
                    // borderWidth: 1
                      },
                      {
                        label: 'thang',
                        data: [321,32,45,200],
                        backgroundColor: [
                           "#999900"
                        ],
                        borderColor: [
                            "#999900"
                        ],
                        // borderWidth: 1
                          }
                ]
            },
        });
    }
    $scope.chartLine();

    // START TOP GENRE
    $scope.getTop4Genre = async function () {
        $scope.countSongAndEpisode = {};
        try {
			const respTop4Genre = await $http.get(apiDashboard+"/top4-genre");
			$scope.top4Genre = respTop4Genre.data.data;
         
		   $scope.chartPieTop4Genre();
		} catch(error){
			console.log(error);
		}

    };

    $scope.chartPieTop4Genre = function(){
        const ctx = document.getElementById('myChartTopGenre').getContext('2d');
        const myChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: $scope.top4Genre.nameGenre,
                datasets: [{
                    label: 'Number',
                    data: $scope.top4Genre.top4Genre,
                    backgroundColor: [
                       " #ff4d4d","#cc3399","#ffff99", "#2ECC40"
                    ],
                    // borderColor: [
                    //     "#0074D9", "#FF4136", "#2ECC40","#ffff99"
                    // ],
                    // borderWidth: 1
                }]
            },
        });
    }
    // END TOP GENRE

    $scope.getTop10Artist = function(){
      $http.get(apiDashboard+"/top10-artist").then(function (response) {
        $scope.top10Artist = response.data.data;
     
      }).catch(error => {
        console.log(error);
      })
    };
    $scope.getTop10Podcast = function(){
      $http.get(apiDashboard+"/top10-podcast").then(function (response) {
        $scope.top10Podcast = response.data.data;
     
      }).catch(error => {
        console.log(error);
      })
    };

    $scope.getTop10Podcast();
    $scope.getTop10Artist();
    $scope.getTop1Country ();
     $scope.getTop4Genre();
     $scope.getCount();
     $scope.getCountSubscription();
})

