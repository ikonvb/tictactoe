package com.konstantinbulygin.tictactoexml.parser;


import com.konstantinbulygin.tictactoexml.model.*;
import com.konstantinbulygin.tictactoexml.service.GameDocumentReader;
import com.konstantinbulygin.tictactoexml.service.GameParser;

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

import static com.konstantinbulygin.tictactoexml.util.GameUtil.*;


public class GameParserXml extends GameParser implements GameDocumentReader {

    private final List<Player> players = new ArrayList<>();
    private final Player player1 = new Player();
    private final Player player2 = new Player();
    private final GameResult gameResult = new GameResult();
    private final List<Step> steps = new ArrayList<>();
    private Gameplay gameplay;

    @Override
    public void readGameFile(String fileName) {
        try {
            tryToReadXml(fileName);
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        showGameFirstState(gameplay);
        showGameSteps(gameplay);
        showGameResult(gameplay);
    }

    private void tryToReadXml(String fileName) throws XMLStreamException, FileNotFoundException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
        gameplay = new Gameplay();
        Game game = new Game();

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

        game.setSteps(steps);
        gameplay.setGame(game);
        gameplay.setGameResult(gameResult);
        gameplay.setPlayers(players);
    }
}
