package com.konstantinbulygin.tictactoe.app;

import com.konstantinbulygin.tictactoexml.exceptions.WrongCoordinateException;
import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.Step;
import com.konstantinbulygin.tictactoexml.service.TicTacToeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.konstantinbulygin.tictactoexml.util.GameParser.initGame;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TicTacToeService.class})
class TicTacToeServiceApplicationTests {

    @Autowired
    TicTacToeService ticTacToeService;

    private String[][] gameField;
    private Player player;

    @BeforeEach
    void initState() {
        this.gameField = initGame();
        Player player = new Player();
        player.setSymbol("X");
        this.player = player;
    }

    @Test
    void checkPlayerToWinXTest() {
        String line = "XXX";
        boolean isWinner = ticTacToeService.checkPlayerToWin(player, line);
        assertTrue(isWinner);
    }

    @Test
    void checkPlayerToWinTest() {
        String line = "XXO";
        boolean isWinner = ticTacToeService.checkPlayerToWin(player, line);
        assertFalse(isWinner);
    }

    @Test
    void checkPlayerTurnTrueTest() throws WrongCoordinateException {
        Step step = new Step();
        step.setStepCoordinate("1");
        boolean validStep = ticTacToeService.checkPlayerTurn(player, step, gameField);
        assertTrue(validStep);
    }

    @Test
    void checkPlayerTurnFalseTest() throws WrongCoordinateException {
        Step step = new Step();
        step.setStepCoordinate("1");
        ticTacToeService.checkPlayerTurn(player, step, gameField);
        assertThrows(WrongCoordinateException.class, () -> ticTacToeService.checkPlayerTurn(player, step, gameField));
    }

    @Test
    void checkWinnerFalseTest() {
        boolean isWinner = ticTacToeService.checkWinner(gameField, player);
        assertFalse(isWinner);
    }

    @Test
    void checkWinnerTrueTest() {
        String[][] board = new String[][]{{"X", "2", "3"}, {"4", "X", "6"}, {"7", "8", "X"}};
        boolean isWinner = ticTacToeService.checkWinner(board, player);
        assertTrue(isWinner);
    }
}
