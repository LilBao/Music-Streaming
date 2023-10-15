$(document).ready(function () {
    $('#myTab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
 
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

    new Chart(age, {
        type: 'bar',
        data: {
            labels: ['<18', '18-22', '23-27', '28-34', '35-44', '45-60', '60+'],
            datasets: [{
                data: [10, 27, 26, 16, 7, 3, 1],
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

    new Chart(gender, {
        type: 'doughnut',
        data: {
            labels: ['Male', 'Female', 'Non-binary', 'Not specific'],
            datasets: [{
                data: [16, 77, 1, 6],
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

    //Char Map
    anychart.onDocumentReady(function () {
        var map = anychart.map();
        map.geoData('anychart.maps.world');
        map.interactivity().selectionMode('none');
        map.padding(0);

        // Tạo một mảng dữ liệu tùy chỉnh
        var customData = [
            { id: 'US', density: 1 }, // Ví dụ: Mật độ dân số của Hoa Kỳ
            { id: 'CA', density: 50 },
            { id: 'VN', density: 100000 },
            { id: 'GB', density: 50000 }  // Ví dụ: Mật độ dân số của Canada
            // Thêm các quốc gia hoặc khu vực khác tại đây
        ];

        var dataSet = anychart.data.set(customData);
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
    });
})