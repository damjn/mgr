   $( document ).ready(function() {
      getCoursesList('mama',false);
       
       $("#courseLookupButton").click(function(e){
           e.preventDefault();
           var courseLookupValue = $('#findCourse').val();
           getCoursesList(courseLookupValue,true);
  });
                   
        });



function getCoursesList(searchWord, comingFromLookup){
    $('#pageContent').empty();
    if(comingFromLookup === false)
    {
            $('#submenu2').empty();
    }
    var webserviceURL='';
    if(searchWord !== ''){
        webserviceURL += searchWord;
    }
    
         $.get( "./courses", function (data) {
              if (data.length == 0){
                $('#pageContent').append("Nie ma zadnych dostepnych kursow...");
              }
              else{
                  var set = new Set();
                for (var i = 0; i < data.length; i++) {
                  $('#pageContent').append("<a href='#home'><div class='col-sm-2 col-md-2'><div class='thumbnail'> <img src='/images/sample.jpg' width='200px' height='200px'> <div class='caption'> <h3>"+data[i].name+"</h3><p>"+data[i].description+"</p> </div> </div></div> </div></a> ");
                     if(comingFromLookup === false){
                    if(set.add(data[i].category)){
                         $('#submenu2').append("<li><a>"+ data[i].category.toUpperCase()+ "</a></li> ");
                    }
                     }
                }
              }
            });
    
}