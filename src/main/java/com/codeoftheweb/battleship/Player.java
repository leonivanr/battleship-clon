package com.codeoftheweb.battleship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Player {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    List<GamePlayer> gameplayers;

    @JsonIgnore
    @OneToMany(mappedBy = "player", fetch = FetchType.EAGER)
    List<Score> scores;

    private String password;

    //Constructors
    public Player() {
    }

    public Player(String username, String password) {
        this.email = username;
        this.password = password;
    }

    //G&S

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Score> getScores() { return scores; }

    public void addGamePlayer(GamePlayer gameplayer) {
        gameplayer.setPlayer(this);
        this.gameplayers.add(gameplayer);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
}
}