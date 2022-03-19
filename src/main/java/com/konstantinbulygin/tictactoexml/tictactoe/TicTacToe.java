package com.konstantinbulygin.tictactoexml.tictactoe;

import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.Step;
import com.konstantinbulygin.tictactoexml.service.GameDocumentWriter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.konstantinbulygin.tictactoexml.util.GameUtil.*;

public class TicTacToe {

    private static Player winner;
    private static String yesOrNot = "y";
    private static boolean gameOver;
    private static int counter = 0;
    private static int gameTry = 1;
    private static int id = 1;

    public void playTicTacToe(GameDocumentWriter writer) {
        Player player1 = new Player();
        Player player2 = new Player();

        Scanner scanner = null;

        while (yesOrNot.equalsIgnoreCase("y")) {

            List<Step> steps = new ArrayList<>();
            List<Player> players = new ArrayList<>();

            scanner = new Scanner(System.in);

            String[][] ticTacToe = initGame();

            initPlayers(player1, player2, scanner, players);

            while (!gameOver) {
                getPlayerStepAndCheck(player1, scanner, steps, ticTacToe);
                if (checkWinner(ticTacToe, player1)) {
                    break;
                }
                getPlayerStepAndCheck(player2, scanner, steps, ticTacToe);
                if (checkWinner(ticTacToe, player2)) {
                    break;
                }
            }

            showGameBoard(ticTacToe);
            showGameResult();
            writer.writeGameResult(players, steps, gameTry, winner);
            endGame(scanner);
            gameTry++;
        }
        assert scanner != null;
        scanner.close();
    }

    private void getPlayerStepAndCheck(Player player1, Scanner scanner, List<Step> steps, String[][] ticTacToe) {
        String playerStep;
        playerStep = takePlayerStep(player1, ticTacToe, scanner);
        if (!checkPlayerTurn(player1, playerStep, ticTacToe)) {
            playerStep = takePlayerStep(player1, ticTacToe, scanner);
            while (!checkPlayerTurn(player1, playerStep, ticTacToe)) {
                playerStep = takePlayerStep(player1, ticTacToe, scanner);
            }
        }
        steps.add(new Step(String.valueOf(counter), String.valueOf(player1.getId()), playerStep));
    }

    private static void endGame(Scanner scanner) {
        System.out.println("Do you want to play again?");
        System.out.println("Please enter Y or N");
        yesOrNot = scanner.next();

        while (!(yesOrNot.equalsIgnoreCase("y") || yesOrNot.equalsIgnoreCase("n"))) {
            System.out.println("Do you want to play again?");
            System.out.println("Please enter Y or N");
            yesOrNot = scanner.next();
        }
    }

    private static void initPlayers(Player player1, Player player2, Scanner scanner, List<Player> players) {

        player1.setName(getPlayersName(scanner, "Enter first player name: "));
        player1.setId(id++);
        player1.setSymbol(X);

        player2.setName(getPlayersName(scanner, "Enter second player name: "));
        player2.setId(id++);
        player2.setSymbol(O);

        players.add(player1);
        players.add(player2);
    }

    public static String[][] initGame() {

        winner = null;
        gameOver = false;
        counter = 0;

        return new String[][]{
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };
    }

    public static void showGameResult() {
        if (winner == null) {
            System.out.println(STRING_GAME_OVER);
            System.out.println(STRING_DRAW);
        } else {
            System.out.println(STRING_GAME_OVER);
            System.out.println(STRING_WINNER + winner.getName());
        }
    }

    public static String takePlayerStep(Player player, String[][] ticTacToe, Scanner scanner) {
        String playerStep;
        showGameBoard(ticTacToe);
        System.out.println(player.getName() + " your symbol is " + player.getSymbol());
        System.out.print("Please choose a slot where do you want to go:");
        playerStep = scanner.next();
        return playerStep;
    }

