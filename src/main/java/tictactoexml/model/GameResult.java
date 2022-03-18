package tictactoexml.model;

import java.io.Serializable;

public class GameResult implements Serializable {

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
