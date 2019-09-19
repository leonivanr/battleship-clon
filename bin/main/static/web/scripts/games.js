$(function () {
  const sample = {
    "leaderboard" : [ {
      "id" : 1,
      "email" : "j.bauer@ctu.gov",
      "score" : 1.5,
      "won" : 0.0,
      "lost" : 1.0,
      "tied" : 1.0
    }, {
      "id" : 2,
      "email" : "c.obrian@ctu.gov",
      "score" : 1.5,
      "won" : 0.0,
      "lost" : 0.0,
      "tied" : 1.0
    }, {
      "id" : 3,
      "email" : "kim_bauer@gmail.com",
      "score" : 1.0,
      "won" : 0.0,
      "lost" : 1.0,
      "tied" : 1.0
    }, {
      "id" : 4,
      "email" : "t.almeida@ctu.gov",
      "score" : 0.5,
      "won" : 0.0,
      "lost" : 1.0,
      "tied" : 0.0
    } ],
    "games" : [ {
      "id" : 1,
      "created_date" : "2019-09-10T03:47:19.981+0000",
      "game_players" : [ {
        "id" : 1,
        "player" : {
          "id" : 1,
          "email" : "j.bauer@ctu.gov"
        }
      }, {
        "id" : 2,
        "player" : {
          "id" : 2,
          "email" : "c.obrian@ctu.gov"
        }
      } ]
    }, {
      "id" : 2,
      "created_date" : "2019-09-10T04:47:19.981+0000",
      "game_players" : [ {
        "id" : 3,
        "player" : {
          "id" : 3,
          "email" : "kim_bauer@gmail.com"
        }
      }, {
        "id" : 4,
        "player" : {
          "id" : 4,
          "email" : "t.almeida@ctu.gov"
        }
      } ]
    }, {
      "id" : 3,
      "created_date" : "2019-09-10T05:47:19.981+0000",
      "game_players" : [ ]
    }, {
      "id" : 4,
      "created_date" : "2019-09-10T06:09:05.981+0000",
      "game_players" : [ ]
    } ]
  }
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