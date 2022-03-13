package tictactoexml.gameplayxml;

import tictactoexml.tictactoe.TicTacToe;

public class PlayGameXml {

    static final String fileName = "game_result1.xml";

    public static void main(String[] args) {

        //starts tictactoe game, uncomment this block and comment code block below
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playTicTacToe();


        //starts the game from xml file, uncomment this block and comment code block above
        //StaxParserXml saxParserXml = new StaxParserXml();
        //saxParserXml.readXml(fileName);
    }
}
