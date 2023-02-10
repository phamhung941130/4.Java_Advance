const manager = document.querySelector(".manager");
const user = document.querySelector(".user");
const content = document.querySelector("#content");
const modalContainer = document.querySelector(".modal-container");
const modalTitle = document.querySelector(".modal-title");
const saveBtn = document.querySelector(".btn-primary");
const loginBtn = document.querySelector(".login");
const menuBtn = document.querySelector(".menu");
const ManagerHTML = ` <h3>
Manager Group <ion-icon name="people-circle-outline"></ion-icon>
</h3>
<div class="list_group">
<p>List Group</p>
<div class="list_navbar">
<div class="list_search">
<input
type="text"
name="search"
placeholder="Search"
id=""
const user = document.querySelector(".user");
      class="input_search"
    />
  </div>
  <div class="list_flow">
    <div class="flow_icon add_btn">
      <ion-icon
        style="margin: -5px 6px"
        name="add-circle-outline"
      ></ion-icon>
    </div>
    <div class="flow_icon update_btn">
      <ion-icon
        style="margin: -5px 6px"
        name="refresh-circle-outline"
      ></ion-icon>
    </div>
    <div class="flow_icon delete_btn">
      <ion-icon
        style="margin: -5px 6px"
        name="trash-outline"
      ></ion-icon>
    </div>
  </div>
</div>
<div class="list_table">
  <table  class = "table_header">
    <tr>
      <th>Id</th>
      <th>Group Name</th>
      <th>Member</th>
      <th>Creator</th>
      <th>Create Date</th>
    </tr>
  </table>
</div>
</div>
`;
let listGroups = [];
const modalFooter = document.querySelector(".modal-footer");
function getListGroups() {
  fetch("http://localhost:8080/api/final-exam/groups/")
    // error
    .then((response) => {
      return response.json();
    })
    .then((response) => {
      return response.content;
    })
    .then((data) => {
      listGroups = [];
      let tcol = document.querySelector(".table_header");
      data.forEach((item) => {
        listGroups.push(item);
        let trow = document.createElement("tr");
        for (let i = 0; i < listGroups.length; i++) {
          trow.innerHTML = `
                              <td>${listGroups[i].id}</td>
                              <td>${listGroups[i].groupName}</td>
                              <td>${listGroups[i].member}</td>
                              <td>${listGroups[i].creator}</td>
                              <td>${listGroups[i].createDate}</td>
                              <td style="width: 150px"><button onclick="handleUpdateGroup(${listGroups[i].id})">Update</button>  
                              <button onclick="handleDeleteGroup(${listGroups[i].id})">Delete</button></td>                            `;
          tcol.appendChild(trow);
        }
      });
    });
}
function buildListGroups() {
  getListGroups();
  content.innerHTML = ManagerHTML;

  const addGroup = document.querySelector(".add_btn");
  const updateGroup = document.querySelector(".update_btn");
  const deleteGroup = document.querySelector(".delete_btn");

  addGroup.onclick = () => addGroupFunc();

  updateGroup.onclick = () => {
    updateGroupFunc();
    const inputText = document.querySelector("#id");
    inputText.attributes.removeNamedItem("disabled");
    inputText.attributes.removeNamedItem("value");
    inputText.setAttribute("placeholder", "ID");
    modalContainer.querySelector("#id").focus();
  };

  deleteGroup.onclick = () => deleteGroupFunc();
}
user.onclick = () => {
  content.innerHTML = "<h1>Welcome to User</h1>";
};
manager.onclick = () => {
  buildListGroups();
};
loginBtn.onclick = () => {
  showLoginmodal();
};
function addGroupFunc() {
  modalContainer.innerHTML = `<div class="group-name">
    <label for="name"><b>Group Name</b></label>
    <input
        type="text"
        placeholder="Enter Name"
        name="name"
        id="name"
        required
    />
    </div>
    <div class="group-member">
    <label for="member"><b>Member</b></label>
    <input
        type="text"
        placeholder="Enter Member"
        name="member"
        id="member"
    />
    </div>`;
  showModal();
  modalTitle.innerHTML = "Add Group";
  saveBtn.innerHTML = "Create";
  modalContainer.querySelector("#name").focus();
}
function updateGroupFunc(id) {
  modalContainer.innerHTML = `
    <div class="group-id">
    <label for="name"><b>ID</b></label>
    <input
        type="text"
        placeholder="${id}"
        name="name"
        id="id"
        value="${id}"
        required
        disabled 
    />
    </div>
    <div class="group-name">
    <label for="name"><b>Group Name</b></label>
    <input
        type="text"
        placeholder="Enter Name"
        name="name"
        id="name"
        required
    />
    </div>
    <div class="group-member">
    <label for="member"><b>Member</b></label>
    <input
        type="text"
        placeholder="Enter Member"
        name="member"
        id="member"
    />
    </div>`;
  showModal();
  modalTitle.innerHTML = "Update Group";
  saveBtn.innerHTML = "Save";
  modalContainer.querySelector("#name").focus();
}
function deleteGroupFunc() {
  modalContainer.innerHTML = `
    <div class="group-id">
    <label for="name"><b>ID</b></label>
    <input
        type="text"
        placeholder="Enter ID"
        name="name"
        id="id"
        required
    />
    </div>`;
  showModal();
  modalTitle.innerHTML = "Delete Group";
  saveBtn.innerHTML = "Delete";
  modalContainer.querySelector("#id").focus();
}
function showModal() {
  document.querySelector("#modal").style.top = "11%";
  //   Close the modal window
  const closeBtn = document.querySelector(".close");
  const closeButton = document.querySelector(".cancle");
  closeButton.onclick = () => closeModal();
  closeBtn.onclick = () => closeModal();
}
function closeModal() {
  document.querySelector("#modal").style.top = "-100%";
}
function postRequest(url, data) {
  fetch(url, {
    method: "POST",
    headers: new Headers({ "Content-Type": "application/json" }),
    body: JSON.stringify(data),
  })
    .then((response) => response)
    .then(() => {
      buildListGroups();
    });
}
function idIsExists(url, data) {
  fetch(url, {
    method: "PUT",
    headers: new Headers({ "Content-Type": "application/json" }),
    body: JSON.stringify(data),
  })
    .then((response) => response.json())
    .then((response) => {
      return response;
    })
    .then((response) => {
      if (response == true) {
        handleDeleteGroup(data.id);
      } else {
        window.alert("ID nhập vào không tồn tại. Vui lòng nhập đúng ID ");
      }
    });
}
function getData() {
  let creator = "";
  let groupName = modalContainer.querySelector("#name").value;
  let member = modalContainer.querySelector("#member").value;

  if (member === undefined || member === "") {
    member = 0;
  }
  let data = {
    groupName: groupName,
    member: member,
    creator: creator,
  };
  return data;
}
function login(loginUrl, data) {
  fetch(loginUrl, {
    method: "POST",
    headers: new Headers({ "Content-Type": "application/json" }),
    body: JSON.stringify(data),
  })
    .then((response) => response.json())
    .then((data) => {
      localStorage.setItem("username", data.userName);
    });
}
saveBtn.onclick = () => {
  closeModal();
  if (modalTitle.innerHTML.includes("Add")) {
    let data = getData();
    data.creator = localStorage.getItem("username");
    postRequest(`http://localhost:8080/api/final-exam/groups`, data);
  }
  if (modalTitle.innerHTML.includes("Update")) {
    let id = modalContainer.querySelector("#id").value;
    let data = getData();
    data.id = id;
    checkIdUpadate(data);
  }
  if (modalTitle.innerHTML.includes("Delete")) {
    let id = modalContainer.querySelector("#id").value;
    let data = {
      id: id,
    };
    idIsExists("http://localhost:8080/api/final-exam/groups/idIsExists", data);
  }
  if (modalTitle.innerHTML.includes("Login")) {
    let data = {};
    data.userName = modalContainer.querySelector("#username").value;
    data.password = modalContainer.querySelector("#password").value;
    let loginUrl = `http://localhost:8080/api/final-exam/users`;
    login(loginUrl, data);
  }
};
function showLoginmodal() {
  modalContainer.innerHTML = `<div class="user-name">
    <label for="name"><b>User Name</b></label>
    <input
        type="text"
        placeholder="Enter User Name "
        name="username"
        id="username"
        required
    />
    </div>
    <div class="user-password">
    <label for="password"><b>Password</b></label>
    <input
        type="password"
        name="password"
        id="password"
    />
    </div>`;

  showModal();
  modalTitle.innerHTML = "Login";
  saveBtn.innerHTML = "Login";
}

