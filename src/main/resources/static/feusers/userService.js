
function loadUsers() {
    fetch("/users", {
        method: "get",
        headers: {
            'Authorization': 'Basic ' + btoa(localStorage.getItem("currentUsername") + ":" + localStorage.getItem("currentPassword"))
        }
    })
        .then(resp => resp.json())
        .then(users => {
            for (const user of users) {
                addUser(user);
            }
        });
}

function addUser(user) {
    const tr = document.createElement("tr");
    tr.innerHTML = `
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.lastName}</td>
        <td>${user.age}</td>
        <td>
            <button onclick="deleteUser(${user.id})">Delete</button>
            <a href="/feusers/editUser.html?userId=${user.id}">Edit</a>
        </td>
    `;
    document.getElementById("table-body").appendChild(tr);
}

function createUser() {
    const name = document.getElementById("name").value;
    const lastName = document.getElementById("lastName").value;
    const age = document.getElementById("age").value;
    const username = document.getElementById("username").value;

    fetch("/users/add", {
        method: "post",
        body: JSON.stringify({
            name: name,
            lastName: lastName,
            age: age,
            username: username
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Authorization': 'Basic ' + btoa(/*"login_user:VWRYDQE2"*/localStorage.getItem("currentUsername") + ":" + localStorage.getItem("currentPassword"))
        }
    }).then(() => {
        window.location.href = "/feusers/users.html";
    });
}

function deleteUser(id) {
    fetch("/users/delete/" + id, {
        method: "delete",
        headers: {
            'Authorization': 'Basic ' + btoa(localStorage.getItem("currentUsername") + ":" + localStorage.getItem("currentPassword"))
        }
    })
        .then((resp) => resp.json())
        .then(successful => {
            if (successful === true) {
                window.location.reload();
            } else {
                alert("Delete failed");
            }
        });
}

function loadUserForEdit() {
    const userId = new URL(window.location.href).searchParams.get("userId");

    fetch("/users/" + userId, {
        method: "get",
        headers: {
            'Authorization': 'Basic ' + btoa(localStorage.getItem("currentUsername") + ":" + localStorage.getItem("currentPassword"))
        }
    })
        .then(resp => resp.json())
        .then(userFromServer => {
            document.getElementById("name").value = userFromServer.name;
            document.getElementById("lastName").value = userFromServer.lastName;
            document.getElementById("age").value = userFromServer.age;
        });

}

function updateUser() {
    const name = document.getElementById("name").value;
    const lastName = document.getElementById("lastName").value;
    const age = document.getElementById("age").value;
    const userId = new URL(window.location.href).searchParams.get("userId");

    fetch("/users/update/" + userId, {
        method: "put",
        body: JSON.stringify({
            name: name,
            lastName: lastName,
            age: age
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Authorization': 'Basic ' + btoa(localStorage.getItem("currentUsername") + ":" + localStorage.getItem("currentPassword"))
        }
    }).then(resp => resp.json())
        .then(successful => {
            if (successful) {
                window.location.href = "/feusers/users.html";
            } else {
                alert("Failed to update");
            }
        });

}