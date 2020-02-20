package slogo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class tester extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        CommandBox myCommandLine = new CommandBox();
        Group myGroup = new Group();
        myGroup.getChildren().add(myCommandLine.getCommandLine());
        Scene myScene = new Scene(myGroup, 500,500);

        primaryStage.setScene(myScene);
        primaryStage.show();
        //primaryStage
    }
}
