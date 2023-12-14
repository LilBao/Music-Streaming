var host = "http://localhost:8080/api";
app.controller('myCtrl', function ($scope, $http) {
    $scope.artist={};
    $http.get(host + "/v1/profile", {
        headers: { 'Authorization': 'Bearer ' + getCookie('token') }
    }).then(resp => {
        $scope.artist = resp.data.data;
    }).catch(error => {
        console.log(error)
    })


    //Slide feature
    var img = ["carousel1.jpg", "carousel2.jpg", "carousel3.jpg"]
    var color = ["#b49bc8", "#ed7e7e", "#a0c3d2"]
    const imgFeature = $('#img-landing-home');
    const prevImg = $('#prev');
    const nextImg = $('#next');
    const positionImg = $('.position-img');
    var index = 0;
    imgFeature.attr("src", "../static/img/" + img[index]);

    positionImg.text(index + 1 + "/" + img.length);

    $('#landing-home').css("background-color", color[index]);

    prevImg.click(function () {
        prevImg.attr("disable", index === 0 ? true : false);
        if (index > 0) index--;
        imgFeature.attr("src", "../static/img/" + img[index]);
        positionImg.text(index + 1 + "/" + img.length);
        $('#landing-home').css({
            'background-color':color[index],
            'transition': 'background-color 1s ease'
        });
    });

    nextImg.click(function () {
        if (index < img.length - 1) index++;
        imgFeature.attr("src", "../static/img/" + img[index]);
        positionImg.text(index + 1 + "/" + img.length);
        $('#landing-home').css({
            'background-color':color[index],
            'transition': 'background-color 1s ease'
        });
    });
})