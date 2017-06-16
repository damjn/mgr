jQuery(document).ready(function($) {
	console.log("document ready!");
	getCourses();
});

function getCourses() {
	
			$.get(
					"./t_courses/",
					function(data) {
						//console.log("tescik: " + data[1].name)
						if (data.length == 0) {
							$('#coursesList').append(
									"You dont have any courses...");
						} else {
							for (var i = 0; i < data.length; i++) {
								console.log("nazwa: " + data[i].name)
								$('#coursesList')
										.append(
												"<div class='col-md-3' style='background-color:#EEE; margin:20px 0;'><p class='list-group-item' style='margin:10px;'><span class='fa-user'/> <a data-toggle='modal'  data-id='"
														+ data[i].id
														+ "'   data-target='#modalUpdate' class='update-Customer' style='margin:0 10px;'><i class='fa fa-pencil-square-o'></i></a><a class='open-AddBookDialog' data-toggle='modal'  data-id='"
														+ data[i].id
														+ "' data-target='#myModal' style='margin:0 10px;'><i class='fa fa-trash'></i></a><br/><b>Course name: </b>"
														+ data[i].name
														+ "<br/> <b>Description: </b>"
														+ data[i].description
														+ "<br/> <b> Price: </b>"
														+ data[i].price
														+ "</br> </p></div>");
							}
						}
					});
}

function getCourses() {
	
	$.get(
			"./t_courses/",
			function(data) {
				//console.log("tescik: " + data[1].name)
				if (data.length == 0) {
					$('#coursesList').append(
							"You dont have any courses...");
				} else {
					for (var i = 0; i < data.length; i++) {
						console.log("nazwa: " + data[i].name)
						$('#coursesList')
								.append(
										"<div class='col-md-3' style='background-color:#EEE; margin:20px 0;'><p class='list-group-item' style='margin:10px;'><span class='fa-user'/> <a data-toggle='modal'  data-id='"
												+ data[i].id
												+ "'   data-target='#modalUpdate' class='update-Customer' style='margin:0 10px;'><i class='fa fa-pencil-square-o'></i></a><a class='open-AddBookDialog' data-toggle='modal'  data-id='"
												+ data[i].id
												+ "' data-target='#myModal' style='margin:0 10px;'><i class='fa fa-trash'></i></a><br/><b>Course name: </b>"
												+ data[i].name
												+ "<br/> <b>Description: </b>"
												+ data[i].description
												+ "<br/> <b> Price: </b>"
												+ data[i].price
												+ "</br> </p></div>");
					}
				}
			});
}

