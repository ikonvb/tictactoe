package tictactoexml.writers;

import tictactoexml.model.Player;
import tictactoexml.model.Step;
import tictactoexml.repository.GameDocumentWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static tictactoexml.util.GameUtil.*;
import static tictactoexml.util.GameUtil.XML_EXTENSION;

public class XmlGameWriter implements GameDocumentWriter {

    @Override
    public void writeGameResult(List<Player> players, List<Step> steps, int gameTry, Player winner) {

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter writer = outputFactory.createXMLStreamWriter(out);

            writer.writeStartDocument(ENCODING_XML, VERSION);
            writer.writeStartElement(GAME_PLAY);

            for (Player player : players) {
                writer.writeStartElement(PLAYER);
                writer.writeAttribute(ID, String.valueOf(player.getId()));
                writer.writeAttribute(NAME, String.valueOf(player.getName()));
                writer.writeAttribute(SYMBOL, String.valueOf(player.getSymbol()));
                writer.writeEndElement();
            }

            writer.writeStartElement(GAME);
            for (Step step : steps) {
                writer.writeStartElement(STEP);
                writer.writeAttribute(NUM, step.getNum());
                writer.writeAttribute(PLAYER_ID, step.getPlayerId());
                writer.writeCharacters(step.getStep());
                writer.writeEndElement();
            }
            writer.writeEndElement();

            writer.writeStartElement(GAME_RESULT);
            if (winner == null) {
                writer.writeStartElement(DRAW);
                writer.writeCharacters(STRING_DRAW);
                writer.writeEndElement();
            } else {
                for (Player player : players) {
                    if (player == winner) {
                        writer.writeStartElement(PLAYER);
                        writer.writeAttribute(ID, String.valueOf(player.getId()));
                        writer.writeAttribute(NAME, String.valueOf(player.getName()));
                        writer.writeAttribute(SYMBOL, String.valueOf(player.getSymbol()));
                        writer.writeEndElement();
                    }
                }
            }
            writer.writeEndElement();
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();

            String xml = out.toString(StandardCharsets.UTF_8);

            String prettyPrintXML = formatXML(xml);
            //save the game_result file in the root of project
            Files.writeString(Paths.get(FILE_NAME + gameTry + XML_EXTENSION), prettyPrintXML, StandardCharsets.UTF_8);

        } catch (IOException | XMLStreamException | TransformerException e) {
            e.printStackTrace();
        }

    }

    private static String formatXML(String xml) throws TransformerException {

        // write data to xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print by indention
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // add standalone="yes", add line break before the root element
        transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");

        StreamSource source = new StreamSource(new StringReader(xml));
        StringWriter output = new StringWriter();
        transformer.transform(source, new StreamResult(output));

        return output.toString();

    }
}
