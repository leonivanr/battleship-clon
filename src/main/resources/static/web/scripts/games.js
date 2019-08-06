$(function () {

    // load and display JSON sent by server for /players
  
    function loadData() {
      $.get("/api/games")
        .done(function (data) {
          updateView(data)
          console.log(data);
        })
        .fail(function (jqXHR, textStatus) {
          showOutput("Failed: " + textStatus);
        });
    }

    function updateView(data) {
        var htmlList = data.map(function (game) {
             return  '<li>' + new Date(game.created_date).toLocaleString() + ' ' + game.game_players.map(function(p) { return p.player.email}).join(',') +'</li>';
        }).join('');
      document.getElementById("game-list").innerHTML = htmlList;
    }

    loadData();
});