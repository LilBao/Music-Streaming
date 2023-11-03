app.controller('recordCtrl', function ($scope, $http) {
    $scope.record = {};
    $scope.genre = {};
    $scope.songGenre = {};
    //Call API => create Record
    $('#create-record').click(function () {
        $scope.createRecord() 
    })

    $scope.createRecord = function(){
        $scope.checkbox();
        var url = host + "/v1/record";
        var data = new FormData();
        data.append('recordingName', $scope.record.name);
        data.append('studio', $scope.record.studio);
        data.append('produce', $scope.record.proceduce);
        data.append('idMv', $scope.record.idMv);
        data.append('mood', $scope.moods);
        data.append('songStyle', $scope.styles);
        data.append('culture', $scope.cultures);
        data.append('instrument', $scope.instruments);
        data.append('versions', $scope.record.version);
        data.append('fileRecord', $scope.recordFile);
        if ($scope.lyricsFile) {
            data.append('fileLyrics', $scope.lyricsFile);
        }
        $http.post(url, data, {
            headers: {
                'Content-Type': undefined,
                'Authorization': 'Bearer ' + getCookie('token')
            },
            transformRequest: angular.identity
        }).then(resp => {
            var song = resp.data.data;
            $('input[name="genre"]:checked').each(function () {
                let genreId = $(this).val();
                var url = host + "/v1/genre/" + genreId;
                $http.get(url).then(resp => {
                    let genre = resp.data.data;
                    $scope.createSongGenre(song, genre);
                }).catch(error => {
                    console.log(error)
                })
            })
            showStickyNotification('Create record success', 'success', 3000);
        }).catch(error => {
            showStickyNotification('Create record fail', 'danger', 3000);
        })
    }

    $scope.createSongGenre = function (song, genre) {
        $scope.songGenre.recording = song;
        $scope.songGenre.genre = genre;
        var data = angular.copy($scope.songGenre);
        let url = host + "/v1/song-genre";
        $http.post(url, data).then(resp => {
            console.log("success")
        }).catch(error => {
            console.log(error)
        })
    }

    $scope.updateFile = function () {
        var url = host + "/v1/record";
        var data = new FormData();
        data.append('fileRecord', $scope.recordFile);
        if ($scope.lyricsFile) {
            data.append('fileLyrics', $scope.lyricsFile);
        }
    }

    //Get File Audio and File lyrics
    $scope.selectFileRecord = function (id) {
        $('#' + id).change(function (event) {
            var file = event.target.files[0];
            if (file) {
                $scope.$apply(function () {
                    if (id == 'records') {
                        $scope.recordFile = file;
                    }
                    if (id == 'lyrics') {
                        $scope.lyricsFile = file;
                    }
                });
            }
        });
    };

    $scope.genres = [];
    $scope.moods = [];
    $scope.songStyles = [];
    $scope.instruments = [];
    $scope.cultures = [];
    $scope.getListGenre = function () {
        let url = host + "/v1/genre";
        $http.get(url).then(resp => {
            $scope.genres = resp.data.data;
        })
    }

    $scope.getListMood = function () {
        let url = host + "/v1/mood";
        $http.get(url).then(resp => {
            $scope.moods = resp.data.data;
        })
    }

    $scope.getListInstrument = function () {
        let url = host + "/v1/instrument";
        $http.get(url).then(resp => {
            $scope.instruments = resp.data.data;
        })
    }

    $scope.getListSongStyle = function () {
        let url = host + "/v1/song-style";
        $http.get(url).then(resp => {
            $scope.songStyles = resp.data.data;
        })
    }

    $scope.getListCulture = function () {
        let url = host + "/v1/culture";
        $http.get(url).then(resp => {
            $scope.cultures = resp.data.data;
        })
    }

    $scope.getListGenre();
    $scope.getListMood();
    $scope.getListInstrument();
    $scope.getListSongStyle();
    $scope.getListCulture();

    //get Value checbox
    $scope.checkbox = function () {
        $scope.cultures = "";
        $scope.moods = "";
        $scope.styles = "";
        $scope.instruments = "";
        const selectedValues = [];
        $('input[name="culture"]:checked').each(function () {
            $scope.cultures += " " + $(this).val();
        });
        $('input[name="mood"]:checked').each(function () {
            $scope.moods += " " + $(this).val();
        });
        $('input[name="style"]:checked').each(function () {
            $scope.styles += " " + $(this).val();
        });
        $('input[name="intrument"]:checked').each(function () {
            $scope.instruments += " " + $(this).val();
        });
        $('input[name="genre"]:checked').each(function () {
            selectedValues.push($(this).val());
        });
        $scope.genre = selectedValues;
    }
    
    $('#genre').on('keypress', function (event) {
        if (event.key === 'Enter') {
            event.preventDefault();
        }
    });
    $('#genre').on('change', function () {
        var selectedOption = $(this).val().trim();
        var datalist = document.getElementById('genres');
        var options = datalist.getElementsByClassName('option-genre');
        var GenreText = "";
        for (var i = 0; i < options.length; i++) {
            if (options[i].value == selectedOption) {
                GenreText = options[i].text;
                break;
            }
        }
        if (selectedOption && selectedOption != "") {
            var tag = $('<div class="checkbox"> <input type="checkbox" checked name="genre" value='+selectedOption+'id="" />' +
                '<div class="box bg-black text-white"><p>' + GenreText + '</p></div>' +
                '</div>');
            tag.find('input[name="genre"]').val(selectedOption);
            $('#list-genres').append(tag);
            $(this).find('option[value="' + selectedOption + '"]').remove();
        }
        $('#genre').val("");
    });
    
    $('#list-genres').on('click', 'input[name="genre"]', function () {
        var selectedGenre = $(this).val();
        var genreName = $(this).find('option:selected').text();
        if (selectedGenre) {
            $('#genre').append($('<option>', {
                value: selectedGenre,
                text: genreName
            }));
            $(this).closest('.checkbox').remove();
        }
    });

    $('#genre').change(function(){
        if(document.getElementsByTagName('genre').length > 3){
            $('#genre').attr('readonly',true);
        }
    })
    
    var countC = 0;
    var countM = 0;
    var countS = 0;
    $('input[name="culture"]').on('change', function () {
        console.log("fsdfds")
        if (this.checked) {
            if (countC < 3) {
                countC++;
            } else {
                this.checked = false;
            }
        } else {
            countC--;
        }
    });
    $('input[name="mood"]').on('change', function () {
        if (this.checked) {
            if (countM < 3) {
                countM++;
            } else {
                this.checked = false;
            }
        } else {
            countM--;
        }
    });
    $('input[name="style"]').on('change', function () {
        if (this.checked) {
            if (countS < 3) {
                countS++;
            } else {
                this.checked = false;
            }
        } else {
            countS--;
        }
    });
})