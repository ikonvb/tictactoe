package com.konstantinbulygin.tictactoexml.restapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static com.konstantinbulygin.tictactoexml.tictactoe.TicTacToe.initGame;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class RestApiGameControllerTest {

    @Autowired
    MockMvc mockMvc;

    private final String[][] gameField = initGame();

    @Test
    void startState() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/gameplay/start");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(Arrays.deepToString(gameField), result.getResponse().getContentAsString());
    }

    @Test
    void resetGame() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/gameplay/reset");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(Arrays.deepToString(gameField), result.getResponse().getContentAsString());
    }

    @Test
    void game() {

    }

    @Test
    void checkPlayerTurn() {
    }

    @Test
    void checkWinner() {
    }
}