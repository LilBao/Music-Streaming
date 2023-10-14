var app = angular.module("myApp", ["ngRoute","ngCookies"]);
app.config(function($routeProvider,$cookiesProvider) {

  $routeProvider
  .when("/", {
    templateUrl : "dashboard.html"
  })
  .when("/Create_Blog", {
    templateUrl : "Create_Blog.html"
  })
  .when("/EditUser", {
    templateUrl : "EditUser.html"
  })
  .when("/ManagerBlog", {
    templateUrl : "ManagerBlog.html"
  })
  .when("/ArtistProfile", {
    templateUrl : "ArtistProfile.html"
  })
  .when("/Mood", {
    templateUrl : "Mood.html",
    controller: "moodController"
  })
  .when("/ads", {
    templateUrl : "ads.html"
  })
  .when("/statistical_managerment", {
    templateUrl : "statistical_managerment.html"
  });
});

app.constant("apiURL", "http://localhost:8080/api/v1/category"); 