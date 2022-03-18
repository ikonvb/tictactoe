package tictactoexml.gameplayxml;

import tictactoexml.parser.JsonParser;
import tictactoexml.parser.StaxParserXml;
import tictactoexml.repository.GameDocumentReader;
import tictactoexml.repository.GameDocumentWriter;
import tictactoexml.tictactoe.TicTacToe;
import tictactoexml.writers.JsonGameWriter;

public class PlayGameXml {

    //change file name to play another game from xml
    static final String xmlFileName = "game_result1.xml";
    static final String jsonFileName = "game_result1.json";

    public static void main(String[] args) {
        //to start tictactoe game, uncomment this block and comment code block below
        //GameDocumentWriter writer = new JsonGameWriter();
        //GameDocumentWriter writer = new XmlGameWriter();
        //TicTacToe ticTacToe = new TicTacToe();
        //ticTacToe.playTicTacToe(writer);

        //to start game from xml file, uncomment this block and comment code block above
        //GameDocumentReader reader = new StaxParserXml();
        GameDocumentReader reader = new JsonParser();
        reader.readGameFile(jsonFileName);
    }
}
