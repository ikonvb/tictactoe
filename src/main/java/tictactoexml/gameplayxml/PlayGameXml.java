package tictactoexml.gameplayxml;

import tictactoexml.parser.StaxParserXml;
import tictactoexml.tictactoe.TicTacToe;

public class PlayGameXml {

    //change file name to play another game from xml
    static final String fileName = "game_result1.xml";

    public static void main(String[] args) {
        //to start tictactoe game, uncomment this block and comment code block below
//        TicTacToe ticTacToe = new TicTacToe();
//        ticTacToe.playTicTacToe();

        //to start game from xml file, uncomment this block and comment code block above
        StaxParserXml saxParserXml = new StaxParserXml();
        saxParserXml.readXml(fileName);
    }
}
