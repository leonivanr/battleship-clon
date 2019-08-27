$(function () {
  //Get current gameplayer
  var urlParams = new URLSearchParams(window.location.search);
  var gamePlayerId = urlParams.get('gp');

  //#region sample data  
  var turn = 1;
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
    ],
    "salvoes": [{
        "turn": "1",
        "player": "1",
        "locations": ["H2", "A2"]
      },
      {
        "turn": "1",
        "player": "2",
        "locations": ["C5", "E6"]
      },
      {
        "turn": "2",
        "player": "1",
        "locations": ["B4", "D8"]
      },
      {
        "turn": "2",
        "player": "2",
        "locations": ["A7", "F1"]
      }
    ]
  }
  //#endregion sample data

  const loadData = () => {
    $.get("/api/game_view/" + gamePlayerId)
      .done(function (data) {
        console.log(data);
        renderGamePlayers(data);
        renderShips(data);
        renderSalvoes(data);
      })
      .fail(function (jqXHR, textStatus) {
        console.log("Failed: " + textStatus);
      });
  }
  // Grid type = salvo / ship
  const createGrid = (size, gridId, gridType) => {

    let gridContainer = document.querySelector('.' + gridId);

    for (let i = 0; i < size; i++) {
      let row = document.createElement('div');

      let rowId = String.fromCharCode(i + 64).toLowerCase();

      gridContainer.appendChild(row);

      for (let j = 0; j < size; j++) {
        // Creates a div (cell) for each row.         
        let cell = document.createElement('div');
        cell.classList.add('grid-cell');
        if (i > 0 && j > 0) {
          //example: id="salvog5" / id="shipc3"
          cell.id = gridType + rowId + j;
        }
        if (j === 0 && i > 0) {
          // Adds header's column name. 
          cell.classList.add('grid-header');
          cell.innerText = String.fromCharCode(i + 64);
        }
        if (i === 0 && j > 0) {
          // Adds header's row name. 
          cell.classList.add('grid-header');
          cell.innerText = j;
        }
        row.appendChild(cell)
      }
    }
  }
  // Display players email's.
  const renderGamePlayers = data => {

    var playerOne = document.getElementById('playerOne');
    var playerTwo = document.getElementById('playerTwo');

    if (data.gamePlayers[0].id == gamePlayerId) {
      playerOne.innerText = data.gamePlayers[0].player.email;
      playerTwo.innerText = data.gamePlayers[1].player.email;
    } else {
      playerOne.innerText = data.gamePlayers[1].player.email;
      playerTwo.innerText = data.gamePlayers[0].player.email;
    }


  }
  // Display ships for the gameplayer viewing the game.
  const renderShips = data => {
    data.ships.forEach(shipData => {
      shipData.locations.forEach(location => {
        addShipClass(location);
      })
    });
  }
  const addShipClass = cellId => {
    let shipPart = document.getElementById("ship" + cellId.toLowerCase());

    shipPart.classList.add('ship-placed');
  }
  // Display salvoes for the player and opponent.
  const renderSalvoes = data => {
    //REVIEW:
    var salvos = data.salvoes.filter(salvo =>
      salvo.turn === turn
    )

    if (gamePlayerId === salvos[0].player) {
      addSalvoClass(salvos[0].locations, "salvo");
      addSalvoClass(salvos[1].locations, "ship");
    } else {
      addSalvoClass(salvos[1].locations, "salvo");
      addSalvoClass(salvos[0].locations, "ship");
    }

  }
  const addSalvoClass = (locations, type) => {
    locations.forEach(location => {
      let locationElem = document.getElementById(type + location.toLowerCase());
      locationElem.classList.add('salvo-placed');
    })
  }

  createGrid(11, "grid-ships", "ship");
  createGrid(11, "grid-salvoes", "salvo");
  // renderShips(sample);
  // renderGamePlayers(sample);
  // renderSalvoes(sample);
  loadData();
});