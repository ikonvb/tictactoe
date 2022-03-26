package com.konstantinbulygin.tictactoexml.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gameplay {
    private List<Player> players;
    private Game game;
    private GameResult gameResult;
}
