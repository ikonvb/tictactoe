package com.konstantinbulygin.tictactoexml.restapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.konstantinbulygin.tictactoexml.model.Game;
import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.restapi.GameStep;
import com.konstantinbulygin.tictactoexml.service.GameService;
import com.konstantinbulygin.tictactoexml.service.PlayerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
    private Player savedPlayer;
    private GameStep gameStep;
    List<Player> playerList;

    @BeforeEach
    void initState() {
        Player player = new Player();
        player.setPlayerName("Peter");
        player.setSymbol("X");
        this.player = player;
        this.savedPlayer = playerService.save(player);
        this.gameStep = getGameStep(savedPlayer);
        this.playerList = playerService.findAll();
    }

    @AfterEach
    void flushState() {
        this.player = null;
        this.savedPlayer = null;
        this.gameStep = null;
        this.playerList = null;
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
        assertNotNull(savedPlayer);
    }

    @Test
    void startNewGameTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/gameplay/start/new/game");
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    void playGameIsAcceptedTest() throws Exception {
        String content = objectWriter.writeValueAsString(gameStep);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/gameplay/play")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isAccepted());
    }

    private GameStep getGameStep(Player savedPlayer) {
        Game game = new Game();
        Game savedGame = gameService.save(game);
        GameStep gameStep = new GameStep();
        gameStep.setPlayerId(savedPlayer.getPlayerId());
        gameStep.setGameId(savedGame.getGameId());
        gameStep.setStepOrder("1");
        gameStep.setStepCoordinate("1");
        return gameStep;
    }
}