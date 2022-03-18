package tictactoexml.parser;

import com.google.gson.Gson;
import tictactoexml.model.*;
import tictactoexml.repository.GameDocumentReader;

import java.util.ArrayList;
import java.util.List;

public class JsonParser implements GameDocumentReader {

    private final String[][] ticTacToe = initGame();
    private final List<Player> players = new ArrayList<>();
    private final Player player1 = new Player();
    private final Player player2 = new Player();
    private final GameResult gameResult = new GameResult();
    private final List<Step> steps = new ArrayList<>();

    @Override
    public void readGameFile(String fileName) {
        GameInfo gameInfo = new Gson().fromJson(fileName, GameInfo.class);
        System.out.println(player1);
    }

    private String[][] initGame() {

        return new String[][]{
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };
    }
}
