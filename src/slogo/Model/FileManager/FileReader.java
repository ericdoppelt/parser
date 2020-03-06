package slogo.Model.FileManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    private Element fileElement;
    private Map configMap;
    private NodeList nodeList;

    public FileReader() throws XMLException{
        configMap = new HashMap<String, String>();
    }

    private void constructMap(){
        for (int i=1; i<nodeList.getLength(); i++)
        {
            // Get element
            Element element = (Element)nodeList.item(i);
            configMap.put(element.getNodeName(), getValue(element.getNodeName(), fileElement));
        }
    }

    public Map<String, String> getConfigMap(String fileName){
        setElement(fileName);
        constructMap();
        return configMap;
    }

    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodes.item(0);
        return node.getNodeValue();
    }

    public boolean setElement(String fileName) throws XMLException {

        try {
            File simulation = new File("Configs/" + fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(simulation);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName("configuration");
            nodeList = doc.getElementsByTagName("*");
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
            throw new XMLException(parameter);
        }
    }

    public double getDoubleValue(String parameter) {
        try {
            return Double.parseDouble(getValue(parameter, fileElement));
        } catch (NullPointerException e) {
            throw new XMLException(parameter);
        }
    }

    public String getString(String parameter) {
        try {
            return getValue(parameter, fileElement);
        } catch (NullPointerException e) {
            throw new XMLException(parameter);
        }
    }

}
