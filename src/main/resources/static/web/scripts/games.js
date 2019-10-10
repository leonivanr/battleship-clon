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
    .fail(function (jqXHR, textStatus) {
      showOutput("Failed: " + textStatus);
    });
}

loadData();