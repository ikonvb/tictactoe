package com.konstantinbulygin.tictactoexml.model.restapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStep {

    private Integer playerId;
    private Integer gameId;
    private String stepOrder;
    private String stepCoordinate;

    public GameStep(Integer playerId, Integer gameId, String stepOrder, String stepCoordinate) {
        this.playerId = playerId;
        this.gameId = gameId;
        this.stepOrder = stepOrder;
        this.stepCoordinate = stepCoordinate;
    }

    public GameStep() {
    }
}
