package com.codeoftheweb.battleship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping("/api")
public class BattleshipController {
    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private GamePlayerRepo gamePlayerRepo;

    @RequestMapping("/games")
    public List<Object> getAll() {
        return gameRepo.findAll().stream().map(this::gameToDto).collect(toList());
    }

    @RequestMapping("/game_view/{gamePlayerId}")
    public Map<String, Object> getGameView(@PathVariable Long gamePlayerId) {
        GamePlayer gamePlayer = gamePlayerRepo.findById(gamePlayerId).orElse(null);
        return getGameViewDTO(gamePlayer.getGame(), gamePlayer);
    }

    private Map<String, Object> getGameViewDTO(Game game, GamePlayer gamePlayer) {
        Map<String, Object> dtoGame = new LinkedHashMap<>();
        dtoGame.put("idGame", game.getId());
        dtoGame.put("creationDate", game.getDate());
        dtoGame.put("gamePlayers", getGamePlayersList(game.getGameplayers()));
        dtoGame.put("ships", getShipLocation(gamePlayer.getShips()));
        return dtoGame;
    }

    private Map<String, Object> gameToDto(Game game) {
        Map <String, Object> gameData = new LinkedHashMap<String, Object>();
        gameData.put("id", game.getId());
        gameData.put("created_date", game.getDate());
        gameData.put("game_players", getGamePlayersList(game.getGameplayers()));
        return gameData;
    }

    private List<Object> getGamePlayersList(List<GamePlayer> gamePlayers) {
        return gamePlayers.stream()
                .map(this::gamePlayerToDto)
                .collect(toList());
    }

    private Map<String, Object> gamePlayerToDto(GamePlayer gamePlayer) {
        Map <String, Object> gameData = new LinkedHashMap<String, Object>();
        gameData.put("id", gamePlayer.getId());
        gameData.put("player", playerToDto(gamePlayer.getPlayer()));
        return gameData;
    }

    private Map<String, Object> playerToDto(Player player) {
        Map <String, Object> gameData = new LinkedHashMap<String, Object>();
        gameData.put("id", player.getId());
        gameData.put("email", player.getEmail());
        return gameData;
    }

    // Se genera una lista mapeando Ship
    private List<Map<String, Object>> getShipLocation(List<Ship> ships){
        return ships
                .stream()
                .map(ship -> shipDTO(ship))
                .collect(toList());
    }

    // Se mapea Ship para mostrar su type y locaciones
    private Map<String, Object> shipDTO(Ship ship){
        Map<String, Object> dtoShip = new LinkedHashMap<String, Object>();
        dtoShip.put("shipType", ship.getShipType());
        dtoShip.put("locations", ship.getShipLocations());
        return dtoShip;
    }


}
