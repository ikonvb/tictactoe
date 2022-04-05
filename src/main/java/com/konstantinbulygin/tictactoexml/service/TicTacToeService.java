package com.konstantinbulygin.tictactoexml.service;

import com.konstantinbulygin.tictactoexml.exceptions.WrongCoordinateException;
import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.Step;
import com.konstantinbulygin.tictactoexml.repository.TicTacToe;
import org.springframework.stereotype.Service;

import static com.konstantinbulygin.tictactoexml.util.GameUtil.*;

@Service
public class TicTacToeService implements TicTacToe {

    public static Player winner;
    private static int counter = 0;

    @Override
    public boolean checkPlayerToWin(Player player, String line) {
        if (line.equals(LINE_XXX)) {
            winner = player.getSymbol().equals(SYMBOL_X) ? player : null;
            counter = 0;
            return true;
        } else if (line.equals(LINE_OOO)) {
            winner = player.getSymbol().equals(SYMBOL_O) ? player : null;
            counter = 0;
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPlayerTurn(Player player, Step step, String[][] ticTacToe) throws WrongCoordinateException {
        switch (step.getStepCoordinate()) {
            case "1":
                if (player.getSymbol().equals(SYMBOL_X)) {
                    if (!ticTacToe[0][0].equals(player.getSymbol()) && !ticTacToe[0][0].equals(SYMBOL_O)) {
                        ticTacToe[0][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                } else if (player.getSymbol().equals(SYMBOL_O)) {
                    if (!ticTacToe[0][0].equals(player.getSymbol()) && !ticTacToe[0][0].equals(SYMBOL_X)) {
                        ticTacToe[0][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                }
            case "2":
                if (player.getSymbol().equals(SYMBOL_X)) {
                    if (!ticTacToe[0][1].equals(player.getSymbol()) && !ticTacToe[0][1].equals(SYMBOL_O)) {
                        ticTacToe[0][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                } else if (player.getSymbol().equals(SYMBOL_O)) {
                    if (!ticTacToe[0][1].equals(player.getSymbol()) && !ticTacToe[0][1].equals(SYMBOL_X)) {
                        ticTacToe[0][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                }
            case "3":
                if (player.getSymbol().equals(SYMBOL_X)) {
                    if (!ticTacToe[0][2].equals(player.getSymbol()) && !ticTacToe[0][2].equals(SYMBOL_O)) {
                        ticTacToe[0][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                } else if (player.getSymbol().equals(SYMBOL_O)) {
                    if (!ticTacToe[0][2].equals(player.getSymbol()) && !ticTacToe[0][2].equals(SYMBOL_X)) {
                        ticTacToe[0][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                }
            case "4":
                if (player.getSymbol().equals(SYMBOL_X)) {
                    if (!ticTacToe[1][0].equals(player.getSymbol()) && !ticTacToe[1][0].equals(SYMBOL_O)) {
                        ticTacToe[1][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                } else if (player.getSymbol().equals(SYMBOL_O)) {
                    if (!ticTacToe[1][0].equals(player.getSymbol()) && !ticTacToe[1][0].equals(SYMBOL_X)) {
                        ticTacToe[1][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                }
            case "5":
                if (player.getSymbol().equals(SYMBOL_X)) {
                    if (!ticTacToe[1][1].equals(player.getSymbol()) && !ticTacToe[1][1].equals(SYMBOL_O)) {
                        ticTacToe[1][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                } else if (player.getSymbol().equals(SYMBOL_O)) {
                    if (!ticTacToe[1][1].equals(player.getSymbol()) && !ticTacToe[1][1].equals(SYMBOL_X)) {
                        ticTacToe[1][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                }
            case "6":
                if (player.getSymbol().equals(SYMBOL_X)) {
                    if (!ticTacToe[1][2].equals(player.getSymbol()) && !ticTacToe[1][2].equals(SYMBOL_O)) {
                        ticTacToe[1][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                } else if (player.getSymbol().equals(SYMBOL_O)) {
                    if (!ticTacToe[1][2].equals(player.getSymbol()) && !ticTacToe[1][2].equals(SYMBOL_X)) {
                        ticTacToe[1][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                }
            case "7":
                if (player.getSymbol().equals(SYMBOL_X)) {
                    if (!ticTacToe[2][0].equals(player.getSymbol()) && !ticTacToe[2][0].equals(SYMBOL_O)) {
                        ticTacToe[2][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                } else if (player.getSymbol().equals(SYMBOL_O)) {
                    if (!ticTacToe[2][0].equals(player.getSymbol()) && !ticTacToe[2][0].equals(SYMBOL_X)) {
                        ticTacToe[2][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                }
            case "8":
                if (player.getSymbol().equals(SYMBOL_X)) {
                    if (!ticTacToe[2][1].equals(player.getSymbol()) && !ticTacToe[2][1].equals(SYMBOL_O)) {
                        ticTacToe[2][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                } else if (player.getSymbol().equals(SYMBOL_O)) {
                    if (!ticTacToe[2][1].equals(player.getSymbol()) && !ticTacToe[2][1].equals(SYMBOL_X)) {
                        ticTacToe[2][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                }
            case "9":
                if (player.getSymbol().equals(SYMBOL_X)) {
                    if (!ticTacToe[2][2].equals(player.getSymbol()) && !ticTacToe[2][2].equals(SYMBOL_O)) {
                        ticTacToe[2][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                } else if (player.getSymbol().equals(SYMBOL_O)) {
                    if (!ticTacToe[2][2].equals(player.getSymbol()) && !ticTacToe[2][2].equals(SYMBOL_X)) {
                        ticTacToe[2][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        throw new WrongCoordinateException("You`ve chosen a wrong coordinate, please choose another");
                    }
                }
        }
        return false;
    }

    @Override
    public boolean checkWinner(String[][] ticTacToe, Player player) {
        String line;
        for (int a = 0; a < 8; a++) {
            switch (a) {
                case 0:
                    line = ticTacToe[0][0] + ticTacToe[0][1] + ticTacToe[0][2];
                    if (checkPlayerToWin(player, line)) return true;
                    break;
                case 1:
                    line = ticTacToe[1][0] + ticTacToe[1][1] + ticTacToe[1][2];
                    if (checkPlayerToWin(player, line)) return true;
                    break;
                case 2:
                    line = ticTacToe[2][0] + ticTacToe[2][1] + ticTacToe[2][2];
                    if (checkPlayerToWin(player, line)) return true;
                    break;
                case 3:
                    line = ticTacToe[0][0] + ticTacToe[1][0] + ticTacToe[2][0];
                    if (checkPlayerToWin(player, line)) return true;
                    break;
                case 4:
                    line = ticTacToe[0][1] + ticTacToe[1][1] + ticTacToe[2][1];
                    if (checkPlayerToWin(player, line)) return true;
                    break;
                case 5:
                    line = ticTacToe[0][2] + ticTacToe[1][2] + ticTacToe[2][2];
                    if (checkPlayerToWin(player, line)) return true;
                    break;
                case 6:
                    line = ticTacToe[0][0] + ticTacToe[1][1] + ticTacToe[2][2];
                    if (checkPlayerToWin(player, line)) return true;
                    break;
                case 7:
                    line = ticTacToe[0][2] + ticTacToe[1][1] + ticTacToe[2][0];
                    if (checkPlayerToWin(player, line)) return true;
                    break;
            }
        }
        if (counter == 9) {
            winner = null;
            return true;
        }
        return false;
    }
}
