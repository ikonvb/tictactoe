package com.konstantinbulygin.tictactoexml.repository;

import com.konstantinbulygin.tictactoexml.exceptions.WrongCoordinateException;
import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.Step;

public interface TicTacToe {

    boolean checkWinner(String[][] ticTacToe, Player player);

    boolean checkPlayerToWin(Player player, String line);

    boolean checkPlayerTurn(Player player, Step step, String[][] ticTacToe) throws WrongCoordinateException;
}
