var app = angular.module("myApp", ["ngRoute","ngCookies","angularUtils.directives.dirPagination","ngMessages"]);
app.config(function($routeProvider,$cookiesProvider) {
  
  $routeProvider
  .when("/", {
    templateUrl : "dashboard.html"
  })
  .when("/Create_Blog", {
    templateUrl : "Create_Blog.html",
    controller: "newController",
    controllerAs: "eventCtl"
  })
  .when("/EditUser", {
    templateUrl : "EditUser.html"
  })
  .when("/ManagerBlog", {
    templateUrl : "ManagerBlog.html",
    controller: "managerBlogController"
   
  })
  .when("/ArtistProfile", {
    templateUrl : "ArtistProfile.html"
  })
  .when("/display-slide", {
    templateUrl : "displaySlide.html",
    controller: "displaySlideController"
  })
  .when("/Mood", {
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
  .when("/statistical_managerment", {
    templateUrl : "statistical_managerment.html"
  });
});


