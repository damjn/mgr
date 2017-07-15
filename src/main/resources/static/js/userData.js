function loadUserData(id){
    $.get( "/user/"+id, function (data) {
        if (data.length == 0){
            console.log('no data about user');
        }
        else{
            $('#userName').val(data.name);
            $('#userLastName').val(data.lastname);
            $('#userPoints').val(data.points);
            $('#userEmail').val(data.email);
        }
    });
}

function passwordValidation(){

    var newPass = $("#newPassword2").val();
    var newPass2 = $("#newPassword").val();
    if(newPass === newPass2){
        changePassword();
    }
    else{
        alert('Please provide new password twice!');
    }
}


function changePassword() {
    var email = $("#userEmail").val();
    var oldPass = $("#oldPassword").val();
    var newPass = $("#newPassword2").val();
    var pass = {
        "email": email,
        "oldPass": oldPass,
        "newPass": newPass
    }


    $.ajax({
        url: './user_pass',
        type: "PUT",
        data: JSON.stringify(pass),
        dataType: 'json',
        contentType: "application/json",
        success: function(result){
             $('#closeMe').click();
             $('#alertPassChanged').show();
            }
    });
}