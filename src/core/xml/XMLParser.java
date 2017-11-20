package core.xml;

import core.exceptions.InvalidXMLException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Lolita
 * Nov 20 2017
 * Parses XML files.
 */

public class XMLParser {

    public static Document parse(File xml) throws InvalidXMLException, ParserConfigurationException, SAXException, IOException {
        if (isXML(xml.getName())) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document file = builder.parse(xml);
            factory = null;
            builder = null;
            return file;
        } else {
            throw new InvalidXMLException("XML file convention is invalid.");
        }
    }

    public static boolean isXML(String file) { return file.substring((file.lastIndexOf(".") + 1)).equals("xml"); }

}