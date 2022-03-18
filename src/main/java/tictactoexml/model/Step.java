package tictactoexml.model;


import java.io.Serializable;

public class Step implements Serializable {

    private String num;

    private String playerId;

    private String step;

    public Step() {
    }

    public Step(String num, String playerId, String step) {
        this.num = num;
        this.playerId = playerId;
        this.step = step;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }
}
