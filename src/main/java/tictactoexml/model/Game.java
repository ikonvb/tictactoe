package tictactoexml.model;

import java.io.Serializable;
import java.util.List;

public class Game implements Serializable {

    private List<Step> steps;

    public Game() {
    }

    public Game(List<Step> steps) {
        this.steps = steps;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
