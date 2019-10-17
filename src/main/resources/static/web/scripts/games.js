$('#gameCreatedSuccess, #gameJoined, #gameReEnter').hide();
const newgameBtn = document.querySelector('#createGame');
const dateOptions = {
  year: '2-digit',
  month: '2-digit',
  day: 'numeric'
}
let games;
let playerId;

const updateUserName = data => {
  let userData = document.querySelector('#user-name');
  userData.innerText = `Hi ${data.player.email}!`;
}

const updateGamesTable = data => {
  var htmlList = data.games.map(game => {
    let gamePlayersHtml;
    let stateBtn = "";
    if (game.game_players.length == 1) {
      if (game.game_players[0].player.id == data.player.id) {
        stateBtn = `<td><form class="text-center"><input class="pixel-sdw return-game" type="submit" id="gameId${game.id}" value="RETURN" /></form></td>`
      } else {
        stateBtn = `<td><form class="text-center"><input class="pixel-sdw join-game" type="submit" id="gameId${game.id}" value="JOIN" /></form></td>`;
      }
      gamePlayersHtml = `<td>${game.game_players[0].player.email}</td><td>awaiting for player...</td>`;
    } else {
      if (game.game_players[0].player.id == data.player.id || game.game_players[1].player.id == data.player.id) {
        stateBtn = `<td><form class="text-center"><input class="pixel-sdw return-game" type="submit" id="gameId${game.id}" value="RETURN" /></form></td>`
      }
      gamePlayersHtml = game.game_players.map(p => {
        return `<td>${p.player.email}</td>`
      }).join('');
    }

    return '<tr class="mb-3"><td>' + new Date(game.created_date).toLocaleString('es-AR', dateOptions) + '</td>' + gamePlayersHtml + stateBtn + '</tr>';
  }).join('');
  document.getElementById("game-list-table").innerHTML = htmlList;
}

// load and display JSON sent by server for /players
const loadData = () => {
  $.get("/api/games")
    .done(data => {
      console.log('First load data:', data)
      updateUserName(data);
      updateGamesTable(data);
      games = data.games;
      playerId = data.player.id;
    })
    .fail(error => {
      console.log(error);
    });
}

loadData();

// Listeners
newgameBtn.addEventListener('click', (e) => {
  e.preventDefault();
  $.post("/api/games")
    .done(data => {
      console.log("Miren mi juego ", data);
      console.log("game created");
      gameViewUrl = "/web/game.html?gp=" + data.gpid;
      $('#createGameForm').hide();
      $('#gameCreatedSuccess').show("slow");
      setTimeout(
        function () {
          location.href = gameViewUrl;
        }, 3000);
    })
    .fail(error => {
      console.log(error);
    })
})

$('#game-list-table').on('click', '.join-game', (e) => {
  e.preventDefault();
  // Get gameid from button. 
  let gameId = e.target.id.replace(/\D+/g, '');

  $.post("/api/games/" + gameId + "/players/")
    .done(data => {
      console.log(data);
      $('#createGameForm').hide();
      $('#gameJoined').show("slow");
      gameViewUrl = "/web/game.html?gp=" + data.gpid;
      setTimeout(
        function () {
          location.href = gameViewUrl;
        }, 3000);
    })
    .fail(error => {
      console.log(error);
    })
})

$('#game-list-table').on('click', '.return-game', (e) => {
  e.preventDefault();

  // Get gameid from button. 
  let gameId = e.target.id.replace(/\D+/g, '');
  //Get gameplayer ID matching player's id. 
  let gameviewGP = games.filter(game => (game.id == gameId))[0].game_players.filter(gpl => (gpl.player.id == playerId))[0].gpid;

  $.get("/api/game_view/" + gameviewGP)
    .done(data => {
      console.log(data);
      gameViewUrl = "/web/game.html?gp=" + gameviewGP;
      $('#createGameForm').hide();
      $('#gameReEnter').show("slow").delay(2000).hide("slow");
      setTimeout(
        function () {
          location.href = gameViewUrl;
        }, 3000);
    })
    .fail(error => {
      console.log(error);
    })
})