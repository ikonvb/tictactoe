package com.konstantinbulygin.tictactoexml.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer gameId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    private List<Step> steps;

    public Game(List<Step> steps) {
        this.steps = steps;
    }

    public Game() {
    }
}
