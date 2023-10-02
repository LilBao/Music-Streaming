var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
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
  .when("/song", {
    templateUrl : "song.html"
  })
  .when("/blue", {
    templateUrl : "blue.htm"
  })
  .when("/statistical_managerment", {
    templateUrl : "statistical_managerment.html"
  });
});