
function loadCourseData(id){
    $.get( "/course/"+id, function (data) {
        if (data.length == 0){

        }
        else{
                $('#courseName').empty().append(data.name);
                $('#coursePrice').empty().append(data.price);
                $('#courseDesciption').empty().append(data.description);
                $('#courseNameTwo').empty().append(data.name);
                $('#traningId123').empty().append(data.id);

            $('#courseContent').append("<ol id='courseDataList'></ol>")
            if(0 < data.contentList.length) {
                for (var i = 0; i < data.contentList.length; i++) {
                    $('#courseDataList').append("<li><p> <label>Description: </label><span class='courseItemDescription'>" +data.contentList[i].description + "</span><br/><label>Path: </label><a href='"+ data.contentList[i].path +"' class='courseItemPath'>Open content</a></p></li>");
                }
            }
            else{
                $('#courseContentaxdas').append("<p> This course has no content yet!</p>")
            }
        }
    });
}



function buyTraining(trainingId,userId){


    var buyTraining = {
        "training_id": trainingId,
        "user_id": userId,
    }
    $.ajax({
        url: '/buyTraining',
        type: "POST",
        data: JSON.stringify(buyTraining),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        error: function (xhr, ajaxOptions, thrownError) {
            $('#alertAddToCourse').show();

        }
    })
    console.log(course);


}