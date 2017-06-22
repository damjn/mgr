   $( document ).ready(function() {
	   console.log("test");
       loadMyCourses();
  });

function loadMyCourses(){
    $.get( "./t_courses", function (data) {
    			console.log("load courses")
              if (data.length == 0){
                $('#myCurses').append("Nie ma zadnych dostepnych kursow...");
              }
              else{
                               
                for (var i = 0; i < data.length; i++) {
                  $('#myCurses').append("<li class='curse'><ol>" +data[i].name+  "  <a data-toggle='modal' data-id='" + data[i].id + "'  data-target='#modalUpdate' class='update-Course' ><i class='fa fa-plus'/>Edytuj</a><a class='removeCourse' data-toggle='modal'  data-id='" + data[i].id + "' data-target='#myModal'><i class='fa fa-trash'></i>Usuń kurs!</a></ol></li>"); 
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


 $(document).on("click", ".update-Course", function (e) {
        e.preventDefault();
        var _self = $(this);
        var myBookId = _self.data('id');
        var value = $("#courseId").append(myBookId);
     
      $("#courseNamePopUp").val(data.user.username);
      $("#CourseDescPopUp").val(data.contactPhone);
    
       /* $.get(
                "./courses/" + myBookId,
                function (data) {
                    $("#courseNamePopUp").val(data.name);
                    $("#CourseDescPopUp").val(data.description);
                }); */
    });

function updateCurseData(id){
      var person = {
            name: $("#courseNamePopUp").val(),
            description:$("#courseDescPopUp").val(),
            id:id,
        }
console.log(person);
        $.ajax({
            url: '/curse/',
            type: 'put',
            dataType: 'json',
            success: function (data) {
                 $('#alertUpdate').show();
            },
            data: person
        });
    
}

function deleteCourse(id){
     //   var header = $("meta[name='_csrf_header']").attr("content");
    //    var token = $("meta[name='_csrf']").attr("content");
    
        $.ajax({
            url:'./courses/' + id,
            type: 'DELETE',
            success: function(data) {
                $('#alertDeleted').show();
                   loadMyCourses();

            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log('error occure');
                console.log(xhr.status + ": " + thrownError);
            }
        });
    };


$(function(){
    $("[data-hide]").on("click", function(){
        $(this).closest("." + $(this).attr("data-hide")).hide();
    });
});

