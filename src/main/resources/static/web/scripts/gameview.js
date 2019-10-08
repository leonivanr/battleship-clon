$(function () {
  //Get current gameplayer
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
        if (i === 0 && j === 0) {
          //example: id="salvog5" / id="shipc3"
          cell.classList.add('grid-empty');
        }
        if (i > 0 && j > 0) {
          //example: id="salvog5" / id="shipc3"
          cell.id = gridType + rowId + j;
        }
        if (j === 0 && i > 0) {
          // Adds header's column name. 
          cell.classList.remove('grid-cell')
          cell.classList.add('grid-header-top');
          cell.innerText = String.fromCharCode(i + 64);
        }
        if (i === 0 && j > 0) {
          // Adds header's row name. 
          cell.classList.remove('grid-cell')
          cell.classList.add('grid-header-left');
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
  const renderSalvoes = data => {
    let playerOne = data.salvoes.filter(x => (x.player === Number(gamePlayerId)));
    console.log(playerOne);
    let playerTwo = data.salvoes.filter(x => (x.player !== Number(gamePlayerId)));
    console.log(playerTwo);
    
    playerOne.forEach(turno => {
      turno.locations.forEach(hit => {
        addSalvoClass(hit, "salvo")
      })
    })

    playerTwo.forEach(turno => {
      turno.locations.forEach(hit => {
        addSalvoClass(hit, "ship")
      })
    })

  }
  const addSalvoClass = (hit, type) => {
    let hitLocation = document.getElementById(type + hit.toLowerCase());
    hitLocation.classList.add('salvo-placed');
  }

  createGrid(11, "grid-ships", "ship");
  createGrid(11, "grid-salvoes", "salvo");
  // renderShips(sample);
  // renderGamePlayers(sample);
  // renderSalvoes(sample);
  loadData();
});