const newgameBtn = document.querySelector('#createGame');

const updateUserName = data => {
  let userData = document.querySelector('#user-name');
  userData.innerText = 'Welcome ' + data.player.email + '!';
}
const updateGamesTable = data => {
  var htmlList = data.games.map(function (game) {
    let gamePlayersHtml;
    let joinBtn = "";
    console.log(game.id)
    if (game.game_players.length == 1) {
      joinBtn = '<td><form class="text-center" id="createGameForm"><input class="pixel-sdw return-game" type="submit" id="gameId' +
        game.id + '" value="JOIN" /></form></td>';
      gamePlayersHtml = '<td>' + game.game_players[0].player.email + '</td>' +
        '<td>awaiting for player...</td>';
    } else {
      if (game.game_players[0].player.id == data.player.id || game.game_players[1].player.id == data.player.id) {
        joinBtn = '<td><form class="text-center" id="createGameForm"><input class="pixel-sdw join-game" type="submit" id="gameId' +
          game.id + '" value="JOIN" /></form></td>'
      }
      gamePlayersHtml = game.game_players.map(function (p) {
        return '<td>' + p.player.email + '</td>'
      }).join('');
    }



    return '<tr class="mb-3"><td>' + new Date(game.created_date).toLocaleString('es-AR', {
      year: '2-digit',
      month: '2-digit',
      day: 'numeric'
    }) + '</td>' + gamePlayersHtml + joinBtn + '</tr>';
  }).join('');
  document.getElementById("game-list-table").innerHTML = htmlList;
}
// load and display JSON sent by server for /players
const loadData = () => {
  $.get("/api/games")
    .done(function (data) {
      console.log(data)
      updateUserName(data);
      updateGamesTable(data);

    })
    .fail(() => {
      console.log("not ok")
    });
}

loadData();
// Listeners
newgameBtn.addEventListener('click', (e) => {
  e.preventDefault();
  $.post("/api/games")
    .done((data) => {
      console.log("Miren mi juego ", data);
      console.log("game created");
      gameViewUrl = "/web/game.html?gp=" + data.gpid;
      // $('#gameCreatedSuccess').show("slow").delay(2000).hide("slow");
      setTimeout(
        function () {
          location.href = gameViewUrl;
        }, 3000);
    })
    .fail((error) => {
      console.log('not ok');
    })
})

$('#game-list-table').on('click', '.join-game', function (e) {
  e.preventDefault();
  console.log(e.target.id);
  let gameId = e.target.id.replace(/\D+/g, '');
  console.log(gameId)
  $.post("/api/games/" + gameId + "/players/")
    .done((data) => {
      console.log(data);
      // gameViewUrl = "/web/game.html?gp=" + data.gpid;
      // $('#gameCreatedSuccess').show("slow").delay(2000).hide("slow");
      setTimeout(
        function () {
          location.href = gameViewUrl;
        }, 3000);
    })
    .fail((error) => {
      console.log('not ok');
    })
})

$('#game-list-table').on('click', '.return-game', function (e) {
  e.preventDefault();
  console.log(e.target.id);
  let gameId = e.target.id.replace(/\D+/g, '');
  console.log(gameId)
  $.get("/api/games/" + gameId + "/players/")
    .done((data) => {
      console.log(data);
      // gameViewUrl = "/web/game.html?gp=" + data.gpid;
      // $('#gameCreatedSuccess').show("slow").delay(2000).hide("slow");
      setTimeout(
        function () {
          location.href = gameViewUrl;
        }, 3000);
    })
    .fail((error) => {
      console.log('not ok');
    })
})