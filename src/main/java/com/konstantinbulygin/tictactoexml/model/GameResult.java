package com.konstantinbulygin.tictactoexml.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "game_result")
public class GameResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_result_id")
    private Integer gameResultId;

    @OneToOne
    private Game game;

    @OneToOne
    private Player player;

    public GameResult(Player player) {
        this.player = player;
    }

    public GameResult() {
    }
}
