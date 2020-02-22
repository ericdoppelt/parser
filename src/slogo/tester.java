package slogo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slogo.Model.ModelDatabase;
import slogo.Model.ModelParser;

public class tester extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ModelParser modelParser = new ModelParser("English");
        CommandBox myCommandLine = new CommandBox(modelParser);
        Group myGroup = new Group();
        myGroup.getChildren().add(myCommandLine.getCommandLine());
        Scene myScene = new Scene(myGroup, 500,500);

        primaryStage.setScene(myScene);
        primaryStage.show();
        //primaryStage
    }
}

