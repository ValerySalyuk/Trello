// Tasks

function loadTasks() {
    fetch("/tasks", {
        method: "get"})
        .then(resp => resp.json())
        .then(tasks => {
            for (const task of tasks) {
                addTask(task);
            }
        });
}

function addTask(task) {
    const tr = document.createElement("tr");
    tr.innerHTML = `
        <td>${task.id}</td>
        <td>${task.title}</td>
        <td>${task.description}</td>
        <td>${task.taskStatus}</td>
        <td>${task.assignedUserId}</td>
        <td>
            <button onclick="deleteTask(${task.id})">Delete</button>
            <a href="/fetasks/editTask.html?taskId=${task.id}">Edit</a>
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
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(() => {
        window.location.href = "/index.html";
});
}

function deleteTask(id) {
    fetch("/tasks/delete/" + id, {method: "delete"})
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
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(resp => resp.json())
        .then(successful => {
            if (successful) {
                console.log("HERE");
                window.location.href = "/index.html";
            } else {
                alert("Failed to update");
            }
        });

}