var apiNew = "http://localhost:8080/api/v1/admin/new";
var cookieName = "token";

app.controller("managerBlogController", function ($scope, $http, $cookies, $log, $timeout) {

    $scope.items = [];
    $scope.form = {};
    $scope.groupedPosts = {};
    $scope.success = false;

    $scope.reset = function () {
        $scope.form = {};
        $scope.key = null;
    }

    // Gửi yêu cầu HTTP GET để lấy dữ liệu từ API
    $http.get(apiNew).then(function (response) {
        // Lấy danh sách tin tức từ phản hồi
        $scope.items = response.data;

        // Sắp xếp danh sách theo trường "publishDate"
        $scope.items.data.sort(function (b, a) {
            return new Date(a.publishDate) - new Date(b.publishDate);
        });

        // Trích xuất các trường quan trọng và gán cho $scope.posts
        $scope.posts = $scope.items.data.map(function (news) {
            return {
                title: news.title,
                email: news.account.email,
                modifiedby: news.modifiedBy,
                modifidate: news.modifiDate,
                image: news.image.url,
                publishDate: news.publishDate,
                account: news.account.image.url 
            };
        });
        // console.log($scope.posts)
     
        
        // Function hiển thị nội dung theo dòng thời gian
        $scope.groupAndDisplayPosts = function (posts) {
            // Duyệt qua từng bài viết và phân nhóm theo dòng thời gian
            $scope.posts.forEach(function (post) {
                var time = new Date(post.publishDate).toDateString();

                if ($scope.groupedPosts[time]) {
                    $scope.groupedPosts[time].push(post);
                } else {
                    $scope.groupedPosts[time] = [post];
                }
            });

            // Hiển thị nội dung theo dòng thời gian
            // for (var time in $scope.groupedPosts) {
            //     console.log('Mốc thời gian: ' + time);

            //     $scope.groupedPosts[time].forEach(function (post) {
            //         console.log('- ' + post.title);
            //         console.log('- ' + post.content);
            //         console.log('- ' + post.image);
            //         console.log('- ' + post.publishDate);
            //     });
            // }
        };

        // Gọi function để phân nhóm và hiển thị nội dung
        $scope.groupAndDisplayPosts($scope.posts);
    });
});
