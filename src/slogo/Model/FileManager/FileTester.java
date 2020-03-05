package slogo.Model.FileManager;

import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileTester {
    public static void main(String args[]){
        try {
            FileWriter test = new FileWriter();
            Map<String, String> configMap = new HashMap<>();
            configMap.put("image", "DUVALL");
            configMap.put("language", "GERMAN");
            configMap.put("background", "0xff");
            configMap.put("pen", "0xAB");
            test.saveConfig(configMap, "TestConfig");

            FileReader testReader = new FileReader("TestConfig.xml");
            testReader.getConfigMap();
        } catch(XMLException e){
            popUp();
        }
    }
    private static void popUp(){
        String errorMessage = "File is invalid";
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("XML Error");
        alert.setHeaderText(errorMessage);
        Platform.runLater(alert::showAndWait);
    }
}
