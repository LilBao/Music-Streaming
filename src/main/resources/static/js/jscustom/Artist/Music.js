$(document).ready(function () {
    $('#myTab a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
    
})
var currentTab = 0;
document.addEventListener("DOMContentLoaded", function (event) {
    showTab(currentTab);
    $('#current-tab').innerText = currentTab + 1;
});

$('.btnPitch').click(function(){
    console.log("skndjfhkdsj")
})

function showTab(n) {
    var x = document.getElementsByClassName("tab");
    x[n].style.display = "block";
    if (n == 0) {
        document.getElementById("prevBtn").style.display = "none";
    } else {
        document.getElementById("prevBtn").style.display = "inline";
    }
    if (n == (x.length - 2)) {
        document.getElementById("nextBtn").innerHTML = "Submit";
    }else if(n > (x.length - 2)){
        $("#nextBtn").addClass("submit");
       $("#nextBtn").hide();
       $("#prevBtn").hide();
    }else {
        document.getElementById("nextBtn").innerHTML = "Next";
    }
    fixStepIndicator(n)
}

function nextPrev(n) {
    var x = document.getElementsByClassName("tab");
    x[currentTab].style.display = "none";
    currentTab = currentTab + n;
    if (currentTab >= x.length) {
        document.getElementById("nextprevious").style.display = "none";
        document.getElementById("all-steps").style.display = "none";
        document.getElementById("text-message").style.display = "block";
    }
    showTab(currentTab);
}

function fixStepIndicator(n) {
    var i, x = document.getElementsByClassName("step");
    for (i = 0; i < x.length; i++) {
        x[i].className = x[i].className.replace("active", "");
    }
    if(n>4)(
        x[n].className += " active"
    )
   
}

//Tag Genre
$('#genre').on('change', function () {
    var selectedOption = $(this).val();
    if (selectedOption) {
        var tag = $('<div class="checkbox"> <input type="checkbox" name="genre" id="" />' +
            '<div class="box bg-black text-white"><p>' + selectedOption + '</p></div>' +
            '</div>');
        tag.find('input[type="checkbox"]').val(selectedOption);
        $('#list-genres').append(tag);
        $(this).find('option[value="' + selectedOption + '"]').remove();
    }
    $('#genre').val("");
});

$('#list-genres').on('click', 'input[type="checkbox"]', function () {
    var selectedGenre = $(this).val();
    if (selectedGenre) {
        $('#genre').append($('<option>', {
            value: selectedGenre,
            text: selectedGenre
        }));
        $(this).closest('.checkbox').remove();
    }
});