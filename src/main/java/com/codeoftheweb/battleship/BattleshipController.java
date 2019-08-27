package com.codeoftheweb.battleship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        //Retrive data for the given player. /game.html?gp=1
        GamePlayer gamePlayer = gamePlayerRepo.findById(gamePlayerId).orElse(null);
        return getGameViewDTO(gamePlayer.getGame(), gamePlayer);
    }

    private Map<String, Object> getGameViewDTO(Game game, GamePlayer gamePlayer) {
        //DTO = combine multiple requests in one request. (JSON).
        // 
        /* {
            "idGame": 1,
            "creationDate": "2019-08-18T04:01:39.803+0000",
            "gamePlayers": [],
            "ships": [],
            "salvoes": []
        } */ 
        Map<String, Object> dtoGame = new LinkedHashMap<>();
        dtoGame.put("idGame", game.getId());
        dtoGame.put("creationDate", game.getDate());
        dtoGame.put("gamePlayers", getGamePlayersList(game.getGameplayers()));
        dtoGame.put("ships", getShipLocation(gamePlayer.getShips()));
        dtoGame.put("salvoes", getAllSalvoes(game));
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
        //Map all gameplayers for the given game.
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
    
    private List<Map<String, Object>> getShipLocation(List<Ship> ships){
        return ships
                .stream()
                .map(ship -> shipDTO(ship))
                .collect(toList());
    }

    private Map<String, Object> shipDTO(Ship ship){
        Map<String, Object> dtoShip = new LinkedHashMap<String, Object>();
        dtoShip.put("shipType", ship.getShipType());
        dtoShip.put("locations", ship.getShipLocations());
        return dtoShip;
    }

    private List<Map<String, Object>> getAllSalvoes(Game game) {
        List<Map<String, Object>> salvoesList = new ArrayList<>();
        game.getGameplayers().forEach(gamePlayer -> salvoesList.addAll(getSalvoLocations(gamePlayer.getSalvoes())));
        return salvoesList;
    }

    private List<Map<String, Object>> getSalvoLocations(List<Salvo> salvoes){
        return salvoes
                .stream()
                .map(salvo -> salvoDTO(salvo))
                .collect(toList());
    }

    private Map<String, Object> salvoDTO(Salvo salvo){
        Map<String, Object> dtoSalvo = new LinkedHashMap<String, Object>();
        dtoSalvo.put("turn", salvo.getTurn());
        dtoSalvo.put("player", salvo.getGamePlayer().getPlayer().getId());
        dtoSalvo.put("locations", salvo.getSalvoLocations());
        return dtoSalvo;
    }

}
