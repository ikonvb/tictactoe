package com.konstantinbulygin.tictactoexml.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer playerId;

    @Column(name = "player_name")
    private String playerName;

    @Column(name = "player_symbol")
    private String symbol;

    public Player() {
    }

    public Player(Integer playerId, String playerName, String symbol) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.symbol = symbol;
    }
}
