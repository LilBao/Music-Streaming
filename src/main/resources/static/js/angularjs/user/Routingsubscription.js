var app = angular.module("myApp", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "subscription.html",
        })
        .when("/premium", {
            templateUrl: "subscription.html",
            controller:"subscriptionCtrl"
        })
        .when("/premium/:id", {
            templateUrl: "subscriptionDetail.html",
            controller:"subscriptionCtrl"
        })
});
