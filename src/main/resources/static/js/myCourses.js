 $( document ).ready(function(){
    	loadMyCoursess();
    });

function loadMyCoursess(){
	console.log("w funkcji");
    $.get( "/u_courses", function (data) {
        if (data.length == 0){
            console.log('no data about user');
        }
        else{
        	var template = $('#courseTemplate').html();
        	var templateScript = Handlebars.compile(template);
        	
        	for (var i = 0; i < data.length; i++) {
        		var context = data[i];
        		var html = templateScript(context);
        		$('#courses').append(html);
        		console.log(data[i].userDTO.id)
        		$("#rate"+data[i].id).starRating({
        			  disableAfterRate: false,
        			  initialRating: data[i].userRate,
        			  useFullStars: true,
        			  useGradient: true,
        			  callback: function(currentRating, $el){
        			    //alert('rated ' + currentRating + 'id ' + $($el).attr("userid"));
        			    console.log('DOM element ', $el);
        			    console.log("current: " + currentRating);
        			    updateRate($($el).attr("plainId"), $($el).attr("userId"), currentRating)
        			  }
        			});
        	}
        	
        }
    });
    
    function updateRate(id, userid, rate) {
        var rate = {
            "trainingid": id,
            "userid": userid,
            "rate": rate,
        }

        $.ajax({
            url: './rate',
            type: "PUT",
            data: JSON.stringify(rate),
            contentType: "application/json; charset=utf-8",
            async: false,
            success: function (data) {
                console.log("done");
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log("error");
            }


        })


    }

}
