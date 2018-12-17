
function loadTasks() {
    fetch("/tasks", {
        method: "get",
        headers: {
            'Authorization': 'Basic ' + btoa(localStorage.getItem("currentUsername") + ":" + localStorage.getItem("currentPassword"))
            //login_user:VWRYDQE2
        }
    }).then(
        resp => resp.json()
    ).then(tasks => {
        for (const task of tasks) {
            addTask(task);
        }
    });
}

function addTask(task) {
    // if (task.assignedUserId != null) {
    //         fetch("/tasks/getuser/" + task.id, {
    //             method: "get"
    //         })
    //             .then(resp => resp.json())
    //             .then(user => {
    //                 console.log("Inside fetch. User name: " + user.name);
    //                 //fetchedUser = user;
    //                 console.log("Inside fetch. User: " /*+ fetchedUser*/);
    //             });
    //     }

    const tr = document.createElement("tr");
    tr.innerHTML = `
        <td>${task.id}</td>
        <td>${task.title}</td>
        <td>${task.description}</td>
        <td>${task.taskStatus}</td>
        <td>${task.user ? task.user.name + " " + task.user.lastName : ""}</td>
        <td>
            <button onclick="deleteTask(${task.id})">Delete</button>|
            <a href="/fetasks/editTask.html?taskId=${task.id}">Edit</a>|
            <a href="/fetasks/assignUser.html?taskId=${task.id}">Assign user</a>
        </td>
    `;
    document.getElementById("table-body").appendChild(tr);
}

function createTask() {
    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;

    fetch("/tasks/add", {method: "post",
        body: JSON.stringify({
            title: title,
            description: description
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Authorization': 'Basic ' + btoa(localStorage.getItem("currentUsername") + ":" + localStorage.getItem("currentPassword"))
        }
    }).then(() => {
        window.location.href = "/fetasks/tasks.html";
    });
}

function deleteTask(id) {
    fetch("/tasks/delete/" + id, {
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

function loadTaskForEdit() {
    const taskId = new URL(window.location.href).searchParams.get("taskId");

    fetch("/tasks/" + taskId)
        .then(resp => resp.json())
        .then(taskFromServer => {
            document.getElementById("title").value = taskFromServer.title;
            document.getElementById("description").value = taskFromServer.description;
        });

}

function updateTask() {
    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;
    const taskId = new URL(window.location.href).searchParams.get("taskId");

    fetch("/tasks/update/" + taskId, {
        method: "put",
        body: JSON.stringify({
            title: title,
            description: description
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Authorization': 'Basic ' + btoa(localStorage.getItem("currentUsername") + ":" + localStorage.getItem("currentPassword"))
        }
    }).then(resp => resp.json())
        .then(successful => {
            if (successful) {
                window.location.href = "/fetasks/tasks.html";
            } else {
                alert("Failed to update");
            }
        });

}

function loadUsers() {
    fetch("/users", {
        method: "get"})
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
        <td>${user.name}</td>
        <td>${user.lastName}</td>
        <td>
            <button onclick="assignUser(${user.id})">Assign</button>
        </td>
    `;
    document.getElementById("table-body").appendChild(tr);
}

function assignUser(userId) {
    console.log("User ID: " + userId);
    const taskId = new URL(window.location.href).searchParams.get("taskId");
    console.log("Task ID: " + taskId);

    let url = "/tasks/assign/" + taskId + "?" + "userId=" + userId;
    fetch(url, {
        method: "put",
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Authorization': 'Basic ' + btoa(localStorage.getItem("currentUsername") + ":" + localStorage.getItem("currentPassword"))
        }
    })
        .then(resp => resp.json())
        .then(successful => {
            if (successful) {
                window.location.href = "/fetasks/tasks.html";
            } else {
                alert("Failed to assign user");
            }
        });

}