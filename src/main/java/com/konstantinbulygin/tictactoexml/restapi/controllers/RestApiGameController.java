package com.konstantinbulygin.tictactoexml.restapi.controllers;

import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.service.GameParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static com.konstantinbulygin.tictactoexml.tictactoe.TicTacToe.*;
import static com.konstantinbulygin.tictactoexml.util.GameUtil.DRAW;

@RestController
@RequestMapping(path = "/gameplay", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Api("Main Controller")
public class RestApiGameController extends GameParser {

    private String[][] gameField = initGame();

    @GetMapping("/start")
    @ApiOperation("Shows start state of the game")
    public ResponseEntity<String> startState() {
        return new ResponseEntity<>(Arrays.deepToString(gameField), HttpStatus.OK);
    }

    @GetMapping("/reset")
    @ApiOperation("Resets state of the game")
    public ResponseEntity<String> resetGame() {
        this.gameField = initGame();
        return new ResponseEntity<>(Arrays.deepToString(gameField), HttpStatus.OK);
    }

    @PostMapping(value = "/play", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Play game with json body")
    public ResponseEntity<String> playGame(@RequestBody Player player) {

        if (!checkPlayerTurn(player, gameField)) {
            return new ResponseEntity<>(Arrays.deepToString(gameField), HttpStatus.BAD_REQUEST);
        }
        if (checkWinner(gameField, player)) {
            if (winner.getName().equals(player.getName())) {
                return new ResponseEntity<>(winner + player.getSymbol(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(DRAW, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Arrays.deepToString(gameField), HttpStatus.ACCEPTED);
    }
}
