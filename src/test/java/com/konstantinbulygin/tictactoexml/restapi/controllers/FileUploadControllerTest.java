package com.konstantinbulygin.tictactoexml.restapi.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FileUploadControllerTest {

    @Autowired
    MockMvc mockMvc;

    List<Path> filesToBeDeleted = new ArrayList<>();

    @Test
    void loadFileNotJsonXMLTest() throws Exception {
        String fileName = "testFile.txt";
        String MOCK_TXT_FILE = "mock text";
        MockMultipartFile sampleFile = new MockMultipartFile(
                "file",
                fileName,
                "text/plain",
                MOCK_TXT_FILE.getBytes()
        );
        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/gameplay/upload");

        mockMvc.perform(multipartRequest.file(sampleFile))
                .andExpect(status().isNotAcceptable());

        Path docRootPath = Path.of(fileName);
        filesToBeDeleted.add(docRootPath);
        assertThat(Files.exists(docRootPath)).isFalse();
    }

    @Test
    void loadFileJsonTest() throws Exception {
        String fileName = "testFile.json";
        String MOCK_JSON_FILE = "{\"players\":[{\"id\":1,\"name\":\"ddd\",\"symbol\":\"X\"},{\"id\":2,\"name\":\"fff\",\"symbol\":\"O\"}],\"game\":{\"steps\":[{\"num\":\"1\",\"playerId\":\"1\",\"step\":\"1\"},{\"num\":\"2\",\"playerId\":\"2\",\"step\":\"2\"},{\"num\":\"3\",\"playerId\":\"1\",\"step\":\"3\"},{\"num\":\"4\",\"playerId\":\"2\",\"step\":\"4\"},{\"num\":\"5\",\"playerId\":\"1\",\"step\":\"5\"},{\"num\":\"6\",\"playerId\":\"2\",\"step\":\"6\"},{\"num\":\"7\",\"playerId\":\"1\",\"step\":\"7\"}]},\"gameResult\":{\"player\":{\"id\":1,\"name\":\"ddd\",\"symbol\":\"X\"}}}";
        MockMultipartFile sampleFile = new MockMultipartFile(
                "file",
                fileName,
                "text/json",
                MOCK_JSON_FILE.getBytes()
        );
        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/gameplay/upload");

        mockMvc.perform(multipartRequest.file(sampleFile))
                .andExpect(status().isOk());

        Path docRootPath = Path.of(fileName);
        filesToBeDeleted.add(docRootPath);
        assertThat(Files.exists(docRootPath)).isTrue();
    }

    @Test
    void loadFileXmlTest() throws Exception {
        String fileName = "testFile.xml";
        String MOCK_XML_FILE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<Gameplay>\n" +
                "    <Player id=\"1\" name=\"fff\" symbol=\"X\"/>\n" +
                "    <Player id=\"2\" name=\"ggg\" symbol=\"O\"/>\n" +
                "    <Game>\n" +
                "        <Step num=\"1\" playerId=\"1\">1</Step>\n" +
                "        <Step num=\"2\" playerId=\"2\">2</Step>\n" +
                "        <Step num=\"3\" playerId=\"1\">3</Step>\n" +
                "        <Step num=\"4\" playerId=\"2\">4</Step>\n" +
                "        <Step num=\"5\" playerId=\"1\">5</Step>\n" +
                "        <Step num=\"6\" playerId=\"2\">7</Step>\n" +
                "        <Step num=\"7\" playerId=\"1\">8</Step>\n" +
                "        <Step num=\"8\" playerId=\"2\">9</Step>\n" +
                "        <Step num=\"9\" playerId=\"1\">6</Step>\n" +
                "    </Game>\n" +
                "    <GameResult>\n" +
                "        <Draw>It`s a draw!!!</Draw>\n" +
                "    </GameResult>\n" +
                "</Gameplay>";
        MockMultipartFile sampleFile = new MockMultipartFile(
                "file",
                fileName,
                "text/xml",
                MOCK_XML_FILE.getBytes()
        );
        MockMultipartHttpServletRequestBuilder multipartRequest =
                MockMvcRequestBuilders.multipart("/gameplay/upload");

        mockMvc.perform(multipartRequest.file(sampleFile))
                .andExpect(status().isOk());

        Path docRootPath = Path.of(fileName);
        filesToBeDeleted.add(docRootPath);
        assertThat(Files.exists(docRootPath)).isTrue();
    }

    @AfterEach
    public void cleanup() {
        filesToBeDeleted.forEach(path -> {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}