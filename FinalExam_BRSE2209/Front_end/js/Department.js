var department;

function initDepartment(){
    $.ajax({
        url: 'http://localhost:8080/api/v1/department',
        type: 'GET',
        contentType: "application/json",
        dataType: 'json', // datatype return
        success: function (data, textStatus, xhr) {
            department = data;
            fillDepartmentToTable();
        },
        error(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function fillDepartmentToTable(){
    console.log(department);
    // $('tbody').empty();
    department.forEach(function (item) {
        $('tbody').append(
            '<tr >' +
            '<td>' + item.id + '</td>' +
            '<td>' + item.name + '</td>' +
            '<td>' + item.totalMember + '</td>' +
            '<td>' + item.type + '</td>' +
            '<td>' + item.createDate + '</td>' +
            '<td>' +
            '<i class="bi bi-plus"></i>' +
            '<i class="bi bi-trash" style="padding-left: 2em"></i>' +
            '<i class="bi bi-pencil" style="padding-left: 2em"></i>' +
            '</td>' +
            '</tr>'
        );
    });
}

