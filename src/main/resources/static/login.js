function saveCredentials() {

    localStorage.setItem("currentUsername", document.getElementById("username").value);
    localStorage.setItem("currentPassword", document.getElementById("password").value);

    window.location.href = "/fetasks/tasks.html";

}