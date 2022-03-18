package com.konstantinbulygin.tictactoexml.service;


import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.Step;

import java.util.List;

public interface GameDocumentWriter {
    void writeGameResult(List<Player> players, List<Step> steps, int gameTry, Player winner);
}
