function loadTasks(){
    $.get( "/getTasks", function (data) {
        if (data.length == 0){
            console.log('no data about user');
        }
        else{
        	var template = $('#taskTemplate').html();
        	var templateScript = Handlebars.compile(template);
        	
        	for (var i = 0; i < data.length; i++) {
        		var context = data[i];
        		var html = templateScript(context);
        		$('#tasks').append(html);
        	}
        	
        }
    });
}