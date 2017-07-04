$(document).ready(function () {
    loadMyCourses();
});

function loadMyCourses() {
    $.get("./t_courses", function (data) {
        $('#myCurses').empty();
        console.log("load courses")
        if (data.length == 0) {
            $('#myCurses').append("Nie ma zadnych dostepnych kursow...");
        }
        else {
            for (var i = 0; i < data.length; i++) {
                $('#myCurses').append("<li class='curse'> <label>Nazwa kursu:<a href='/courseDetails/"+data[i].id+"' class='courseLink'>" + data[i].name + " </a></label> <br/> <b>Opis</b>:" + data[i].description + " <br/> <b>Cena:</b> " + data[i].price + "zl.<a data-toggle='modal' data-id='" + data[i].id + "'  data-target='#modalUpdate' class='update-Course'><i class='fa fa-plus'/><br/>Edytuj</a>.<a data-toggle='modal' data-id='" + data[i].id + "'  data-target='#modalAddContent' class='add-Content'>Dodaj content!</a> <p id='courseContent"+data[i].id+"'></p></li>  <hr>" );
                loadCourseData(data[i].id);
            }
        }
    });
}

$(document).on("click", ".removeCourse", function (e) {
    e.preventDefault();
    var _self = $(this);
    var myBookId = _self.data('id');
    var value = $("#courseId2").append(myBookId);

});




$(document).on("click", ".add-Content", function (e) {
    e.preventDefault();
    var _self = $(this);
    var myBookId = _self.data('id');
    var ta = document.getElementById('trainingId');
    ta.value = myBookId;
    alert(ta.value);
});


function addNewCourse() {
    var name = $("#courseName").val();
    var description = $("#courseDesc").val();
    var price = $("#coursePrice").val();
    var category = $("#courseCategory").val();

    var course = {
        "name": name,
        "description": description,
        "price": price,
        "category": category
    }
    alert('category' +category);
    $.ajax({
        url: './course?user_id=1',
        type:"POST",
        data: JSON.stringify(course),
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        error: function (xhr, ajaxOptions, thrownError) {
            $('#alertAdd').show();
            loadMyCourses();
        }
    })
    console.log(course);
}


$(document).on("click", ".update-Course", function (e) {
    e.preventDefault();
    var _self = $(this);
    var myBookId = _self.data('id');
    var value = $("#courseId").empty().append(myBookId);
     $.get(
     "./course/" + myBookId,
     function (data) {
     $("#courseNamePopUp").val(data.name);
     $("#courseDescPopUp").val(data.description);
     $("#coursePricePopUp").val(data.price);
     $("#courseCategoryPopUp").val(data.category);
     });
});

$(document).on("click", ".add-Course", function (e) {
    e.preventDefault();
    var _self = $(this);
    var myBookId = _self.data('id');
    $("#courseId3").empty().append(myBookId);
});

$(document).on("click", ".deleteContentModal2", function (e) {
    e.preventDefault();
    var _self = $(this);
    var myBookId = _self.data('id');
    $("#deleteContentId").empty().append(myBookId);
});





function updateCurseData(id) {

    var name = $("#courseNamePopUp").val();
    var description =  $("#courseDescPopUp").val();
    var price = $("#coursePricePopUp").val();
    var category = $("#courseCategoryPopUp").val();

    var course = {
        "id":id,
        "name": name,
        "description": description,
        "price": price,
        //"category":$("#courseCategory").val(),
        "category": category

    }

    $.ajax({
        url: './course',
        type:"PUT",
        data: JSON.stringify(course),
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        async: false,
        success: function(data) {
            alert('siema');
            $('#alertUpdate').show();
        },
        error: function (xhr, ajaxOptions, thrownError) {
            $('#alertUpdate').show();
            loadMyCourses();
        }


    })





}


function deleteContent(id) {

    $.ajax({
        url: './content/' + id,
        type: 'DELETE',
        success: function (data) {
            $('#alertContentDelete').show();
            loadMyCourses();

        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log('error occure');
            console.log(xhr.status + ": " + thrownError);
        }
    });
};

function deleteCourse(id) {

    $.ajax({
        url: './courses/' + id,
        type: 'DELETE',
        success: function (data) {
            $('#alertDeleted').show();
            loadMyCourses();

        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log('error occure');
            console.log(xhr.status + ": " + thrownError);
        }
    });
};


$(function () {
    $("[data-hide]").on("click", function () {
        $(this).closest("." + $(this).attr("data-hide")).hide();
    });
});


function loadCourseData(id){
    $.get( "/course/"+id, function (data) {
        if (data.length == 0){

        }
        else{
            $('#courseContent' +id).append("<ol id='courseDataList"+id+"'></ol>")
            if(0 < data.contentList.length) {
                console.log('here you go' + id )
                for (var i = 0; i < data.contentList.length; i++) {
                    $('#courseDataList'+id).append("<li><p> <label>Opis:</label><span class='courseItemDescription'>" +data.contentList[i].description + "</span><br/><label>Zrodlo:</label><a data-toggle='modal' data-id='" + data.contentList[i].id + "'  data-target='#deleteContentModal' class='deleteContentModal2'>Usun material</a>    </p></li>");
                }
            }
            else{
                $('#courseContent' +id).append("<p> Na ta chwile brak materialow dla kursu!</p>")
            }
        }
    });
}