function handleDeleteGroup(id) {
  if (window.confirm(`Xác nhận xoá Group có ID = ${id}`) == false) {
    return;
  } else {
    let data = {
      id: id,
    };
    const options = {
      method: "DELETE",
      headers: new Headers({ "Content-Type": "application/json" }),
      body: JSON.stringify(data),
    };
    fetch(`http://localhost:8080/api/final-exam/groups`, options)
      .then((response) => response)
      .then(() => {
        buildListGroups();
      });
  }
}
function handleUpdateGroup(id) {
  updateGroupFunc(id);
}
function checkIdUpadate(data) {
  fetch("http://localhost:8080/api/final-exam/groups/idIsExists", {
    method: "PUT",
    headers: new Headers({ "Content-Type": "application/json" }),
    body: JSON.stringify(data),
  })
    .then((response) => response.json())
    .then((response) => {
      return response;
    })
    .then((response) => {
      if (response == true) {
        fetch("http://localhost:8080/api/final-exam/groups", {
          method: "PUT",
          headers: new Headers({ "Content-Type": "application/json" }),
          body: JSON.stringify(data),
        })
          .then((response) => response)
          .then(() => {
            buildListGroups();
          });
      } else {
        window.alert("ID nhập vào không tồn tại. Vui lòng nhập đúng ID");
      }
    });
}
