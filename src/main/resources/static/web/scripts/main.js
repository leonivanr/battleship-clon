$(function () {
  //TODO: Add start button;
  let leaderboardList;
  var isLogged = false;
  const logoutBtn = document.querySelector('#menu-logout-btn')
  // Get all games.  
  const verifyLogin = () => {
    if (isLogged) {
      const loginMenuBtn = document.querySelector("#menu-login-btn");
      const logoutMenuBtn = document.querySelector("#menu-logout-btn");
      const registerMenuBtn = document.querySelector("#menu-register-btn");
      const startMenuBtn = document.querySelector("#menu-start-btn");
      loginMenuBtn.classList.add("hide");
      registerMenuBtn.classList.add("hide");
      startMenuBtn.classList.remove("hide");
      logoutMenuBtn.classList.remove("hide");
    }
  }
  const getGames = () => {
    $.get("/api/games").done((data) => {
        console.log(data.player)
        if (data.player.email) {
          isLogged = true;
          verifyLogin();
          console.log(data);
        }
      })
      .fail(error => console.log(error));

  }

  const getLeaders = () => {
    // Get leaderboard list.
    $.get("/api/leaderboard")
      .done((data) => {
        console.log(data);
        updateView(data);
      })
      .fail(error => console.log(error));

  }



  getGames();
  getLeaders();



  const logout = () => {
    console.log("entro")
    $.post("/api/logout")
      .done(() => {
        console.log("logged out!");
      })
      .fail(error => {
        return console.log("Status: " + error.responseJSON.status + " Error: " + error.responseJSON.error)
      });
  }
  // Update leaderboard table.
  const updateView = data => {
    var htmlList = data.leaderboard.map(function (game) {
      return '<tr><td>' + game.email + '</td><td>' + game.score + '</td><td>' + game.won + '</td><td>' + game
        .lost + '</td><td>' + game.tied + '</td></tr>';
    }).join('');
    document.getElementById("leader-list").innerHTML = htmlList;
  }

  //Listenners
  logoutBtn.addEventListener("click", (e) => {
    e.preventDefault();
    logout();
    setTimeout(() => {
      window.location.replace("main.html");
    }, 100)
  })



});