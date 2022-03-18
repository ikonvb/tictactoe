package com.konstantinbulygin.tictactoexml.model;

import java.io.Serializable;
import java.util.List;

public class Gameplay implements Serializable {

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

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }
}
