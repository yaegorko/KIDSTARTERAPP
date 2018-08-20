$(document).ready(function () {
    fillusertable();
});

function fillusertable(){
    $("#table_body_userlist").empty();
    $.ajax({
        type: "GET",
        url: "./users",
        datatype: "JSON",
        success: function (response) {
            for (var i in response) {
                var roles = [];
                for (var j in response[i].roles) {
                    roles.push(response[i].roles[j].name);
                }
                $("#table_body_userlist").append("<tr><td>" + response[i].id + "</td><td>" +
                    response[i].lastName + "</td><td>" +
                    response[i].firstName + "</td><td>" +
                    response[i].patronymic + "</td><td>" +
                    response[i].login + "</td><td>" +
                    roles + "</td><td>" +
                    response[i].createDate + "</td><td>" +
                    response[i].age + "</td><td>" +
                    response[i].sex + "</td><td>" +
                    response[i].phone + "</td><td>" +
                    response[i].email + "</td><td>" +
                    response[i].country + "</td><td>" +
                    response[i].address + "</td><td>" +
                    response[i].enabled + "</td><td>" +
                    "<form class='navbar-form' onsubmit='edit_user(" + response[i].id + ");return false'><input class='form-control' type='submit' value='Изменить'></form></td><td>" +
                    "<form class='navbar-form' onsubmit='delete_user(" + response[i].id + ");return false'><input class='form-control' type='submit' value='Удалить'></form></td><td>" +
                    "</tr>");
            }
        }
    })
}

function edit_user(userid) {
    console.log("edit");
    console.log(userid);
}

function delete_user(userid) {
    var url = "./user/" + userid;
    $.ajax({
        type: "DELETE",
        url: url,
        datatype: "JSON",
        success: function () {
            fillusertable();
        }
    });
}