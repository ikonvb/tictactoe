package com.konstantinbulygin.tictactoexml.parser;


import com.konstantinbulygin.tictactoexml.model.*;
import com.konstantinbulygin.tictactoexml.repository.GameDocumentReader;
import com.konstantinbulygin.tictactoexml.util.GameParser;

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
    private final GameResult gameResult = new GameResult();
    private final List<Step> steps = new ArrayList<>();
    private final Gameplay gameplay = new Gameplay();

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

        while (reader.hasNext()) {
            XMLEvent nextEvent = reader.nextEvent();
            if (nextEvent.isStartElement()) {
                StartElement startElement = nextEvent.asStartElement();
                switch (startElement.getName().getLocalPart()) {
                    case PLAYER:
                        String name = startElement.getAttributeByName(new QName(NAME)).getValue();
                        String id = startElement.getAttributeByName(new QName(ID)).getValue();
                        String symbol = startElement.getAttributeByName(new QName(SYMBOL)).getValue();
                        players.add(new Player(Integer.valueOf(id), name, symbol));

                        if (players.get(0).getPlayerName().equals(name)) {
                            gameResult.setPlayer(players.get(0));
                        } else if (players.get(1).getPlayerName().equals(name)) {
                            gameResult.setPlayer(players.get(1));
                        } else {
                            gameResult.setPlayer(null);
                        }
                        break;
                    case STEP:
                        nextEvent = reader.nextEvent();
                        String stepOrder = startElement.getAttributeByName(new QName(NUM)).getValue();
                        String playerId = startElement.getAttributeByName(new QName(PLAYER_ID)).getValue();
                        String stepCoordinate = nextEvent.asCharacters().getData();
                        steps.add(new Step(Integer.valueOf(playerId), stepOrder, stepCoordinate));
                        break;
                }
            }
        }

        Game game = new Game();
        game.setSteps(steps);
        gameplay.setGame(game);
        gameplay.setGameResult(gameResult);
        gameplay.setPlayers(players);
    }
}
