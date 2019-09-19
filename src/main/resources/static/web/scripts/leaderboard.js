$(function () {

    // load and display JSON sent by server for /players

    function loadData() {
      $.get("/api/leaderboard")
        .done(function (data) {
            console.log(data)
          updateView(data)
        })
        .fail(function (jqXHR, textStatus) {
          showOutput("Failed: " + textStatus);
        });
    }

    function updateView(data) {
        var htmlList = data.leaderboard.map(function (game) {
             return  '<tr><td>' + game.email + '</td><td>' + game.score + '</td><td>' + game.won + '</td><td>' + game.lost + '</td><td>' + game.tied +'</td></tr>';
        }).join('');
      document.getElementById("leader-list").innerHTML = htmlList;
    }
    loadData();
});