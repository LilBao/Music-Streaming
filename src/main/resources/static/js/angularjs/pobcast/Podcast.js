var app = angular.module('myApp', ["ngCookies"]);
var host = "http://localhost:8080/api";

app.controller('myCtrlPodcast', function ($scope, $http, $cookies, $window) {
    $scope.podcast = {};
    $scope.dataDisplayImage = {};
    $scope.listImage = [];
    $scope.dataNews = [];
    $scope.top3 = [];

    $http.get(host + "/v1/profile-podcast", {
        headers: { 'Authorization': 'Bearer ' + $cookies.get('token') }
    }).then(resp => {
        $scope.podcast = resp.data.data;
        console.log($scope.podcast.idrole);
        $scope.id = $scope.podcast.idrole;
        $scope.hiden = true;
    }).catch(error => {
        console.log(error)
    })

    $scope.displayImage = function (position) {
        $http.get(host + "/v1/display/" + position).then(resp => {
            $scope.dataDisplayImage = resp.data.data;
            // console.log($scope.dataDisplayImage);
            $scope.listImage = $scope.dataDisplayImage.map(element => element.url);
            setUpImageSlider();
        }).catch(error => {
            console.log(error);
        });
    }
    $scope.displayImage('PODCAST');

    //Slide feature
    function setUpImageSlider() {
        var img = $scope.listImage.slice().sort(() => Math.random() - 0.5).slice(0, 3);

        // Your image slider setup code here
        var color = ["#b49bc8", "#ed7e7e", "#a0c3d2"];
        const imgFeature = $('#img-landing-home');
        const prevImg = $('#prev');
        const nextImg = $('#next');
        const positionImg = $('.position-img');
        var index = 0;
        imgFeature.attr("src", img[index]);

        positionImg.text(index + 1 + "/" + img.length);

        $('#landing-home').css("background-color", color[index]);

        prevImg.click(function () {
            prevImg.attr("disable", index === 0 ? true : false);
            if (index > 0) index--;
            imgFeature.attr("src", img[index]);
            positionImg.text(index + 1 + "/" + img.length);
            $('#landing-home').css({
                'background-color': color[index],
                'transition': 'background-color 1s ease'
            });
        });

        nextImg.click(function () {
            if (index < img.length - 1) index++;
            imgFeature.attr("src", img[index]);
            positionImg.text(index + 1 + "/" + img.length);
            $('#landing-home').css({
                'background-color': color[index],
                'transition': 'background-color 1s ease'
            });
        });
    }

    $scope.dataNews = function (role) {

        $http.get(host + "/v1/news", { params: { createfor: role } }).then(resp => {
            $scope.dataNews = resp.data.data;
            // console.log($scope.dataNews);
        }).catch(error => {
            console.log(error);
        })
    }
    $scope.dataNews("PODCAST");

     //Get access podcast
    $scope.getAccessPodcast = function(){
        console.log($scope.id);
        var isVerify = $scope.id;
        if(isVerify == 3){
            $window.location.href = '/podcast-browse';
        }else {
            $window.location.href = '/getstarted';
        }
    } 

    $scope.login = function () {
        $window.location.href = '/logins';
    }

    $scope.newsPage = function (id) {
        $window.location.href = '/news/home/'+ `${id}`;
        localStorage.setItem('newsId', id);
        localStorage.setItem('op', 'pod'); 
    }
    
    $http.get(host + "/v1/top3-podcast").then(resp => {
        $scope.top3 = resp.data.data;
    }).catch(error => {
        console.log(error);
    });
})



