package com.konstantinbulygin.tictactoexml.restapi.controllers;

import com.konstantinbulygin.tictactoexml.service.GameParser;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.konstantinbulygin.tictactoexml.util.GameUtil.*;

@RestController
@RequestMapping(path = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class RestApiGame extends GameParser {

    private static String winner;
    private static int counter;
    String[][] gameField = initGame();

    @GetMapping("/start")
    @ApiOperation("Shows start state of the game")
    public ResponseEntity<String[][]> startState() {
        return new ResponseEntity<>(gameField, HttpStatus.OK);
    }

    @GetMapping("/reset")
    @ApiOperation("Resets state of the game")
    public ResponseEntity<String[][]> resetGame() {
        this.gameField = initGame();
        return new ResponseEntity<>(gameField, HttpStatus.OK);
    }

    @GetMapping("/play/{playerName}/{symbol}/{step}")
    @ApiOperation("Play game with endpoints")
    public ResponseEntity<String[][]> game(@PathVariable String playerName, @PathVariable String symbol, @PathVariable String step) {
        if (!checkPlayerTurn(symbol, step, gameField)) {
            return new ResponseEntity<>(gameField, HttpStatus.BAD_REQUEST);
        }
        if (checkWinner(playerName, gameField)) {
            if (winner.equals(playerName)) {
                return new ResponseEntity<>(new String[][]{new String[]{winner}, new String[]{symbol}}, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new String[][]{new String[]{winner}, null}, HttpStatus.OK);
            }

        }
        return new ResponseEntity<>(gameField, HttpStatus.ACCEPTED);
    }

    public static boolean checkPlayerTurn(String symbol, String step, String[][] ticTacToe) {
        switch (step) {
            case "1":
                if (symbol.equals(X)) {
                    if (!ticTacToe[0][0].equals(symbol) && !ticTacToe[0][0].equals(O)) {
                        ticTacToe[0][0] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (symbol.equals(O)) {
                    if (!ticTacToe[0][0].equals(symbol) && !ticTacToe[0][0].equals(X)) {
                        ticTacToe[0][0] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "2":
                if (symbol.equals(X)) {
                    if (!ticTacToe[0][1].equals(symbol) && !ticTacToe[0][1].equals(O)) {
                        ticTacToe[0][1] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (symbol.equals(O)) {
                    if (!ticTacToe[0][1].equals(symbol) && !ticTacToe[0][1].equals(X)) {
                        ticTacToe[0][1] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "3":
                if (symbol.equals(X)) {
                    if (!ticTacToe[0][2].equals(symbol) && !ticTacToe[0][2].equals(O)) {
                        ticTacToe[0][2] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (symbol.equals(O)) {
                    if (!ticTacToe[0][2].equals(symbol) && !ticTacToe[0][2].equals(X)) {
                        ticTacToe[0][2] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "4":
                if (symbol.equals(X)) {
                    if (!ticTacToe[1][0].equals(symbol) && !ticTacToe[1][0].equals(O)) {
                        ticTacToe[1][0] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (symbol.equals(O)) {
                    if (!ticTacToe[1][0].equals(symbol) && !ticTacToe[1][0].equals(X)) {
                        ticTacToe[1][0] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "5":
                if (symbol.equals(X)) {
                    if (!ticTacToe[1][1].equals(symbol) && !ticTacToe[1][1].equals(O)) {
                        ticTacToe[1][1] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (symbol.equals(O)) {
                    if (!ticTacToe[1][1].equals(symbol) && !ticTacToe[1][1].equals(X)) {
                        ticTacToe[1][1] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "6":
                if (symbol.equals(X)) {
                    if (!ticTacToe[1][2].equals(symbol) && !ticTacToe[1][2].equals(O)) {
                        ticTacToe[1][2] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (symbol.equals(O)) {
                    if (!ticTacToe[1][2].equals(symbol) && !ticTacToe[1][2].equals(X)) {
                        ticTacToe[1][2] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "7":
                if (symbol.equals(X)) {
                    if (!ticTacToe[2][0].equals(symbol) && !ticTacToe[2][0].equals(O)) {
                        ticTacToe[2][0] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (symbol.equals(O)) {
                    if (!ticTacToe[2][0].equals(symbol) && !ticTacToe[2][0].equals(X)) {
                        ticTacToe[2][0] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "8":
                if (symbol.equals(X)) {
                    if (!ticTacToe[2][1].equals(symbol) && !ticTacToe[2][1].equals(O)) {
                        ticTacToe[2][1] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (symbol.equals(O)) {
                    if (!ticTacToe[2][1].equals(symbol) && !ticTacToe[2][1].equals(X)) {
                        ticTacToe[2][1] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                }
            case "9":
                if (symbol.equals(X)) {
                    if (!ticTacToe[2][2].equals(symbol) && !ticTacToe[2][2].equals(O)) {
                        ticTacToe[2][2] = symbol;
                        counter++;
                        return true;
                    } else {
                        System.out.println("already exists choose another slot");
                        return false;
                    }
                } else if (symbol.equals(O)) {
                    if (!ticTacToe[2][2].equals(symbol) && !ticTacToe[2][2].equals(X)) {
                        ticTacToe[2][2] = symbol;
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

    public static boolean checkWinner(String name, String[][] ticTacToe) {
        String line;
        for (int a = 0; a < 8; a++) {
            switch (a) {
                case 0:
                    line = ticTacToe[0][0] + ticTacToe[0][1] + ticTacToe[0][2];
                    if (checkPlayerToWin(name, line)) return true;
                    break;
                case 1:
                    line = ticTacToe[1][0] + ticTacToe[1][1] + ticTacToe[1][2];
                    if (checkPlayerToWin(name, line)) return true;
                    break;
                case 2:
                    line = ticTacToe[2][0] + ticTacToe[2][1] + ticTacToe[2][2];
                    if (checkPlayerToWin(name, line)) return true;
                    break;
                case 3:
                    line = ticTacToe[0][0] + ticTacToe[1][0] + ticTacToe[2][0];
                    if (checkPlayerToWin(name, line)) return true;
                    break;
                case 4:
                    line = ticTacToe[0][1] + ticTacToe[1][1] + ticTacToe[2][1];
                    if (checkPlayerToWin(name, line)) return true;
                    break;
                case 5:
                    line = ticTacToe[0][2] + ticTacToe[1][2] + ticTacToe[2][2];
                    if (checkPlayerToWin(name, line)) return true;
                    break;
                case 6:
                    line = ticTacToe[0][0] + ticTacToe[1][1] + ticTacToe[2][2];
                    if (checkPlayerToWin(name, line)) return true;
                    break;
                case 7:
                    line = ticTacToe[0][2] + ticTacToe[1][1] + ticTacToe[2][0];
                    if (checkPlayerToWin(name, line)) return true;
                    break;
            }
        }
        if (counter == 9) {
            winner = "It`s a " + DRAW;
            return true;
        }
        return false;
    }

    private static boolean checkPlayerToWin(String name, String line) {
        if (line.equals(XXX)) {
            winner = name;
            return true;
        } else if (line.equals(OOO)) {
            winner = name;
            return true;
        }
        return false;
    }

    public String[][] initGame() {
        return new String[][]{
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };
    }
}
