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
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.lastName}</td>
    `;
    document.getElementById("table-body").appendChild(tr);
}

function createUser() {
    const name = document.getElementById("name").value;
    const lastName = document.getElementById("lastName").value;

    fetch("/users/add", {
        method: "post",
        body: JSON.stringify({
            name: name,
            lastName: lastName
        }),
        headers: {
            "Content-Type": "application/json;charset=UTF-8"
        }
    }).then(() => {
        window.location.href = "/users.html";
    });
}