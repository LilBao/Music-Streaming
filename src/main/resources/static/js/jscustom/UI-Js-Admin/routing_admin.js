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
  .when("/instrument", {
    templateUrl : "Instrument.html",
    controller: "instrumentController"
  })
  .when("/statistical_managerment", {
    templateUrl : "statistical_managerment.html"
   
  });
});


