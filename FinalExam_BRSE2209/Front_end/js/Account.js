var account;
var isOpenCreateModel;
var idForUpdate;

function initAccount() {
    
    
    var url = "http://localhost:8080/api/v1/accounts";
    
    var filterRole = $("#filter-role").val();
    var filterDepartmentId = $("#filter-department").val();
    var username = $("#searchterm").val();
    
    if((filterRole != null && filterRole != "") || (filterDepartmentId != null && filterDepartmentId != "")||(username!= null && username != "")){
        url += "?";
    }
    

    if(filterRole != null && filterRole != ""){
        url += "role="+ filterRole 
    }

    if(filterDepartmentId != null && filterDepartmentId != ""){
        if(filterRole != null && filterRole != ""){
            url += "&";
        }
        url += "departmentId=" + filterDepartmentId;
    }

    if(username!= null && username != ""){
        if((filterRole != null && filterRole != "") && (filterDepartmentId != null && filterDepartmentId != "")){
            url += "&";
        }else if((filterRole != null && filterRole != "") || (filterDepartmentId != null && filterDepartmentId != "")){
            url += "&";
        }
        url+="username=" + username;
        
    }
    

    $.ajax({
        url: url,
        type: 'GET',
        contentType: "application/json",
        dataType: 'json', // datatype return
        success: function (data, textStatus, xhr) {
            account = data;
            fillAccountToTable();
        },
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });

}
function fillDepartmentToFilterDepartment(){
    $.ajax({
        url: 'http://localhost:8080/api/v1/department',
        type: 'GET',
        contentType: "application/json",
        dataType: 'json', // datatype return
        success: function (data, textStatus, xhr) {
            data.forEach(function(item) {
                $("#filter-department").append($('<option>',{
                    value: item.id,
                    text: item.name
                }))
            })},
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function fillAccountToTable() {
    console.log(account);
    $('tbody').empty();
    account.forEach(function (item, index) {
        $('tbody').append(
            '<tr >' +
            '<td><input type="checkbox" value="" id="delete-'+ index + '"></td>' +
            '<td>' + item.username + '</td>' +
            '<td>' + item.fullname + '</td>' +
            '<td>' + item.role + '</td>' +
            '<td>' + item.department.name + '</td>' +
            '<td>' +
            '<i class="bi bi-trash" onclick="deleteAccountById(' + item.id + ')"></i>' +
            '<i class="bi bi-pencil" style="padding-left: 2em;" onclick="openUpdateAccountModel(' + item.id + ')"></i>' +
            '</td>' +
            '</tr>'
        );
    });
}

function deleteAccountById(id) {
    var index = account.findIndex(item => item.id == id);
    var result = confirm("Want to delete " + account[index].username + "?");
    if (result) {
        $.ajax({
            url: 'http://localhost:8080/api/v1/accounts/' + id,
            type: 'DELETE',
            success: function (result) {
            showNotificationMessage("Bạn đã xoá thành công " +account[index].username+ "!")
            initAccount();
            }
        })
    }
}

function deleteAllAccounts(){
    var ids = [];
    var i= 0;
    var user=[];
    while (true) {
        if(document.getElementById("delete-"+i)!=null){
            if (document.getElementById("delete-"+i).checked){
                ids.push(account[i].id);
                user.push(account[i].username)
            }
            i++;
        }else{
            break;
        } 
    }
    var result = confirm("Want to delete" + " "+ user + "?");
    if(result)
         $.ajax({
             url: 'http://localhost:8080/api/v1/accounts?ids='+ ids,
             type: 'DELETE',
            success: function () {
            showNotificationMessage("Bạn đã xoá thành công " + user +  "!")
            initAccount();
             }
             
         })
     }
        
    


function openCreateAccountModel() {
    isOpenCreateModel = true;

    // set title for model
    $("#modal-title").text("Add Account");
    fillDepartmentToCreateAccountModal();
    resetCreateForm();
    showModel();
    
}

function fillDepartmentToCreateAccountModal(){
    $.ajax({
        url: 'http://localhost:8080/api/v1/department',
        type: 'GET',
        contentType: "application/json",
        dataType: 'json', // datatype return
        success: function (data, textStatus, xhr) {
            $("#select-department").empty();
            data.forEach(function(item) {
                $("#select-department").append($('<option>',{
                    value: item.id,
                    text: item.name
                }))
            })},
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function resetCreateForm() {
    document.getElementById("username").disabled = false;
    $("#username").val("");
    $("#firstname").val();
    $("#lastname").val();
    $("#select-role").val("");
    $("#select-department").val("");
}

function showModel() {
    $("#myModal").modal('show');
}

function hideModel() {
    $("#myModal").modal('hide');
}

function createAccount() {
    
    // VALIDATION
    var username=$("#username").val();
    var firstname=$("#firstname").val();
    var lastname=$("#lastname").val();
    var role=$("#select-role").val();
    var departmentId=$("#select-department").val();

    if(username == "" || username.length < 6 || username.length > 30){
        $("#usernameErrorMessage").show();
        return;
    }else{
        $("#usernameErrorMessage").hide();
    }
    if(firstname ==""){
        $("#firstnameErrorMessage").show();
        return;
    }else{
        $("#firstnameErrorMessage").hide();
    }

    if(lastname == ""){
        $("#lastnameErrorMessage").show();
        return;
    }else{
        $("#lastnameErrorMessage").hide();
    }

    if(role == "" || (role != "ADMIN" && role != "MANAGER" && role != "EMPLOYEE")){
        $("#roleErrorMessage").show();
        return;
    }else{
        $("#roleErrorMessage").hide();
    }

    if(departmentId == "" || departmentId < 1 || departmentId > 10){
        $("#departmentErrorMessage").show();
        return;
    }else{
        $("#departmentErrorMessage").hide();
    }


    var newAccount = {
        username: username,
        firstname: firstname,
        lastname:lastname,
        role: role,
        departmentId:departmentId,
        
    }
    $.ajax({
        url: 'http://localhost:8080/api/v1/accounts',
        type: 'POST',
        data: JSON.stringify(newAccount), // body
        contentType: "application/json", // type of body (json, xml, text)
        success: function (data, textStatus, xhr) {
            // showNotification("create successfully!");
            showNotificationMessage("Bạn đã tạo thành công")
            hideModel();
            initAccount();
            
        },
        error(jqXHR, textStatus, errorThrown) {
            alert("Error when loading data");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function openUpdateAccountModel(id) {
    idForUpdate = id;
    isOpenCreateModel = false;
     // set title for model
    $("#modal-title").text("Update Account");
    fillDepartmentToUpdateModal(id);
    showModel();
    
}

function fillDepartmentToUpdateModal(id){
    $.ajax({
        url: 'http://localhost:8080/api/v1/department',
        type: 'GET',
        contentType: "application/json",
        dataType: 'json', // datatype return
        success: function (data, textStatus, xhr) {
            console.log(data);
            $("#select-department").empty();
            data.forEach(function(item) {
                $("#select-department").append($('<option>',{
                    value: item.id,
                    text: item.name
                }));
            });
         fillDetailAccountToUpdateModal(id);
        },
           
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function fillDetailAccountToUpdateModal(id){
    $.ajax({
    url: 'http://localhost:8080/api/v1/accounts/'+ id,
    type: 'GET',
    contentType: "application/json",
    dataType: 'json', // datatype return
    success: function (data, textStatus, xhr) {
        console.log(data);
        document.getElementById("username").disabled = true;
        $("#username").val(data.username);
        $("#firstname").val(data.firstname);
        $("#lastname").val(data.lastname);
        $("#select-role").val(data.role);
        $("#select-department").val(data.department.id);
    },
       
    error(jqXHR, textStatus, errorThrown) {
        console.log(jqXHR);
        console.log(textStatus);
        console.log(errorThrown);
    }
    });
}


function saveAccount() {
    if (isOpenCreateModel) {
        createAccount();
    } else {
        updateAccount();
    }
}


function updateAccount() {
    
    var username = $("#username").val();
    var firstname = $("#firstname").val();
    var lastname = $("#lastname").val();
    var role = $("#select-role").val();
    var departmentId = $("#select-department").val();

    if(username == "" || username.length < 6 || username.length > 30){
        $("#usernameErrorMessage").show();
        return;
    }else{
        $("#usernameErrorMessage").hide();
    }
    if(firstname ==""){
        $("#firstnameErrorMessage").show();
        return;
    }else{
        $("#firstnameErrorMessage").hide();
    }

    if(lastname == ""){
        $("#lastnameErrorMessage").show();
        return;
    }else{
        $("#lastnameErrorMessage").hide();
    }

    if(role == ""){
        $("#roleErrorMessage").show();
        return;
    }else{
        $("#roleErrorMessage").hide();
    }

    if(departmentId == "" || departmentId < 1 || departmentId > 10){
        $("#departmentErrorMessage").show();
        return;
    }else{
        $("#departmentErrorMessage").hide();
    }
    var newAccount = {
        firstname: $("#firstname").val(),
        lastname: $("#lastname").val(),
        role: $("#select-role").val(),
        departmentId: $("#select-department").val()
    }
    
    $.ajax({
        url: 'http://localhost:8080/api/v1/accounts/' + idForUpdate,
        type: 'PUT',
        data: JSON.stringify(newAccount), // body
        contentType: "application/json", // type of body (json, xml, text)
        success: function (data, textStatus, xhr) {
            // showNotification("create successfully!");
            showNotificationMessage("Bạn đã update thành công")
            hideModel();
            initAccount();
        },
        error(jqXHR, textStatus, errorThrown) {
            alert("Error when loading data");
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function showNotificationMessage(message) {
    // Get the snackbar DIV
    var x = document.getElementById("snackbar");
    // set message
    x.innerHTML = message;
    // Add the "show" class to DIV
    x.className = "show";
  
    // After 3 seconds, remove the show class from DIV
    setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
}
