package tictactoexml.parser;

import tictactoexml.model.GameResult;
import tictactoexml.model.Player;
import tictactoexml.model.Step;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static tictactoexml.tictactoe.TicTacToe.checkPlayerTurn;
import static tictactoexml.tictactoe.TicTacToe.showGameBoard;
import static tictactoexml.util.GameUtil.*;

public class StaxParserXml {

    private final String[][] ticTacToe = initGame();
    private final List<Player> players = new ArrayList<>();
    private final Player player1 = new Player();
    private final Player player2 = new Player();
    private final GameResult gameResult = new GameResult();
    private final List<Step> steps = new ArrayList<>();

    public void readXml(String fileName) {
        try {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));

            while (reader.hasNext()) {
                XMLEvent nextEvent = reader.nextEvent();
                if (nextEvent.isStartElement()) {
                    StartElement startElement = nextEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case PLAYER:
                            String name = startElement.getAttributeByName(new QName(NAME)).getValue();
                            String id = startElement.getAttributeByName(new QName(ID)).getValue();
                            String symbol = startElement.getAttributeByName(new QName(SYMBOL)).getValue();
                            if (player1.getId() == 0 && player1.getName() == null) {
                                player1.setId(Integer.parseInt(id));
                                player1.setName(name);
                                player1.setSymbol(symbol);
                                players.add(player1);
                            } else if (player2.getId() == 0 && player2.getName() == null) {
                                player2.setId(Integer.parseInt(id));
                                player2.setName(name);
                                player2.setSymbol(symbol);
                                players.add(player2);
                            } else if (player1.getName().equals(name)) {
                                gameResult.setPlayer(player1);
                            } else if (player2.getName().equals(name)) {
                                gameResult.setPlayer(player2);
                            } else {
                                gameResult.setPlayer(null);
                            }
                            break;
                        case STEP:
                            nextEvent = reader.nextEvent();
                            String num = startElement.getAttributeByName(new QName(NUM)).getValue();
                            String playerId = startElement.getAttributeByName(new QName(PLAYER_ID)).getValue();
                            String slot = nextEvent.asCharacters().getData();
                            steps.add(new Step(num, playerId, slot));
                            break;
                    }
                }
            }
            for (Player p : players) {
                System.out.println("Player name: " + p.getName() + " symbol is " + p.getSymbol());
            }
            System.out.println();
            showGameBoard(ticTacToe);

            for (Step step : steps) {
                System.out.println();
                if (players.get(0).getId() == Integer.parseInt(step.getPlayerId())) {
                    System.out.println("step № " + step.getNum() + " " + players.get(0).getName() + " goes to " + step.getStep());
                    checkPlayerTurn(players.get(0), step.getStep(), ticTacToe);
                    showGameBoard(ticTacToe);
                    Thread.sleep(700);
                }

                if (players.get(1).getId() == Integer.parseInt(step.getPlayerId())) {
                    System.out.println("step № " + step.getNum() + " " + players.get(1).getName() + " goes to " + step.getStep());
                    checkPlayerTurn(players.get(1), step.getStep(), ticTacToe);
                    showGameBoard(ticTacToe);
                    Thread.sleep(700);
                }
            }
            System.out.println();
            if (gameResult.getPlayer() != null) {
                System.out.println(STRING_WINNER + gameResult.getPlayer().getName() + " as " + gameResult.getPlayer().getSymbol());
            } else {
                System.out.println(STRING_DRAW);
            }


        } catch (FileNotFoundException | XMLStreamException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String[][] initGame() {

        return new String[][]{
                {"1", "2", "3"},
                {"4", "5", "6"},
                {"7", "8", "9"}
        };
    }

}
