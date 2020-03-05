package slogo.Model.FileManager;

import java.util.ArrayList;
import java.util.List;

public class FileTester {
    public static void main(String args[]){

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

    }
}
