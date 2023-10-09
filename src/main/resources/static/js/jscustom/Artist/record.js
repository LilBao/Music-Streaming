$('#genre').on('change', function () {
    var selectedOption = $(this).val();
    if (selectedOption) {
        var tag = $('<div class="checkbox"> <input type="checkbox" checked name="genre" id="" />' +
            '<div class="box bg-black text-white"><p>' + selectedOption + '</p></div>' +
            '</div>');
        tag.find('input[name="genre"]').val(selectedOption);
        $('#list-genres').append(tag);
        $(this).find('option[value="' + selectedOption + '"]').remove();
    }
    $('#genre').val("");
});

$('#list-genres').on('click', 'input[name="genre"]', function () {
    var selectedGenre = $(this).val();
    if (selectedGenre) {
        $('#genre').append($('<option>', {
            value: selectedGenre,
            text: selectedGenre
        }));
        $(this).closest('.checkbox').remove();
    }
});

var countC = 0;
var countM = 0;
var countS = 0;
$('input[name="culture"]').on('change', function () {
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

