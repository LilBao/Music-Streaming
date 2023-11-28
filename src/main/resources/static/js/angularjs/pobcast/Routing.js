var app = angular.module("myApp", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "homePobcast.html"
        })
        .when("/home", {
            templateUrl: "HomePobcast.html"
        })
        .when("/analytics", {
            templateUrl: "HomePobcast.html"
        })
        .when("/episodes", {
            templateUrl: "Episode.html",
            controller: "episodeCtrl"
        })
        .when("/archives", {
            templateUrl: "Archieves.html",
            controller: "myArchiveCtrl"
        })
        .when("/podcast-settings", {
            templateUrl: "SettingPodcast.html",
            controller: "myPodcastCtrl"
        })
        .when("/new-episode", {
            templateUrl: "dashboard.html",
            controller: "dashboardCtrl"
        })
        .when("/episode-infor", {
            templateUrl: "EpisodeInfor.html",
            controller: "episodeInforCtrl"
        })
        .when("/campaign", {
            templateUrl : "Marketing.html",
            controller :"marketingCtrl"
        })
})

app.service('FileService', function () {
    var file = null;
    return {
        setFile: function (newFile) {
            file = newFile;
        },
        getFile: function () {
            return file;
        }
    };
});

app.service('sortService', function () {
    this.sort = function (list, field) {
        this.direction = this.direction === "asc" ? "desc" : "asc";
        if (this.direction === "asc") {
            list.sort((a, b) => a[field].localeCompare(b[field]))
        } else {
            list.sort((a, b) => b[field].localeCompare(a[field]))
        }
    }
    this.sortNumber = function (list, field) {
        this.direction = this.direction === "asc" ? "desc" : "asc";
        if (this.direction === "asc") {
            list.sort((a, b) => a[field] - (b[field]))
        } else {
            list.sort((a, b) => b[field] - (a[field]))
        }
    }
})

app.service('pageService', function () {
    this.pager = {
        page: 0,
        size: 5,
        setPageSize: function(newSize) {
            this.size = newSize;
        },
        items(list) {
            var start = this.page * this.size;
            return list.slice(start, start + this.size)
        },
        count(list) {
            return Math.ceil(1.0 * list.length / this.size)
        },
        prev() {
            this.page--;
            if (this.page < 0) {
                this.page = 0;
            }
        },
        next(list) {
            this.page++;
            if (this.page >= this.count(list)) {
                this.page = this.count(list) - 1;
            }
        },
        getNumbers(n) {
            var rangeArray = [];
            for (var i = 1; i <= n; i++) {
                rangeArray.push(i);
            }
            return rangeArray;
        }
    }
})