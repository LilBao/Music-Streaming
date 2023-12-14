var app = angular.module("myApp", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/premium", {
            templateUrl: "User/subscription.html",
            controller:"subscriptionCtrl"
        })
        .when("/premium/:id", {
            templateUrl: "User/subscriptionDetail.html",
            controller:"subscriptionCtrl"
        })
});
