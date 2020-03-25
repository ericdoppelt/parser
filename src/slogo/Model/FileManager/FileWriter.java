package slogo.Model.FileManager;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Map;

/**
 * File writer takes the current configuration and saves the config as an xml file
 */

public class FileWriter{

    public FileWriter(){ }

    /**
     * Creates an xml configuration file with the configMap named configName
     * ConfigMap contains names of parameters mapped to string values (ie langauge -> Italian)
     * @param configMap map containing parameter and value information to be saved
     * @param configName name of the configuration
     */
    public void saveConfig(Map<String, String> configMap, String configName){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("configuration");
            doc.appendChild(rootElement);

            for (String parameter : configMap.keySet()) {
                Element paramElement = doc.createElement(parameter);
                paramElement.appendChild(doc.createTextNode(configMap.get(parameter)));
                rootElement.appendChild(paramElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("./Configs/"+configName+".xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            throw new XMLException("INVALID FILE: " + configName, configName);
        }
    }

}
