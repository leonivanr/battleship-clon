const registerBtn = document.querySelector("#register-btn");

const register = () => {
    $.post("/api/players", {
      email: $("#email-input-reg").val(),
      password: $("#password-input-reg").val()
    }).done(() => {
      console.log("registered!");
      $.post("/api/login", {
        email: $("#email-input-reg").val(),
        password: $("#password-input-reg").val()
      }).done(() => {
        console.log("logged in!");
        setTimeout(() => {
          window.location.replace("games.html");
        }, 300)
      })
      .fail(error => {
        return console.log("Status: " + error.responseJSON.status + " Error: " + error.responseJSON.error)
      });
    })
    .fail(error => {
        console.log(error)
    });
}

registerBtn.addEventListener('click', (e) => {
  e.preventDefault();
  register();
})