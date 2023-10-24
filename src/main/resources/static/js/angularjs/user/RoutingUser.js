var app = angular.module("myApp", ["ngRoute"]);

app.config(function($routeProvider) {
  $routeProvider
  .when("/", {
    templateUrl: "user/index.html"
  })
  .when("/user-home", {
    templateUrl: "user/index.html"
  })
  .when("/login", {
    templateUrl: "user/login.html",
    controller: "loginCtrl"
  })
  .when("/signUp", {
    templateUrl: "user/signup.html",
    controller: "signupCtrl"
  })
  .when("/forgotpassword", {
    templateUrl: "user/fogotpassword.html",
    controller: "forgotPasswordCtrl"
  })
  .when("/changepassword", {
    templateUrl: "user/changepassword.html",
    controller: "changepasswordCtrl"
  });
});