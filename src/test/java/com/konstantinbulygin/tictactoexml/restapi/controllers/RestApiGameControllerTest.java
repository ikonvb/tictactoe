package com.konstantinbulygin.tictactoexml.restapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.konstantinbulygin.tictactoexml.model.Player;
import com.konstantinbulygin.tictactoexml.model.Step;
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

import static com.konstantinbulygin.tictactoexml.service.GameParser.initGame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestApiGameControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

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
    void playGameTestIsAccepted() throws Exception {

        Player player = new Player();
        player.setId(1);
        player.setName("Peter");
        player.setStep(new Step("1", "1", "1"));
        player.setSymbol("X");
        String content = objectWriter.writeValueAsString(player);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/gameplay/play")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(request)
                .andExpect(status().isAccepted());
    }

    @Test
    void playGameTestIsBadRequest() throws Exception {

        Player player = new Player();
        player.setId(1);
        player.setName("Peter");
        player.setStep(new Step("1", "1", "1"));
        player.setSymbol("X");

        String content = objectWriter.writeValueAsString(player);

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
    void playGameTestIsOk() throws Exception {

        String[][] board = new String[][]{{"X", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};

        Player player = new Player();
        player.setId(1);
        player.setName("Peter");
        player.setSymbol("X");
        player.setStep(new Step("1", "1", "1"));

        String content = objectWriter.writeValueAsString(player);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/gameplay/play")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);


        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals(Arrays.deepToString(board), result.getResponse().getContentAsString());

    }
}