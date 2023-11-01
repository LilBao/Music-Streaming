var app = angular.module('myApp', []);
var host = "http://localhost:8080/api";

app.controller('loginCtrl', function ($scope, $http) {
  $scope.loginRequest = {};
  $('#login').click(function () {

    var response = grecaptcha.getResponse();
    if (response.length === 0) {
      alert("Please complete the captcha.");
      return;
    }

    // Hide captcha dialog
    document.getElementById("captchaDialog").style.display = "none";

    var url = host + "/v1/accounts/login";
    var data = angular.copy($scope.loginRequest);
    $http.post(url, data).then(function (resp) {
      setCookie("token", resp.data.data.accessToken);
      showStickyNotification('Login success', 'success', 3000);
    }).catch(function (error) {
      showStickyNotification('Login fail', 'danger', 3000);
    });


    // $scope.loginRequest={};
    // $('#login').click(function(){
    //     var url = host+"/v1/users/login";
    //     var data = angular.copy($scope.loginRequest);
    //     $http.post(url,data).then(resp=>{
    //         setCookie("token",resp.data.data.accessToken);
    //     })
    // })
  })


  })
})

function showCaptchaDialog(event) {
  event.preventDefault(); // Prevent form submission

  var email = document.getElementById("email").value;
  var password = document.getElementById("password").value;

  if (email === "" || password === "") {
    showStickyNotification('Please enter both email and password.', 'danger', 3000);
    return;
  }

  document.getElementById("captchaDialog").style.display = "block";
}

function cancel() {
  document.getElementById("captchaDialog").style.display = "none";
}

