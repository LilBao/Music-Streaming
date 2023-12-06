var cookieName = "token";
var apiAds = "http://localhost:8080/api/v1/admin/advertisement";

app.controller( "advertisementController", function (graphqlService, $scope, $http,$cookies,jwtHelper) {
 
 
    $scope.allAdvertisementRunAndComplete = [];
    $scope.allAdvertisementPendingAndReject = [];
    $scope.statisticsON = {};
    $scope.resultsADS = {};
    $scope.numberAds = [];

    $scope.getAllAdvertisementRunningAndCompleted = async function () {
      
      try {
        const resp = await  $http .get(apiAds + "/running-completed");
        $scope.allAdvertisementRunAndComplete = resp.data.data;
      } catch (error) {
        console.log("Error", error);
      }
    };


    $scope.getAllAdvertisementPendingAndReject = async function () {
      try {
        const response = await $http.get(apiAds + "/pending-reject");
     
        // console.log("Pending", response);
        // for (const element of $scope.pendingAndReject) {
        //   const numberOfAds = await $scope.getNumberOfAdsForAccount(element.account.email);
        //   element.numberOfAds = numberOfAds;
        // }
    
        $scope.allAdvertisementPendingAndReject = response.data.data;
        console.log("Pending", $scope.allAdvertisementPendingAndReject)
      } catch (error) {
        console.log("Error", error);
      }
    };
    
    

    $scope.getNumberOfAdsForAccount = function (idAccount) {
      return new Promise((resolve, reject) => {
        var query = `{
          findAccountByEmail(id: "${idAccount}") {
            email
            advertisement {
              adId
              status
            }
          }
        }`
    
        graphqlService.executeQuery(query)
          .then(resp => {
            resolve(resp.findAccountByEmail);
          })
          .catch(error => {
            console.log("Error", error);
            reject(error);
          });
      });
    };


    $scope.statisticsDetail = function (id) {
       $http.get(apiAds+`/statistics/${id}`).then(resp => {
        $scope.statisticsON = resp.data.data;
        $scope.statisticsDetailResults(id);
       })
    };

    $scope.statisticsDetailResults = function (id) {
      $http.get(apiAds+`/statistics/${id}/results`).then(resp => {
       $scope.resultsADS = resp.data.data;
      })
   };

   $scope.setStatus= function (id,status) {
    // run -> status = 2;
     // reject -> status = 4;
    let config = {
      headers: {
        "Authorization": "Bearer " + $cookies.get("token")
      }
    };
   
  
    $http.put(apiAds + `/${id}/status?status=${status}`, config).then(resp => {
      $scope.getAllAdvertisementPendingAndReject();
      $scope.getAllAdvertisementRunningAndCompleted();
      showStickyNotification("successful", "success", 2000); 
    }).catch(err => {
      showStickyNotification("Error", "danger", 2000);
    });
  };

  $scope.statusShow = false;

  $scope.getRoleAccount = function () {

    var token = $cookies.get("token");
    var decodeToken = jwtHelper.decodeToken(token);
    decodeToken.role.forEach(element => {
      if( element.authority === "MANAGER")
       return $scope.statusShow = true;
    });
    
  };


    $scope.getRoleAccount();

    $scope.getAllAdvertisementRunningAndCompleted();
    $scope.getAllAdvertisementPendingAndReject();
  }
);
