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
            configMap.put("language", "FRENCH");
            configMap.put("background", "0xff");
            configMap.put("pen", "0xAB");

<<<<<<< HEAD
        FileWriter test = new FileWriter();
        List<String> parameters = new ArrayList<>();
        parameters.add("image");
        parameters.add("language");
        parameters.add("background");
        parameters.add("pen");
        test.saveConfig(parameters, "TEST");

        FileReader testReader = new FileReader("TEST.xml");
        String parameter = testReader.getString("image");
        System.out.println(parameter);
=======
            test.saveConfig(configMap, "TestConfig");
>>>>>>> 54c94d5fae3cc002b3e65a3545705bf95f9eaacd

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
