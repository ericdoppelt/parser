package slogo.Model.FileManager;

import java.io.File;

public class FileWriter {
    private String fileName;

    public FileWriter(){
    }

    public File createFile(String fileName){
        return new File(fileName+".xml");
    }

}
