$(document).ready(function () {
    getCoursesList('', false);

    $("#courseLookupButton").click(function (e) {
        e.preventDefault();
        var courseLookupValue = $('#findCourse').val();
        getCoursesList(courseLookupValue, true);
    });

});


function getCoursesList(searchWord, comingFromLookup) {
    $('#pageContent').empty();
    if (comingFromLookup === false) {
    }
    var webserviceURL = './courses';
    if (searchWord !== '') {
        webserviceURL += '/';
        webserviceURL += searchWord;
    }

    $.get(webserviceURL, function (data) {
        if (data.length == 0) {
            $('#pageContent').append("No courses avaliable..");
        }
        else {
            var set = new Set();
            for (var i = 0; i < data.length; i++) {
                $('#pageContent').append("<a href='/courseDetails/"+data[i].id +"'><div class='col-sm-2 col-md-2'><div class='thumbnail '> <img src='/images/sample.jpg' width='200px' height='200px'> <div class='caption'> <h3>" + data[i].name + "</h3><p>" + data[i].description + "</p> <p>" + data[i].averageRate + "</p></div> </div></div> </div></a> ");
                if (comingFromLookup === false) {

                }
            }
        }
    });

}

function getUserCoList(searchWord, comingFromLookup) {
	console.log("tutaj");
    $('#pageContent').empty();
    if (comingFromLookup === false) {
    }
    var webserviceURL = './u_courses';
    

    $.get(webserviceURL, function (data) {
    	console.log("tu");
        if (data.length == 0) {
            $('#pageContent').append("You have no courses..");
        }
        else {
            var set = new Set();
            for (var i = 0; i < data.length; i++) {
                $('#pageContent').append("<a href='/courseDetails/"+data[i].id +"'><div class='col-sm-2 col-md-2'><div class='thumbnail '> <img src='/images/sample.jpg' width='200px' height='200px'> <div class='caption'> <h3>" + data[i].name + "</h3><p>" + data[i].description + "</p> </div> </div></div> </div></a> ");
                if (comingFromLookup === false) {

                }
            }
        }
    });

}