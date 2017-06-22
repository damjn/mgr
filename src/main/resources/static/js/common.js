function logout() {
    alert('logout');
            document.getElementById("logoutForm").submit();
        };



$(function(){
    $("[data-hide]").on("click", function(){
        $(this).closest("." + $(this).attr("data-hide")).hide();
    });
});