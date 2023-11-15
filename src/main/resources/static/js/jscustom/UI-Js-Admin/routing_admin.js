var app = angular.module("myApp", ["ngRoute","ngCookies","ngMessages","angularUtils.directives.dirPagination"]);
app.config(function($routeProvider,$cookiesProvider) {
  
  $routeProvider
  .when("/", {
    templateUrl : "dashboard.html"
  })
  .when("/create-news", {
    templateUrl : "Create_Blog.html",
    controller: "newController",
    controllerAs: "eventCtl"
  })
  .when("/account", {
    templateUrl : "tableAccount.html",
    controller: "tableAccountController"
  })
  .when("/edit-user", {
    templateUrl : "EditUser.html"
  })
  .when("/ManagerBlog", {
    templateUrl : "ManagerBlog.html",
    controller: "managerBlogController"
   
  })
  .when("/artist-profile/:id", {
    templateUrl : "ArtistProfile.html",
    controller: "profileAccountController"
  })
  .when("/display-slide", {
    templateUrl : "displaySlide.html",
    controller: "displaySlideController"
  })
  .when("/mood", {
    templateUrl : "Categories/Mood.html",
    controller: "moodController"
  })
  .when("/instrument", {
    templateUrl : "Categories/Instrument.html",
    controller: "instrumentController"
  })
  .when("/country", {
    templateUrl : "Categories/Country.html",
    controller: "countryController"
  })
  .when("/song-style", {
    templateUrl : "Categories/SongStyle.html",
    controller: "songStyleController"
  })
  .when("/culture", {
    templateUrl : "Categories/culture.html",
    controller: "cultureController"
  })
  .when("/genre", {
    templateUrl : "Categories/Genre.html",
    controller: "genreController"
  })
  .when("/manage-report", {
    templateUrl : "ManageReport.html",
    controller: "ReportController"
  })
  .when("/statistical_managerment", {
    templateUrl : "statistical_managerment.html",
    controller: "ChartController"
  }).otherwise({ templateUrl : "404.html"});
  
});

app.service('graphqlService',function ($http) {
  const graphqlEndpoint = 'http://localhost:8080/graphql'; 

  this.executeQuery = function (query) {
      const options = {
          method: 'POST',
          url: graphqlEndpoint,
          headers: {
              'Content-Type': 'application/json',
          },
          data: JSON.stringify({ query }),
      };

      return $http(options)
          .then(response => response.data.data)
          .catch(error => {
              throw error.data.errors;
          });
  };
});