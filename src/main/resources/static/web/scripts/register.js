const registerBtn = document.querySelector("#register-btn");

const register = () => {

  $.post("/api/players", {
      email: $("#email-input-reg").val(),
      password: $("#password-input-reg").val()
    }).done(() => {
      console.log("registered!");
      setTimeout(() => {
        window.location.replace("login.html");
      }, 400)
    })
    .fail(error => {
      return console.log("Status: " + error.responseJSON.status + " Error: " + error.responseJSON.error)
    });
}

registerBtn.addEventListener('click', (e) => {
  e.preventDefault();
  register();
})