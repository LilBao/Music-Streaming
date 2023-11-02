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
            templateUrl: "search.html"
        })
        .when("/search-detail", {
            templateUrl: "search-detail.html",
            controller: "SearchController"
        })
        .when("/karaoke", {
            templateUrl: "Karaoke.html",
            controller:'karaokeCtrl'
        })
        .when("/queue", {
            templateUrl: "Queue.html",
            controller:'queueCtrl'
        });
});

app.service('queueService', function() {
    var queue = [];
    var peekQueue = [];
    return {
        enQueue: function(item) {
            queue.push(item);
        },
        deQueue: function() {
            peekQueue.push(queue.splice(0,1));
            queue.splice(0,1);
        },
        getQueue: function(){
            return queue;
        },
        getPeekQueue: function(){
            return peekQueue;
        }
    };
});

app.service('audioService', function() {
    var audioSrc;
    var lyricsSrc;
    var listPlay = [];
    var current;
    return {
        setAudio: function(src) {
            audioSrc = src
        },
        getAudio: function() {
            return audioSrc;
        },

        setLyricsSrc: function(lyricSrc){
            lyricsSrc=lyricSrc;
        },
        getLyricsSrc: function(){
            return lyricsSrc;
        },

        setListPlay: function(list){
            listPlay = list;
        },
        getListPlay: function(){
            return listPlay;
        },
        setCurrentSong: function(index){
            current = index;
        },
        getCurrentSong: function(){
            return current;
        }
    };
});
