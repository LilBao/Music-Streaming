var app = angular.module("myApp", ["ngRoute","ngCookies","ngMessages","angularUtils.directives.dirPagination"]);
app.config(function($routeProvider,$cookiesProvider) {
  
  $routeProvider
  .when("/", {
    templateUrl : "dashboard.html"
  })
  .when("/create-news", {
    templateUrl : "Create_Blog.html",
    controller: "newController",
    controllerAs: "eventCtl"
  })
  .when("/account", {
    templateUrl : "tableAccount.html",
    controller: "tableAccountController"
  })
  .when("/edit-user", {
    templateUrl : "EditUser.html"
  })
  .when("/ManagerBlog", {
    templateUrl : "ManagerBlog.html",
    controller: "managerBlogController"
   
  })
  .when("/artist-profile/:id", {
    templateUrl : "ArtistProfile.html",
    controller: "profileAccountController"
  })
  .when("/display-slide", {
    templateUrl : "displaySlide.html",
    controller: "displaySlideController"
  })
  .when("/mood", {
    templateUrl : "Categories/Mood.html",
    controller: "moodController"
  })
  .when("/instrument", {
    templateUrl : "Categories/Instrument.html",
    controller: "instrumentController"
  })
  .when("/country", {
    templateUrl : "Categories/Country.html",
    controller: "countryController"
  })
  .when("/song-style", {
    templateUrl : "Categories/SongStyle.html",
    controller: "songStyleController"
  })
  .when("/culture", {
    templateUrl : "Categories/culture.html",
    controller: "cultureController"
  })
  .when("/genre", {
    templateUrl : "Categories/Genre.html",
    controller: "genreController"
  })
  .when("/manage-report", {
    templateUrl : "ManageReport.html",
  })
  .when("/statistical_managerment", {
    templateUrl : "statistical_managerment.html",
    controller: "ChartController"
  }).when("/approve-role", {
    templateUrl : "approveRoles.html",
  })
  .when("/subscriptions", {
    templateUrl : "Subscription.html",
    controller: "subscriptionController"
  })
  .otherwise({ templateUrl : "404.html"});
  
});

app.service('graphqlService',function ($http) {
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

app.service('sortService', function () {
  this.sort = function (list, field) {
      this.direction = this.direction === "asc" ? "desc" : "asc";
      if (this.direction === "asc") {
          list.sort((a, b) => a[field].localeCompare(b[field]))
      } else {
          list.sort((a, b) => b[field].localeCompare(a[field]))
      }
  }
  sortNumber = function (list, field) {
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