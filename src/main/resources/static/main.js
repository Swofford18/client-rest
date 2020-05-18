function ajaxGetAllUsers() {
    $.ajax({
        type : "GET",
        url : "http://localhost:8088/allUsers",
        headers : {
            'Authorization' : 'Basic YWRtaW5YOmFkbWluWA=='
        },
        success : function(data) {

            for (var i = 0; i < data.length; i++) {

                var roles = "";

                for (var j = 0; j < data[i].roles.length; j++) {
                    var role = data[i].roles[j].name;
                    var toRemove = 'ROLE_';
                    var r = role.replace(toRemove,'');
                    roles += r + " ";
                }

                var user = "<tr>" +
                    "<th>" + data[i].id + "</th>" +
                    "<td>" + data[i].username + "</td>" +
                    "<td>" + data[i].email + "</td>" +
                    "<td>" + roles + "</td>" +
                    "<td><button class=\"edit-but btn btn-info\" value=" + data[i].id + ">Edit</button></td>" +
                    "<td><button class=\"delete-but btn btn-danger\" value=" + data[i].id + ">Delete</button></td>" +
                    "</tr>";
                $("#tbody").append(user)
            }

        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function fillEditInputs(id) {
    $.ajax({
        type : "GET",
        url : "http://localhost:8088/getOne?id=" + id,
        headers : {
            'Authorization' : 'Basic YWRtaW5YOmFkbWluWA=='
        },
        success : function(data) {
            $('#idEdit').val(data.id);
            $('#usernameEdit').val(data.username);
            $('#passwordEdit').val(data.password);
            $('#emailEdit').val(data.email);


            $('#editModal').modal();

            console.log("success: ", data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function fillDeleteInputs(id) {
    $.ajax({
        type : "GET",
        url : "http://localhost:8088/getOne?id=" + id,
        headers : {
            'Authorization' : 'Basic YWRtaW5YOmFkbWluWA=='
        },
        success : function(data) {
            $('#idDel').val(data.id);
            $('#usernameDel').val(data.username);
            $('#passwordDel').val(data.password);
            $('#emailDel').val(data.email);


            $('#deleteModal').modal();

            console.log("success: ", data);
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function ajaxPostCreateUser() {

    // PREPARE FORM DATA
    var formData = {
        username : $("#username").val(),
        password : $("#password").val(),
        email : $("#email").val(),
        roles : $("#createRoles").val()
    }

    // DO POST
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "http://localhost:8088/addUser",
        data : JSON.stringify(formData),
        dataType : 'json',
        headers : {
            'Authorization' : 'Basic YWRtaW5YOmFkbWluWA=='
        },
        success : function() {
            alert("User added");
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function ajaxPutEditUser() {

    // PREPARE FORM DATA
    var formData = {
        id : $("#idEdit").val(),
        username : $("#usernameEdit").val(),
        password : $("#passwordEdit").val(),
        email : $("#emailEdit").val(),
        roles : $("#editRoles").val()
    }

    // DO POST
    $.ajax({
        type : "PUT",
        contentType : "application/json",
        url : "http://localhost:8088/editUser",
        data : JSON.stringify(formData),
        dataType : 'json',
        headers : {
            'Authorization' : 'Basic YWRtaW5YOmFkbWluWA=='
        },
        success : function() {
            console.log("success!");
            refreshTable();
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function ajaxDeleteUser() {

    // PREPARE FORM DATA
    var formData = {
        id : $("#idDel").val(),
        username : $("#usernameDel").val(),
        password : $("#passwordDel").val(),
        email : $("#emailDel").val()
    }

    $.ajax({
        type : "DELETE",
        contentType : "application/json",
        url : "http://localhost:8088/deleteUser",
        data : JSON.stringify(formData),
        dataType : 'json',
        headers : {
            'Authorization' : 'Basic YWRtaW5YOmFkbWluWA=='
        },
        success : function() {
            console.log("success!");
            refreshTable();
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function refreshTable() {
    $("#tbody").empty();
    ajaxGetAllUsers();
}


$(document).ready(
    function () {

        ajaxGetAllUsers();

        $("#createForm").submit(function(event) {
            // Prevent the form from submitting via the browser.
            event.preventDefault();
            ajaxPostCreateUser();
        });

        $("#nav-home-tab").click(function () {
            refreshTable();
        });

        $('#tbody').on('click', '.edit-but', function(){
            var id = $(this).val();
            fillEditInputs(id)
        });

        $("#editForm").submit(function (event) {
            event.preventDefault();
            ajaxPutEditUser();

            $('#editModal').modal('hide');
        });

        $('#tbody').on('click', '.delete-but', function(){
            var id = $(this).val();
            fillDeleteInputs(id)
        });

        $("#deleteForm").submit(function (event) {
            event.preventDefault();
            ajaxDeleteUser();

            $('#deleteModal').modal('hide');
        });
})