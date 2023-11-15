var host = "http://localhost:8080/api";
app.controller('analysisCtrl', function ($scope, $http, graphqlService, $route) {
    $('#myTab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    var aCountries = [];
    var aAge;
    var aGender = [];
    $scope.Recording = function () {
        const query = `{
                recordingById(recordingId: `+ 10 + `) {
                    recordingId
                    recordingName
                    monitors{
                      account{
                        username
                        birthday
                        gender
                        country
                      }
                    }
                }
            }`
        graphqlService.executeQuery(query).then(data => {
            $scope.monitor = data.recordingById.monitors;

        }).catch(err => {

        })
    }
    $scope.Recording();

    const ctx = $('#chartListener');
    const age = $('#age');
    const gender = $('#gender');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
            datasets: [{
                data: [12, 19, 3, 5, 2, 3],
                borderWidth: 1,
                fill: true,
                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }, plugins: {
            legend: {
                display: false, // Ẩn chú thích
            }, datalabels: {
                anchor: 'end',
                align: 'top',
                formatter: function (value, context) {
                    return value; // Hiển thị dữ liệu ở đầu mỗi cột
                }
            }
        }
    });

    $http.get(host + '/v1/monitor/age', {
        params: { id: 10, duration: 3 }
    }).then(resp => {
        var under18 = 0;
        var between18To22 = 0;
        var between23To27 = 0;
        var between28To34 = 0;
        var between35To44 = 0;
        var between45To60 = 0;
        var over60 = 0;

        resp.data.data.forEach(e => {
            if (e.age < 18) {
                under18 = e.quantity;
            } else if (18 < e.age < 22) {
                between18To22 = e.quantity;
            } else if (23 < e.age < 27) {
                between23To27 = e.quantity;
            } else if (28 < e.age < 34) {
                between28To34 = e.quantity;
            } else if (35 < e.age < 44) {
                between35To44 = e.quantity;
            } else if (45 < e.age < 60) {
                between45To60 = e.quantity;
            } else {
                over60 = e.quantity;
            }
        });
        aAge = [under18, between18To22, between23To27, between28To34, between35To44, between45To60, over60];
        new Chart(age, {
            type: 'bar',
            data: {
                labels: ['<18', '18-22', '23-27', '28-34', '35-44', '45-60', '60+'],
                datasets: [{
                    data: aAge,
                    borderWidth: 1,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.5)',
                        'rgba(75, 192, 192, 0.5)',
                        'rgba(255, 205, 86, 0.5)',
                        'rgba(54, 162, 235, 0.5)'
                    ],
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                scales: {
                    x: {
                        beginAtZero: true,
                    },
                    y: {
                        display: false, // Ẩn thanh y
                        beginAtZero: true,
                    }
                },
                plugins: {
                    legend: {
                        display: false, // Ẩn chú thích
                    },
                }
            }
        });
    })

    $http.get(host + '/v1/monitor/gender', {
        params: { id: 10, duration: 30 }
    }).then(resp => {
        var male = 0;
        var female = 0;
        var nonBinary = 0;
        var notSpecific = 0;
        resp.data.data.forEach(e => {
            if (e.gender == 0) {
                female = e.quantity;
            } else if (e.gender == 1) {
                male = e.quantity;
            } else if (e.gender == 2) {
                nonBinary = e.quantity;
            } else {
                notSpecific = e.quantity;
            }
        })
        aGender = [female, male, nonBinary, notSpecific];
        new Chart(gender, {
            type: 'doughnut',
            data: {
                //0         //1         //2           //3   
                labels: ['Female', 'Male', 'Non-binary', 'Not specific'],
                datasets: [{
                    data: aGender,
                    borderWidth: 1,
                    fill: true,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.5)',
                        'rgba(75, 192, 192, 0.5)',
                        'rgba(255, 205, 86, 0.5)',
                        'rgba(54, 162, 235, 0.5)'
                    ],
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
            }
        });
    })

    //Char Map
    anychart.onDocumentReady(function () {
        $http.get(host + '/v1/monitor/country', {
            params: { id: 10, duration: 3 }
        }).then(resp => {
            var map = anychart.map();
            map.geoData('anychart.maps.world');
            map.interactivity().selectionMode('none');
            map.padding(0);

            resp.data.data.forEach(e => {
                var obj = { id: e.country, density: e.quantity }
                aCountries.push(obj);
            });

            var dataSet = anychart.data.set(aCountries);
            var densityData = dataSet.mapAs({ value: 'density' });
            var series = map.choropleth(densityData);

            // ... Các cấu hình và tùy chỉnh khác như ví dụ trước ...
            //Phạm vi chỉnh lại lượt nghe
            var scale = anychart.scales.ordinalColor([
                { less: 1000 },
                { from: 1000, to: 10000 },
                { from: 10000, to: 100000 },
                { from: 100000, to: 500000 },
                { from: 500000, to: 1000000 },
                { from: 1000000, to: 10000000 },
                { from: 10000000, to: 100000000 },
                { from: 100000000, to: 500000000 },
                { greater: 500000000 }
            ]);
            scale.colors([
                '#81d4fa',
                '#4fc3f7',
                '#29b6f6',
                '#039be5',
                '#0288d1',
                '#0277bd',
                '#01579b',
                '#014377',
                '#000000'
            ]);
            var colorRange = map.colorRange();
            colorRange.enabled(true).padding([0, 0, 20, 0]);
            colorRange
                .ticks()
                .enabled(true)
                .stroke('3 #ffffff')
                .position('center')
                .length(7);
            colorRange.colorLineSize(5);
            colorRange.marker().size(7);
            colorRange
                .labels()
                .fontSize(11)
                .padding(3, 0, 0, 0)
                .format(function () {
                    var range = this.colorRange;
                    var name;
                    if (isFinite(range.start + range.end)) {
                        name = range.start + ' - ' + range.end;
                    } else if (isFinite(range.start)) {
                        name = 'More than ' + range.start;
                    } else {
                        name = 'Less than ' + range.end;
                    }
                    return name;
                });

            series.colorScale(scale);
            // create zoom controls
            var zoomController = anychart.ui.zoom();
            zoomController.render(map);
            // Khởi tạo biểu đồ
            map.container('world');
            map.draw();
            var zoom = anychart.ui.zoom();
            zoom.renderTo('zoom-controls');

            // Kết nối thanh thu phóng với biểu đồ
            zoom.target(map);

        })
    })
})