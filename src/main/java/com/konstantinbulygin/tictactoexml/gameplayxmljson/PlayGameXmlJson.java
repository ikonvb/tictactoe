package com.konstantinbulygin.tictactoexml.gameplayxmljson;

import com.konstantinbulygin.tictactoexml.parser.GameParserJson;
import com.konstantinbulygin.tictactoexml.parser.GameParserXml;
import com.konstantinbulygin.tictactoexml.service.GameDocumentReader;
import com.konstantinbulygin.tictactoexml.service.GameDocumentWriter;
import com.konstantinbulygin.tictactoexml.tictactoe.TicTacToe;
import com.konstantinbulygin.tictactoexml.writers.JsonGameWriter;
import com.konstantinbulygin.tictactoexml.writers.XmlGameWriter;

public class PlayGameXmlJson {

    /**
     * change file name to play another game from xml or json
     */
    static final String xmlFileName = "game_result1.xml";
    static final String jsonFileName = "game_result1.json";

    public static void main(String[] args) {

        /**
         * you can choose how to write the result of the game to the file
         * it can be json or xml format, please uncomment desired writer
         *
         * to start tictactoe game, uncomment this block and comment code block below
         */
        //GameDocumentWriter writer = new JsonGameWriter();
        GameDocumentWriter writer = new XmlGameWriter();
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playTicTacToe(writer);
        /**
         * to replay the game from xml file, please uncomment desired reader
         * please uncomment this block and comment code block above
         */
        //GameDocumentReader reader = new GameParserXml();
        //GameDocumentReader reader = new GameParserJson();
        //reader.readGameFile(xmlFileName);
    }
}
