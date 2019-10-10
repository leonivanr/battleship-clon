package com.codeoftheweb.battleship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;


@Entity
public class Game {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private Date createdDate;

    @JsonIgnore
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    List<GamePlayer> gameplayers;

    @JsonIgnore
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    List<Score> scores = new ArrayList<>();

    // Constructors.
    public Game() {
        this.createdDate = new Date();
    }

    // G&S.
    @JsonIgnore
    public List<GamePlayer> getGameplayers() {
        return gameplayers;
    }

    @JsonIgnore
    public List<Score> getScores() { return scores; }

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
        this.gameplayers.add(gameplayer);
    }

}
