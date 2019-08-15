package com.codeoftheweb.battleship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

import static java.util.stream.Collectors.toList;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Date createdDate;

    @JsonIgnore
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    List<GamePlayer> gameplayers;

    public Game() {
    }

    public Game(Date createdDate) {
        this.createdDate = createdDate;
    }

    @JsonIgnore
    public List<GamePlayer> getGameplayers() {
        return gameplayers;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return createdDate;
    }

    public void setDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void addGamePlayer(GamePlayer gameplayer) {
        gameplayer.setGame(this);
        gameplayers.add(gameplayer);
    }

}
