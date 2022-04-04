package com.konstantinbulygin.tictactoexml.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "steps")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_id")
    private Integer stepId;

    @Column(name = "player_id")
    private Integer playerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game")
    private Game game;

    @Column(name = "step_ordinal")
    private String stepOrder;

    @Column(name = "step_coordinate")
    private String stepCoordinate;

    public Step(Integer playerId, String stepOrder, String stepCoordinate) {
        this.playerId = playerId;
        this.stepOrder = stepOrder;
        this.stepCoordinate = stepCoordinate;
    }

    public Step() {
    }
}
