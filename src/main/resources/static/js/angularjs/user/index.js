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
            controller: "SearchController"
        })
        .when("/show/:id", {
            templateUrl: "show-podcast.html",
            controller: "ShowPodcast"
        })
        .when("/playlist-art/:id", {
            templateUrl: "search-playlist-art.html",
            controller: "playlistCtrl"
        })
        .when("/album/:id", {
            templateUrl: "search-album.html",
            controller: "album"
        })
        .when("/mood/:id", {
            templateUrl: "search-mood.html",
            controller: "mood"
        })
        .when("/episode/:id", {
            templateUrl: "search-episodes.html",
            controller: "episode"
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
        .when("/profile/:profile/:id", {
            templateUrl: "profile.html",
            controller: 'profileCtrl'
        })
        .when("/artist/:id", {
            templateUrl: "profileArtist.html",
            controller: 'profileCtrl'
        })
        .when("/podcast/:id", {
            templateUrl: "playlist.html",
            controller: 'playlistCtrl'
        })
        .when("/song/:id", {
            //templateUrl: "playlist.html",
            //controller: 'playlistCtrl'
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
        },
        getQueue: function () {
            return queue;
        },
        getPeekQueue: function () {
            return peekQueue;
        },
        clearQueue: function () {
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
    var listLikedSongs = [];
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
        },
        setListLikedSongs: function (list) {
            listLikedSongs = list;
        },
        getListLikedSongs: function () {
            return listLikedSongs;
        },
        isLiked: function (data) {
            if (data.recordingId) {
                var index = this.getListLikedSongs().findIndex(item => item.recordingId === data.recordingId);
            } else {
                var index = this.getListLikedSongs().findIndex(item => item.episodeId === data.episodeId);
            }
            return index !== -1;
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
        setPageSize: function (newSize) {
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