package com.konstantinbulygin.tictactoexml.repository;


import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.Step;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameDocumentWriter {
    void writeGameResult(List<Player> players, List<Step> steps, int gameTry, Player winner);
}
