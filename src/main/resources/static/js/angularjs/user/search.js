var host = "http://localhost:8080/api/";

app.controller('SearchController', function ($scope, $http, $cookies, $window) {
  // data search bar
  $scope.searchKeyword = '';
  $scope.data = {};
  $scope.dataArt = {};
  $scope.dataAlbum = {};
  $scope.dataS = {};
  $scope.dataPl = {};
  $scope.dataP = {};
  $scope.dataE = {};
  $scope.dataProfile = {};
  $scope.dataGr = {};
  $scope.interface1 = true;
  $scope.interface2 = false;
  $scope.hiden = false;
  $scope.profile = {};

  $scope.$watch('searchKeyword', function (keyword) {
    if (keyword) {
      $('.has-act')[0].click();
      $scope.interface1 = false;
      $scope.interface2 = true;
      $http.get(host + 'v1/search/' + keyword)
        .then(function (resp) {
          $scope.data = resp.data.data;

          const uniqueArt = new Set();
          const uniqueAlbum = new Set();
          const uniqueS = new Set();
          const uniquePl = new Set();
          const uniqueP = new Set();
          const uniqueE = new Set();
          const uniqueProfile = new Set();
          const uniqueGr = new Set();

          $scope.data.forEach(element => {
            if (element[3] !== null) {
              uniqueArt.add(element[3]);
              $scope.dataArt[element[3]] = element;
            }

            if (element[6] !== null) {
              uniqueAlbum.add(element[6]);
              $scope.dataAlbum[element[6]] = element;
            }

            if (element[9] !== null) {
              uniqueS.add(element[9]);
              $scope.dataS[element[9]] = element;
            }

            if (element[19] !== null) {
              uniquePl.add(element[19]);
              $scope.dataPl[element[19]] = element;
            }

            if (element[13] !== null) {
              uniqueP.add(element[13]);
              $scope.dataP[element[13]] = element;
            }

            if (element[16] !== null) {
              uniqueE.add(element[16]);
              $scope.dataE[element[16]] = element;
            }

            if (element[0] !== null) {
              uniqueProfile.add(element[0]);
              $scope.dataProfile[element[0]] = element;
            }

            if (element[22] !== null) {
              uniqueGr.add(element[22]);
              $scope.dataGr[element[22]] = element;
            }
          });
          console.log($scope.dataArt);
        })
        .catch(function (error) {
          console.error('Error searching');
        });

    } else {
      $scope.interface1 = true;
      $scope.interface2 = false;

      $('.has-act').each(function () {
        $(this).removeClass('active')
      });

      $scope.data = {};
      $scope.dataArt = {};
      $scope.dataAlbum = {};
      $scope.dataS = {};
      $scope.dataPl = {};
      $scope.dataP = {};
      $scope.dataE = {};
      $scope.dataProfile = {};
      $scope.dataGr = {};
    }
  });


  // search browser
  $scope.listPodcast = [];

  $scope.getListPodcast = function () {
    $http.get(host + 'v1/podcast')
      .then(function (resp) {
        $scope.listPodcast = resp.data.data;
      })
      .catch(function (error) {
        console.error("Error fetching list podcast data:", error);
      });
  };
  $scope.getListPodcast();

  // btn back and forward
  $("#back").on("click", function () {
    history.back();
  });

  $("#forward").on("click", function () {
    history.forward();
  });

  $http.get(host + "v1/account", {
    headers: { 'Authorization': 'Bearer ' + $cookies.get('token') }
  }).then(resp => {
    $scope.hiden = true;
    $scope.profile = resp.data.data;
    console.log($scope.profile);
  }).catch(error => {
    console.log(error)
  })

  $scope.logout = function () {
    var now = new Date();
    now.setUTCFullYear(1970);
    now.setUTCMonth(0);
    now.setUTCDate(1);
    now.setUTCHours(0);
    now.setUTCMinutes(0);
    now.setUTCSeconds(0);

    $cookies.put('token', '', { expires: now, path: '/' })
    $window.location.href = 'http://127.0.0.1:5500/src/main/resources/templates/user/login.html';
  }

  $scope.signin = function () {
    $window.location.href = 'http://127.0.0.1:5500/src/main/resources/templates/user/login.html';
  }

  $scope.account = function () {
    $window.location.href = 'http://127.0.0.1:5500/src/main/resources/templates/user/account.html';
  }

  $scope.redirectToProfile = function (username) {
    var newUrl = '#!/profile/user/' + username;
    $window.location.href = newUrl;
  };
});