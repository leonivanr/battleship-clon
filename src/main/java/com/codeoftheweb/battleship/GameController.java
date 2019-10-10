package com.codeoftheweb.battleship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private GameRepo gameRepo;

    @Autowired
    private GamePlayerRepo gamePlayerRepo;

    @Autowired
    private PlayerRepo playerRepo;

    @RequestMapping(path = "/games", method = RequestMethod.POST)
    public ResponseEntity<Object> createGame (Authentication authentication) {
        if (isGuest(authentication)) {
            return new ResponseEntity<>("no esta autorizado", HttpStatus.UNAUTHORIZED);
        }
        Player player = playerRepo.findByEmail(authentication.getName());

        //if (player == null){
          //  return new ResponseEntity<>("No esta autorizado", HttpStatus.UNAUTHORIZED);
        //}

        Game game = gameRepo.save(new Game());
        GamePlayer gamePlayer = gamePlayerRepo.save(new GamePlayer(game, player));
        return new ResponseEntity<>(makeMap("gpid", gamePlayer.getId()),HttpStatus.CREATED);
    }
    private boolean isGuest(Authentication authentication) {
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

    private Map<String, Object> makeMap(String key, Object value){
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        return map;
    }

}

