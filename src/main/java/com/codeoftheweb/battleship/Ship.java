package com.codeoftheweb.battleship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ship {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator ="native" )
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String shipType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "gamePlayer_id")
    private GamePlayer gamePlayer;

    @ElementCollection
    private List<String> shipLocations;

    //Constructors
    public Ship() {
    }

    public Ship(List<String> shipLocations, String shipType) {
        this.shipType = shipType;
        this.shipLocations = shipLocations;
    }
    //G&S
    public long getId() {
        return id;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public List<String> getShipLocations() {
        return shipLocations;
    }

    public void setShipLocations(List<String> shipLocations) {
        this.shipLocations = shipLocations;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

}
