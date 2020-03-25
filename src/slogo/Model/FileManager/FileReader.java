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

/**
 * FileReader class is used to read a config file in order to load up a specific configuration
 */
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

    /**
     * Getter method to get the config map
     * Config map maps a parameter to a value (ie language -> english)
     * @param file the specific config file to be loaded
     * @return the configuration map
     */
    public Map<String, String> getConfigMap(File file){
        setElement(file);
        constructMap();
        return configMap;
    }

    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodes.item(0);
        return node.getNodeValue();
    }

    private boolean setElement(File file) throws XMLException {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
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
            throw new XMLException("INVALID FILE: " + file.toString());
        }
    }

    /**
     * Retrieves an integer value given a specific parameter
     * @param parameter
     * @return
     */
    public int getIntValue(String parameter) {
        try {
            return Integer.parseInt(getValue(parameter, fileElement));
        } catch (NullPointerException e) {
            throw new XMLException(parameter);
        }
    }

    /**
     * Retrieves an double value given a specific parameter
     * @param parameter
     * @return
     */
    public double getDoubleValue(String parameter) {
        try {
            return Double.parseDouble(getValue(parameter, fileElement));
        } catch (NullPointerException e) {
            throw new XMLException(parameter);
        }
    }

    /**
     * Retrieves an String value given a specific parameter
     * @param parameter
     * @return
     */
    public String getString(String parameter) {
        try {
            return getValue(parameter, fileElement);
        } catch (NullPointerException e) {
            throw new XMLException(parameter);
        }
    }

}