    public static boolean checkWinner(String[][] ticTacToe, Player player) {
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
            gameOver = true;
            return true;
        }
        return false;
    }

    private static boolean checkPlayerToWin(Player player, String line) {
        if (line.equals(XXX)) {
            gameOver = true;
            winner = player.getSymbol().equals(X) ? player : null;
            return true;
        } else if (line.equals(OOO)) {
            gameOver = true;
            winner = player.getSymbol().equals(O) ? player : null;
            return true;
        }
        return false;
    }

    public static boolean checkPlayerTurn(Player player, String playerStep, String[][] ticTacToe) {

        switch (playerStep) {
            case "1":
                if (player.getSymbol().equals(X)) {
                    if (!ticTacToe[0][0].equals(player.getSymbol()) && !ticTacToe[0][0].equals(O)) {
                        ticTacToe[0][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (player.getSymbol().equals(O)) {
                    if (!ticTacToe[0][0].equals(player.getSymbol()) && !ticTacToe[0][0].equals(X)) {
                        ticTacToe[0][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "2":
                if (player.getSymbol().equals(X)) {
                    if (!ticTacToe[0][1].equals(player.getSymbol()) && !ticTacToe[0][1].equals(O)) {
                        ticTacToe[0][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (player.getSymbol().equals(O)) {
                    if (!ticTacToe[0][1].equals(player.getSymbol()) && !ticTacToe[0][1].equals(X)) {
                        ticTacToe[0][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "3":
                if (player.getSymbol().equals(X)) {
                    if (!ticTacToe[0][2].equals(player.getSymbol()) && !ticTacToe[0][2].equals(O)) {
                        ticTacToe[0][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (player.getSymbol().equals(O)) {
                    if (!ticTacToe[0][2].equals(player.getSymbol()) && !ticTacToe[0][2].equals(X)) {
                        ticTacToe[0][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "4":
                if (player.getSymbol().equals(X)) {
                    if (!ticTacToe[1][0].equals(player.getSymbol()) && !ticTacToe[1][0].equals(O)) {
                        ticTacToe[1][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (player.getSymbol().equals(O)) {
                    if (!ticTacToe[1][0].equals(player.getSymbol()) && !ticTacToe[1][0].equals(X)) {
                        ticTacToe[1][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "5":
                if (player.getSymbol().equals(X)) {
                    if (!ticTacToe[1][1].equals(player.getSymbol()) && !ticTacToe[1][1].equals(O)) {
                        ticTacToe[1][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (player.getSymbol().equals(O)) {
                    if (!ticTacToe[1][1].equals(player.getSymbol()) && !ticTacToe[1][1].equals(X)) {
                        ticTacToe[1][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "6":
                if (player.getSymbol().equals(X)) {
                    if (!ticTacToe[1][2].equals(player.getSymbol()) && !ticTacToe[1][2].equals(O)) {
                        ticTacToe[1][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (player.getSymbol().equals(O)) {
                    if (!ticTacToe[1][2].equals(player.getSymbol()) && !ticTacToe[1][2].equals(X)) {
                        ticTacToe[1][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "7":
                if (player.getSymbol().equals(X)) {
                    if (!ticTacToe[2][0].equals(player.getSymbol()) && !ticTacToe[2][0].equals(O)) {
                        ticTacToe[2][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (player.getSymbol().equals(O)) {
                    if (!ticTacToe[2][0].equals(player.getSymbol()) && !ticTacToe[2][0].equals(X)) {
                        ticTacToe[2][0] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "8":
                if (player.getSymbol().equals(X)) {
                    if (!ticTacToe[2][1].equals(player.getSymbol()) && !ticTacToe[2][1].equals(O)) {
                        ticTacToe[2][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (player.getSymbol().equals(O)) {
                    if (!ticTacToe[2][1].equals(player.getSymbol()) && !ticTacToe[2][1].equals(X)) {
                        ticTacToe[2][1] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "9":
                if (player.getSymbol().equals(X)) {
                    if (!ticTacToe[2][2].equals(player.getSymbol()) && !ticTacToe[2][2].equals(O)) {
                        ticTacToe[2][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (player.getSymbol().equals(O)) {
                    if (!ticTacToe[2][2].equals(player.getSymbol()) && !ticTacToe[2][2].equals(X)) {
                        ticTacToe[2][2] = player.getSymbol();
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
        }
        return false;
    }

    public static void showGameBoard(String[][] ticTacToe) {
        System.out.println("Game board looks like: ");
        for (String[] arr : ticTacToe) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println();
    }

    public static String getPlayersName(Scanner scanner, String s) {
        System.out.println(s);
        String playerName = scanner.nextLine().trim().toLowerCase();
        while (playerName.isEmpty()) {
            System.out.println(s);
            playerName = scanner.nextLine().trim().toLowerCase();
        }
        return playerName;
    }
}
