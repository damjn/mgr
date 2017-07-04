function logout() {
        $.ajax({
            url : 'logout',
            type : 'POST',
            success : function(data) {
                window.location ="/index_l";
            },
            error : function(data) {
                console.log(data);
            }
        });

};



$(function(){
    $("[data-hide]").on("click", function(){
        $(this).closest("." + $(this).attr("data-hide")).hide();
    });
});