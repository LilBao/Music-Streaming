var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "homePobcast.html"
    })
    .when("/home", {
        templateUrl : "HomePobcast.html"
    })
    .when("/analytics", {
        templateUrl : "HomePobcast.html"
    })
    .when("/episodes", {
        templateUrl : "HomePobcast.html"
    })
    .when("/overview", {
        templateUrl : "Profile.html"
    })
    .when("/new-episode", {
        templateUrl : "dashboard.html",
        controller :"dashboardCtrl"
    })
    .when("/episode-infor", {
        templateUrl : "EpisodeInfor.html",
        controller :"episodeInforCtrl"
    })
})

app.service('FileService', function() {
    var file = null;
    return {
        setFile: function(newFile) {
            file = newFile;
        },
        getFile: function() {
            return file;
        }
    };
});