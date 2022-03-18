package tictactoexml.repository;

import tictactoexml.model.Player;
import tictactoexml.model.Step;

import java.util.List;

public interface GameDocumentWriter {
    void writeGameResult(List<Player> players, List<Step> steps, int gameTry, Player winner);
}
