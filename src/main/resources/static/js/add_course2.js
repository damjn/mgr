//$(document).ready(function() {
//	$("li").each(function(index) {
//		$(this).attr('id', index + '-content');
//	});
//
//	$(function() {
//		$("#contentUL").sortable({
//			revert : true
//		});
//		$("#contentUL").draggable({
//			revert : "invalid",
//			start: function( event, ui ) {
//				alert("dziala!");
//				console.log("cos innego");
//			}
//		});
//		$("ul, li").disableSelection();
//	});
//	
//	$("#xxx").click(function() {
//		console.log("cos tam");
//		var listItem = $( "#1-content" );
//		alert( "Index: " + $( "li" ).index( listItem ) );
//	});
//	
//	$( "#1-content" ).on( "dragstop", function( event, ui ) {
//		alert("draglo sie");
//	} );
//});

function addCourse(){
    var courseName = $('#courseName').val();
    var courseDescription = $('#courseDescription').val();
    console.log('opis: ' +courseDescription);
    var coursePrice = $('#coursePrice').val();
    var json = {
        "name": courseName,
        "description": courseDescription,
        "price": coursePrice

    }

//    var header = $("meta[name='_csrf_header']").attr("content");
//    var token = $("meta[name='_csrf']").attr("content");
    $.ajax({
        url:'./addCourse2/',
        type: 'POST',
        async : true,
        contentType : "application/json",
        beforeSend: function(xhr){
//            xhr.setRequestHeader(header, token);
        },
        data : JSON.stringify(json),
        success: function(data) {
        	console.log('elo rescik');
//            $('#success').empty();
//            $('#success').append('<b>Pomyślnie utworzono konto nowego klienta, login: '+customerSurname.toLowerCase() + customerName.toLowerCase()+'</b>');
//            $('#alertSuccess').show();
//            $('#customerList').empty()
//            getCustomers();
        },
        error: function (xhr, ajaxOptions, thrownError) {
            console.log('error occure');
            console.log(xhr.status + ": " + thrownError);
        }
    });
}