var app = angular.module("myApp", ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "discover.html",
            //controller: "myCtrl"
        })
        .when("/main", {
            templateUrl: "discover.html"
        })
        .when("/search", {
            templateUrl: "search-detail.html",
            controller: "SearchController"
        })
        .when("/browse-podcast", {
            templateUrl: "browse-podcast.html",
        })
        .when("/karaoke", {
            templateUrl: "Karaoke.html",
            controller: 'karaokeCtrl'
        })
        .when("/wishlist", {
            templateUrl: "wishlist.html",
            controller: 'playlistCtrl'
        })
        .when("/playlist/:id", {
            templateUrl: "playlist.html",
            controller: 'playlistCtrl'
        })      
        .when("/:profile/:id", {
            controller: 'profileCtrl'
        })
        .when("/podcast/:id", {
            templateUrl: "playlist.html",
            controller: 'playlistCtrl'
        })
        .when("/queue", {
            templateUrl: "Queue.html",
            controller: 'queueCtrl'
        });
});

app.service('queueService', function () {
    var queue = [];
    var peekQueue = [];
    return {
        enQueue: function (item) {
            queue.push(item);
        },
        deQueue: function () {
            peekQueue.push(queue.splice(0, 1));
            queue.splice(0, 1);
        },
        getQueue: function () {
            return queue;
        },
        getPeekQueue: function () {
            return peekQueue;
        },
        clearQueue: function(){
            queue = [];
            return queue; 
        }
    };
});

app.service('audioService', function () {
    var audioSrc;
    var lyricsSrc;
    var listPlay = [];
    var current;
    return {
        setAudio: function (src) {
            audioSrc = src
        },
        getAudio: function () {
            return audioSrc;
        },

        setLyricsSrc: function (lyricSrc) {
            lyricsSrc = lyricSrc;
        },
        getLyricsSrc: function () {
            return lyricsSrc;
        },

        setListPlay: function (list) {
            listPlay = list;
        },
        getListPlay: function () {
            return listPlay;
        },
        setCurrentAudio: function (index) {
            current = index;
        },
        getCurrentAudio: function () {
            return current;
        }
    };
});

app.service('graphqlService', function ($http) {
    const graphqlEndpoint = 'http://localhost:8080/graphql';
    this.executeQuery = function (query) {
        const options = {
            method: 'POST',
            url: graphqlEndpoint,
            headers: {
                'Content-Type': 'application/json',
            },
            data: JSON.stringify({ query }),
        };

        return $http(options)
            .then(response => response.data.data)
            .catch(error => {
                throw error.data.errors;
            });
    };
});

app.directive('formatTime', function () {
    return {
        restrict: 'E',
        scope: {
            seconds: '='
        },
        template: '<span>{{ secondsToTime(seconds) }}</span>',
        link: function (scope, element, attrs) {
            scope.secondsToTime = function (seconds) {
                var hours = Math.floor(seconds / 3600);
                var minutes = Math.floor((seconds % 3600) / 60);
                var secs = Math.floor((seconds % 60))
                const secondsStr = secs < 10 ? "0" + secs : secs;
                if (hours > 0) {
                    return hoursStr + ':' + minutes + ':' + secondsStr;
                } {
                    return minutes + ':' + secondsStr;
                }
            };
        }
    };
});