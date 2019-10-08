$(function () {
  //j.bauer@ctu.gov
  const loginBtn = document.querySelector("#login-btn");
  
  const login = () => {
    console.log("entro")
    $.post("/api/login", {
        email: $("#email-input").val(),
        password: $("#password-input").val()
      }).done((response) => {
        console.log("logged in!");
        setTimeout(() => {
          window.location.replace("main.html");
        }, 300)
      })
      .fail(error => {
        return console.log("Status: " + error.responseJSON.status + " Error: " + error.responseJSON.error)
      });
  }

  loginBtn.addEventListener('click', (e) => {
    e.preventDefault();
    login();
  })


});