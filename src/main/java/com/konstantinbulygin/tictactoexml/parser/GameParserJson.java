package com.konstantinbulygin.tictactoexml.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.konstantinbulygin.tictactoexml.model.Gameplay;
import com.konstantinbulygin.tictactoexml.service.GameDocumentReader;
import com.konstantinbulygin.tictactoexml.service.GameParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameParserJson extends GameParser implements GameDocumentReader {

    private Gameplay gameplay;

    @Override
    public void readGameFile(String fileName) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            Gson gson = new GsonBuilder().create();
            gameplay = gson.fromJson(reader, Gameplay.class);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (gameplay != null) {
            showGameFirstState(gameplay);
            showGameSteps(gameplay);
            showGameResult(gameplay);
        }
    }


}
