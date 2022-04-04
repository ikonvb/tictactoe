package com.konstantinbulygin.tictactoexml.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Setter
@Getter
public class Gameplay {

    private List<Player> players;
    private Game game;
    private GameResult gameResult;

    public Gameplay() {
    }

    public Gameplay(List<Player> players, Game game, GameResult gameResult) {
        this.players = players;
        this.game = game;
        this.gameResult = gameResult;
    }
}
