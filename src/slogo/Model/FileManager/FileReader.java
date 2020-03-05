package slogo.Model.FileManager;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    private Element fileElement;
    private Map configMap;

    public FileReader(String fileName) throws XMLException{
        setElement(fileName);
        configMap = new HashMap<String, String>();
    }

    private void constructMap(){

    }


    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodes.item(0);
        return node.getNodeValue();
    }

    public boolean setElement(String fileName) throws XMLException {

        try {
            File simulation = new File("XMLs/" + fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(simulation);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("configuration");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    fileElement = (Element) node;
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            throw new XMLException("INVALID FILE: " + fileName, fileName);
        }
    }

    public int getIntValue(String parameter) {
        try {
            return Integer.parseInt(getValue(parameter, fileElement));
        } catch (NullPointerException e) {
            popUp(parameter);
            //throw new parameterException(errorMessage, parameter);
            return 0;
        }
    }

    public double getDoubleValue(String parameter) {
        try {
            return Double.parseDouble(getValue(parameter, fileElement));
        } catch (NullPointerException e) {
            popUp(parameter);
            //throw new parameterException(errorMessage, parameter);
            return 0;
        }
    }

    public String getString(String parameter) {
        try {
            return getValue(parameter, fileElement);
        } catch (NullPointerException e) {
            popUp(parameter);
            //throw new parameterException(errorMessage, parameter);
            return null;
        }
    }

    private void popUp(String parameter) {
        String errorMessage = parameter + " parameter is invalid";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Parameter Error");
        alert.setHeaderText(errorMessage);
        Platform.runLater(alert::showAndWait);
    }
}
