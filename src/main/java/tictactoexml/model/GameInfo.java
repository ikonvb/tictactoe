package tictactoexml.model;

public class GameInfo {
    private Gameplay gameplay;

    public GameInfo() {
    }

    public GameInfo(Gameplay gameplay) {
        this.gameplay = gameplay;
    }

    public Gameplay getGameplay() {
        return gameplay;
    }

    public void setGameplay(Gameplay gameplay) {
        this.gameplay = gameplay;
    }
}
