var app = angular.module("myApp", ["ngRoute","ngCookies","ngMessages"]);
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
  })
  .when("/statistical_managerment", {
    templateUrl : "statistical_managerment.html",
    controller: "ChartController"
  });
});


