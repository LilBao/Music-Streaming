var app = angular.module('myApp', []);
var host = "http://localhost:8080/api";

app.controller('signupCtrl', function($scope, $http) {
    $scope.signupRequest = {};

    var countryList = [
        { name: "Vietnam", code: "VN" },
        { name: "United States", code: "US" },
        { name: "Canada", code: "CA" },
        { name: "Australia", code: "AU" },
        { name: "United Kingdom", code: "UK" },
        { name: "France", code: "FR" },
        { name: "Germany", code: "DE" },
        { name: "India", code: "IN" },
        { name: "China", code: "CN" },
        { name: "Japan", code: "JP" },
        { name: "Brazil", code: "BR" },
        { name: "Russia", code: "RU" },
        { name: "South Korea", code: "KR" },
        { name: "Mexico", code: "MX" },
        { name: "South Africa", code: "ZA" }
    ];

    $scope.mapCountryCode = function(countryName) {
        var selectedCountry = countryList.find(function(country) {
            return country.name.toLowerCase() === countryName.toLowerCase();
        });
        return selectedCountry ? selectedCountry.code : null;
    };

    $scope.signup = function(){
        var url = host + "/v1/accounts/signUp";

        // Kiểm tra dữ liệu đầu vào trước khi gửi yêu cầu đăng ký
        if (!$scope.signupRequest.email) {
            showStickyNotification('Please enter your email.', 'danger', 3000);
            return;
        }
        if (!$scope.signupRequest.password) {
            showStickyNotification('Please create a password.', 'danger', 3000);
            return;
        }
        if (!$scope.signupRequest.username) {
            showStickyNotification('Please enter your profile name.', 'danger', 3000);
            return;
        }
        if (!$scope.signupRequest.birthday) {
            showStickyNotification('Please enter your date of birth.', 'danger', 3000);
            return;
        }
        if (!$scope.signupRequest.country) {
            showStickyNotification('Please enter the country code.', 'danger', 3000);
            return;
        }

        $scope.signupRequest.country = $scope.mapCountryCode($scope.signupRequest.country);
        $http.post(url, $scope.signupRequest)
        .then(function (response) {
            showStickyNotification('Signup successful. Please check your email to verify your account.', 'success', 3000);
        })
        .catch(function (error) {
            showStickyNotification('An error occurred. Please try again later.', 'danger', 3000);
        });
    }
});
