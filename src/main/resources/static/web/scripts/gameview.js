$(function () {
  
  var urlParams = new URLSearchParams(window.location.search);
  var gamePlayerId = urlParams.get('gp');

  const sample = {
    "idGame": 1,
    "creationDate": "2019-08-18T04:01:39.803+0000",
    "gamePlayers": [{
        "id": 1,
        "player": {
          "id": 1,
          "email": "j.bauer@ctu.gov"
        }
      },
      {
        "id": 2,
        "player": {
          "id": 2,
          "email": "c.obrian@ctu.gov"
        }
      }
    ],
    "ships": [{
        "shipType": "Patrol Boat",
        "locations": [
          "E1",
          "E2",
          "E3",
          "E4"
        ]
      },
      {
        "shipType": "Destroyer",
        "locations": [
          "H2",
          "H3",
          "H4"
        ]
      }
    ]
  }

  const loadData = () => {
    $.get("/api/game_view/" + gamePlayerId)
      .done(function (data) {
        renderGamePlayers(data);
        renderShips(data);
      })
      .fail(function (jqXHR, textStatus) {
        console.log("Failed: " + textStatus);
      });
  }

  const createGrid = size => {

    let gridContainer = document.querySelector('.grid-container');

    for (let i = 0; i < size; i++) {
      let row = document.createElement('div');

      let rowId = String.fromCharCode(i + 64).toLowerCase();

      gridContainer.appendChild(row);

      for (let j = 0; j < size; j++) {
        let cell = document.createElement('div');
        cell.classList.add('grid-cell');
        if (i > 0 && j > 0) {
          cell.id = rowId + j;
        }
        if (j === 0 && i > 0) {
          cell.classList.add('grid-header');
          cell.innerText = String.fromCharCode(i + 64);
        }
        if (i === 0 && j > 0) {
          cell.classList.add('grid-header');
          cell.innerText = j;
        }
        row.appendChild(cell)
      }
    }
  }

  const addShip = cellId => {
    let shipPart = document.getElementById(cellId.toLowerCase());

    shipPart.classList.add('ship-placed');
  }

  const renderShips = data => {
    data.ships.forEach( element => {
      element.locations.forEach( location => {
        addShip(location);
      })
    });
  }

  const renderGamePlayers = data => {

    var playerOne = document.getElementById('playerOne');
    var playerTwo = document.getElementById('playerTwo');

    if (data.gamePlayers[0].id == gamePlayerId ) {
      playerOne.innerText = data.gamePlayers[0].player.email;
      playerTwo.innerText = data.gamePlayers[1].player.email;
    }
    else {
      playerOne.innerText = data.gamePlayers[1].player.email;
      playerTwo.innerText = data.gamePlayers[0].player.email;
    }

  }

  createGrid(11);
  loadData();
});