package com.konstantinbulygin.tictactoexml.restapi.controllers;

import com.konstantinbulygin.tictactoexml.exceptions.WrongCoordinateException;
import com.konstantinbulygin.tictactoexml.model.Game;
import com.konstantinbulygin.tictactoexml.model.GameResult;
import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.Step;
import com.konstantinbulygin.tictactoexml.model.restapi.GameStep;
import com.konstantinbulygin.tictactoexml.model.restapi.LoginPlayer;
import com.konstantinbulygin.tictactoexml.service.*;
import com.konstantinbulygin.tictactoexml.util.GameParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.konstantinbulygin.tictactoexml.service.TicTacToeService.winner;
import static com.konstantinbulygin.tictactoexml.util.GameUtil.DRAW;
import static com.konstantinbulygin.tictactoexml.util.GameUtil.STRING_WINNER;

@RestController
@RequestMapping(path = "/gameplay", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
@Api("Main Controller")
public class RestApiGameController extends GameParser {

    @Autowired
    PlayerService playerService;
    @Autowired
    GameService gameService;
    @Autowired
    StepService stepService;
    @Autowired
    GameResultService gameResultService;
    @Autowired
    TicTacToeService ticTacToeService;

    private String[][] gameField = initGame();

    @PostMapping("/login")
    @ApiOperation("Login player to the game and returns player info")
    public ResponseEntity<Player> loginPlayer(@RequestBody LoginPlayer loginPlayer) {

        Player player = new Player();
        player.setPlayerName(loginPlayer.getPlayerName());
        player.setSymbol(loginPlayer.getSymbol());

        Player savedPlayer = playerService.save(player);
        return new ResponseEntity<>(savedPlayer, HttpStatus.OK);
    }

    @GetMapping("/show/players")
    @ApiOperation("Shows all registered players")
    public ResponseEntity<List<Player>> showAllPlayers() {
        List<Player> playerList = playerService.findAll();
        if (playerList.size() > 0) {
            return new ResponseEntity<>(playerList, HttpStatus.OK);
        }
        return new ResponseEntity<>(playerList, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/show/results")
    @ApiOperation("Shows all game results")
    public ResponseEntity<List<GameResult>> showAllGameResults() {
        List<GameResult> gameResultList = gameResultService.findAll();
        if (gameResultList.size() > 0) {
            return new ResponseEntity<>(gameResultList, HttpStatus.OK);
        }
        return new ResponseEntity<>(gameResultList, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/start/new/game")
    @ApiOperation("Start new game, returns new game id")
    public ResponseEntity<Game> startGame() {
        this.gameField = initGame();
        Game game = new Game();
        Game savedGame = gameService.save(game);
        return new ResponseEntity<>(savedGame, HttpStatus.OK);
    }

    @PostMapping(value = "/play", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Play game, returns game field state")
    public ResponseEntity<String> playGame(@RequestBody GameStep gameStep) throws WrongCoordinateException {

        Game game = new Game();
        game.setGameId(gameStep.getGameId());

        Step step = new Step();
        step.setGame(game);
        step.setPlayerId(gameStep.getPlayerId());
        step.setStepCoordinate(gameStep.getStepCoordinate());
        step.setStepOrder(gameStep.getStepOrder());

        Optional<Player> player = playerService.findById(step.getPlayerId());

        if (player.isPresent()) {
            if (!ticTacToeService.checkPlayerTurn(player.get(), step, gameField)) {
                return new ResponseEntity<>(Arrays.deepToString(gameField), HttpStatus.BAD_REQUEST);
            }
            stepService.save(step);
            if (ticTacToeService.checkWinner(gameField, player.get())) {
                if (winner == null) {
                    saveGame(game, null);
                    return new ResponseEntity<>(DRAW, HttpStatus.OK);
                }
                if (winner.getPlayerName().equals(player.get().getPlayerName())) {
                    saveGame(game, winner);
                    return new ResponseEntity<>(STRING_WINNER + winner.getPlayerName() + " with " + player.get().getSymbol(), HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(Arrays.deepToString(gameField), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(Arrays.deepToString(gameField), HttpStatus.BAD_REQUEST);
    }

    private void saveGame(Game game, Player player) {
        GameResult gameResult = new GameResult();
        gameResult.setGame(game);
        gameResult.setPlayer(player);
        gameResultService.save(gameResult);
    }
}
