package com.konstantinbulygin.tictactoexml.util;

import com.konstantinbulygin.tictactoexml.exceptions.WrongCoordinateException;
import com.konstantinbulygin.tictactoexml.model.Gameplay;
import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.Step;
import com.konstantinbulygin.tictactoexml.service.TicTacToeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Objects;

import static com.konstantinbulygin.tictactoexml.util.GameUtil.STRING_DRAW;
import static com.konstantinbulygin.tictactoexml.util.GameUtil.STRING_WINNER;

public class GameParser {

    @Autowired
    TicTacToeService ticTacToeService;

    public final String[][] ticTacToeField = initGame();

    public void showGameResult(Gameplay gameplay) {
        if (gameplay.getGameResult().getPlayer() != null) {
            System.out.println(
                    STRING_WINNER + gameplay.getGameResult().getPlayer().getPlayerName() + " as " +
                            gameplay.getGameResult().getPlayer().getSymbol());
        } else {
            System.out.println(STRING_DRAW);
        }
    }

    public void showGameFirstState(Gameplay gameplay) {
        for (Player pl : gameplay.getPlayers()) {
            System.out.println("Player name: " + pl.getPlayerName() + " symbol is " + pl.getSymbol());
        }
        System.out.println();
        showGameBoard(ticTacToeField);
    }

    public void showGameSteps(Gameplay gameplay) {

        for (Step step : gameplay.getGame().getSteps()) {
            gameplay.getPlayers().stream()
                    .filter(player -> Objects.equals(player.getPlayerId(), step.getPlayerId()))
                    .filter(player -> {
                        try {
                            return ticTacToeService.checkPlayerTurn(player, step, ticTacToeField);
                        } catch (WrongCoordinateException e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .forEach(pl -> {
                        System.out.println("step N " + step.getStepOrder() +
                                " " + pl.getPlayerName() + " goes to " + step.getStepCoordinate());
                        showGameBoard(ticTacToeField);
                    });
        }
    }

    public static void showGameBoard(String[][] ticTacToe) {
        System.out.println("Game board looks like: ");
        for (String[] arr : ticTacToe) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
    }

    public static String[][] initGame() {
        return new String[][]{
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };
    }
}
