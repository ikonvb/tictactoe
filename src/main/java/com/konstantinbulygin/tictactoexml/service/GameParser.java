package com.konstantinbulygin.tictactoexml.service;

import com.konstantinbulygin.tictactoexml.model.Gameplay;
import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.Step;

import static com.konstantinbulygin.tictactoexml.tictactoe.TicTacToe.checkPlayerTurn;
import static com.konstantinbulygin.tictactoexml.tictactoe.TicTacToe.showGameBoard;
import static com.konstantinbulygin.tictactoexml.util.GameUtil.STRING_DRAW;
import static com.konstantinbulygin.tictactoexml.util.GameUtil.STRING_WINNER;

public class GameParser {

    private final String[][] ticTacToe = initGame();

    public void showGameResult(Gameplay gameplay) {
        if (gameplay.getGameResult().getPlayer() != null) {
            System.out.println(
                    STRING_WINNER + gameplay.getGameResult().getPlayer().getName() + " as " +
                            gameplay.getGameResult().getPlayer().getSymbol());
        } else {
            System.out.println(STRING_DRAW);
        }
    }

    public void showGameFirstState(Gameplay gameplay) {
        for (Player pl : gameplay.getPlayers()) {
            System.out.println("Player name: " + pl.getName() + " symbol is " + pl.getSymbol());
        }
        System.out.println();
        showGameBoard(ticTacToe);
    }

    public void showGameSteps(Gameplay gameplay) {
        for (Step step : gameplay.getGame().getSteps()) {
            gameplay.getPlayers().stream()
                    .filter(player -> player.getId() == Integer.parseInt(step.getPlayerId()))
                    .filter(player -> checkPlayerTurn(player, step.getStep(), ticTacToe))
                    .forEach(pl -> {
                        System.out.println("step N " + step.getNum() + " " + pl.getName() + " goes to " + step.getStep());
                        showGameBoard(ticTacToe);
                    });
        }
    }

    public String[][] initGame() {
        return new String[][]{
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };
    }
}
