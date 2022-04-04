package com.konstantinbulygin.tictactoexml.writers;

import com.google.gson.Gson;
import com.konstantinbulygin.tictactoexml.model.*;
import com.konstantinbulygin.tictactoexml.repository.GameDocumentWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static com.konstantinbulygin.tictactoexml.util.GameUtil.FILE_NAME;
import static com.konstantinbulygin.tictactoexml.util.GameUtil.JSON_EXTENSION;


public class JsonGameWriter implements GameDocumentWriter {

    @Override
    public void writeGameResult(List<Player> players, List<Step> steps, int gameTry, Player winner) {
        Game game = new Game(steps);
        GameResult gameResult = new GameResult(winner);
        Gameplay gameplay = new Gameplay(players, game, gameResult);
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(FILE_NAME + gameTry + JSON_EXTENSION)) {
            gson.toJson(gameplay, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
