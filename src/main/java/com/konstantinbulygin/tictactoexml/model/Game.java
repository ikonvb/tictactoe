package com.konstantinbulygin.tictactoexml.model;

import java.util.List;

public class Game {

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
