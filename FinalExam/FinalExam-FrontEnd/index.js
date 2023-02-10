// var listAccount = [];
// var listDepartment = [];

// var userLo = "dangblack";
// var passLo = "123456";
// // get listAccount
// function getListAccount() {
//   $.ajax({
//     type: "GET",
//     url: "http://localhost:8080/api/v1/accounts",
//     beforeSend: function (xhr) {
//       xhr.setRequestHeader(
//         "Authorization",
//         "Basic " + btoa(`${userLo}:${passLo}`)
//       );
//     },
//     success: function (response) {
//       listAccount = response.content;
//       // console.log(listAccount);
//       listAccount.forEach((acc) => {
//         $("#tbody").append(
//           `
//              <tr>
//                 <td>${acc.id}</td>
//                 <td>${acc.username}</td>
//                 <td>${acc.fullName}</td>
//                 <td>${acc.role}</td>
//                 <td>${acc.departmentName}</td>
//                 <td>
//                   <button type="button" class="btn btn-warning">Edit</button>
//                 </td>
//                 <td>
//                   <button type="button" class="btn btn-danger">Delete</button>
//                 </td>
//             <tr>
//             `
//         );
//       });
//     },
//   });
// }

// // get listDepartment
// function getListDepartment() {
//   $.ajax({
//     type: "GET",
//     url: "http://localhost:8080/api/v1/departments",
//     beforeSend: function (xhr) {
//       xhr.setRequestHeader(
//         "Authorization",
//         "Basic " + btoa(`${userLo}:${passLo}`)
//       );
//     },
//     success: function (response) {
//       listDepartment = response.content;
//       console.log(listDepartment);
//       // getdepartmetName
//       listDepartment.forEach((department) => {
//         $("#inputDepartment").append(
//           `<option value="">${department.name}</option>`
//         );
//       });
//     },
//   });
// }

// //method main trong js
// $(function () {
//   getListDepartment();
//   getListAccount();
// });
