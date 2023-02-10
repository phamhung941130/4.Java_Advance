$(function () {
    $("#header").load("../html/header.html");
    $("#footer").load("../html/footer.html");
    $("#body").load("../html/homepage.html")
});

function onclickAccount() {
    $("#body").load("../html/account.html", function () {
        initAccount();
        fillDepartmentToFilterDepartment();
    });
}
function onclickDepartment() {
    $("#body").load("../html/department.html", function () {
        initDepartment();
    });
}

function onclickHomepage() {
    $("#body").load("../html/homepage.html")
}


