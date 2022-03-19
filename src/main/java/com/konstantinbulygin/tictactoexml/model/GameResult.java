package com.konstantinbulygin.tictactoexml.model;

public class GameResult {

    private Player player;

    public GameResult() {
    }

    public GameResult(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
