const newgameBtn = document.querySelector('#createGame');

const updateGamesTable = data => {
  var htmlList = data.games.map(function (game) {
    return '<tr><td>' + new Date(game.created_date).toLocaleString() + '</td>' + game.game_players.map(function (p) {
      return '<td>' + p.player.email + '</td>'
    }).join('') + '</tr>';
  }).join('');
  document.getElementById("game-list-table").innerHTML = htmlList;
}
// load and display JSON sent by server for /players
const loadData = () => {
  $.get("/api/games")
    .done(function (data) {
      console.log(data)
      updateGamesTable(data)

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