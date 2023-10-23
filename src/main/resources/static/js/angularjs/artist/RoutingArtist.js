var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
  $routeProvider
  .when("/", {
    templateUrl : "HomePobcast.html"
  })
  .when("/artist-home", {
    templateUrl : "Home.html"
  })
  .when("/artist-music", {
    templateUrl : "Music.html",
    //controller: "musicCtrl"
  })
  .when("/artist-audience", {
    templateUrl : "Audience.html"
  })
  .when("/artist-profile", {
    templateUrl : "Profile.html",
    controller :"profileArtistCtrl"
  })
});