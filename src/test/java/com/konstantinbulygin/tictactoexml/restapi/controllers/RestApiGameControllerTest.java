package com.konstantinbulygin.tictactoexml.restapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.konstantinbulygin.tictactoexml.model.Game;
import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.restapi.GameStep;
import com.konstantinbulygin.tictactoexml.service.GameService;
import com.konstantinbulygin.tictactoexml.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestApiGameControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ObjectWriter objectWriter = objectMapper.writer();

    @Autowired
    PlayerService playerService;

    @Autowired
    GameService gameService;

    @Autowired
    MockMvc mockMvc;

    private Player player;

    @BeforeEach
    void initPlayer() {
        this.player = new Player();
        player.setPlayerName("Peter");
        player.setSymbol("X");
    }

    @Test
    void loginPlayerIsOkStatusTest() throws Exception {
        String content = objectWriter.writeValueAsString(player);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/gameplay/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void loginPlayerTest() {
        Player savedPlayer = playerService.save(player);
        assertNotNull(savedPlayer);
    }

    @Test
    void showAllPlayersSizeTest() {
        playerService.save(player);
        List<Player> playerList = playerService.findAll();
        assertEquals(1, playerList.size());
    }

    @Test
    void startNewGameTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/gameplay/start/new/game");
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void playGameIsAcceptedTest() throws Exception {

        Player savedPlayer = playerService.save(player);
        Game game = new Game();
        Game savedGame = gameService.save(game);

        GameStep gameStep = new GameStep();
        gameStep.setPlayerId(savedPlayer.getPlayerId());
        gameStep.setGameId(savedGame.getGameId());
        gameStep.setStepOrder("1");
        gameStep.setStepCoordinate("1");

        String content = objectWriter.writeValueAsString(gameStep);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/gameplay/play")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isAccepted());
    }

    @Test
    void playGameIsBadRequestTest() throws Exception {
        Player savedPlayer = playerService.save(player);

        Game game = new Game();
        Game savedGame = gameService.save(game);

        GameStep gameStep = new GameStep();
        gameStep.setPlayerId(savedPlayer.getPlayerId());
        gameStep.setGameId(savedGame.getGameId());
        gameStep.setStepOrder("1");
        gameStep.setStepCoordinate("1");

        String content = objectWriter.writeValueAsString(gameStep);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/gameplay/play")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isAccepted());

        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    void playGameIsOkTest() throws Exception {

        String[][] board = new String[][]{{"X", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
        Player savedPlayer = playerService.save(player);

        Game game = new Game();
        Game savedGame = gameService.save(game);

        GameStep gameStep = new GameStep();
        gameStep.setPlayerId(savedPlayer.getPlayerId());
        gameStep.setGameId(savedGame.getGameId());
        gameStep.setStepOrder("1");
        gameStep.setStepCoordinate("1");

        String content = objectWriter.writeValueAsString(gameStep);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/gameplay/play")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(Arrays.deepToString(board), result.getResponse().getContentAsString());
    }
}