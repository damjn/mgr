$(document).ready(function() {
	$("li").each(function(index) {
		$(this).attr('id', index + '-content');
	});

	$(function() {
		$("#contentUL").sortable({
			revert : true
		});
		$("#contentUL").draggable({
			revert : "invalid",
			start: function( event, ui ) {
				alert("dziala!");
				console.log("cos innego");
			}
		});
		$("ul, li").disableSelection();
	});
	
	$("#xxx").click(function() {
		console.log("cos tam");
		var listItem = $( "#1-content" );
		alert( "Index: " + $( "li" ).index( listItem ) );
	});
	
	$( "#1-content" ).on( "dragstop", function( event, ui ) {
		alert("draglo sie");
	} );
});

function updateOrder(){
	// alert("draglo sie");
}