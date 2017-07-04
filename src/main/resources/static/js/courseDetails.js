
function loadCourseData(id){
    $.get( "/course/"+id, function (data) {
        if (data.length == 0){

        }
        else{
                $('#courseName').empty().append(data.name);
                $('#coursePrice').empty().append(data.price);
                $('#courseDesciption').empty().append(data.description);
                $('#courseNameTwo').empty().append(data.name);

            $('#courseContent').append("<ol id='courseDataList'></ol>")
            if(0 < data.contentList.length) {
                for (var i = 0; i < data.contentList.length; i++) {
                    $('#courseDataList').append("<li><p> <label>Opis:</label><span class='courseItemDescription'>" +data.contentList[i].description + "</span><br/><label>Zrodlo:</label><a href='"+ data.contentList[i].path +"' class='courseItemPath'>Przejdz do materialu</a></p></li>");
                }
            }
            else{
                $('#courseContentaxdas').append("<p> Na ta chwile brak materialow dla kursu!</p>")
            }
        }
    });
}
