var app = angular.module('myApp', ['ngCookies']);
var host = "http://localhost:8080/api";
app.controller('ads', function ($scope, $http, $cookies) {

    $scope.req = {};
    $scope.sub = {};
    var cookieName = "token";

    $scope.findSub = () => {
        $scope.pre = [];
        $scope.bas = [];

        $http.get(host + `/v1/subscription`, {
            params: {
                "cate": 'ADVERTISEMENT',
                "active": true
            }
        }).then(
            resp => {
                $scope.dat = resp.data.data;
                console.log($scope.dat);
                $scope.dat.forEach(element => {
                    if (element.priority === 1 || element.priority === 2 || element.priority === 3) {
                        $scope.pre.push(element);

                    } else if (element.priority === 4 || element.priority === 5 || element.priority === 6) {
                        $scope.bas.push(element);
                    }
                });
            }
        );
    };

    $scope.findSubById = (id) => {
        $scope.sub = {};
        $http.get(host + `/v1/subscription/${id}`, {

        }).then(
            resp => {
                $scope.sub = resp.data.data;
                console.log($scope.sub);
            }
        );
    };

    $scope.findSub()

    $scope.startAdvertising = function (type) {
        if (type == 1 || type == 2 || type == 3) {
            $scope.hiden = false;
        } else if (type == 4 || type == 5 || type == 6) {
            $scope.hiden = true; 
        }

    }

    $scope.buyads = function () {

        var fileInput = document.getElementById('banner');
        var file = fileInput.files.length > 0 ? fileInput.files[0] : null;

        var fileInputAudio = document.getElementById('audio');
        var fileAudio = fileInputAudio.files[0] > 0 ? fileInputAudio.files[0] : null;

        let item = angular.copy($scope.req);
        var formData = new FormData();
        formData.append("title", item.title);
        formData.append("content", item.content);
        formData.append("tag", item.tag);
        formData.append("target", item.target);
        formData.append("url", item.url);

        if (file) {
            formData.append("image", file || null);
        }

        if (fileAudio) {
            formData.append("audio", fileAudio || null);
        }

        formData.append("subscriptionId", $scope.sub.subscriptionId || null);
        formData.append("priority", $scope.sub.priority || null);

        var config = {
            headers: {
                'Authorization': 'Bearer ' + $cookies.get(cookieName),
                'Content-Type': undefined
            }
        };

        var url = host + '/v1/buy-ads';
        $http.post(url, formData, config).then(function (response) {
            showStickyNotification("success", "success", 3000)
        }).catch(function (error) {
            showStickyNotification("fail", "danger", 3000)
            console.error("Error", error);
        });
    }
})
