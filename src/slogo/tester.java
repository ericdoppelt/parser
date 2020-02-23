package slogo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import slogo.Model.ModelDatabase;
import slogo.Model.ModelParser;

import javax.swing.border.Border;
import java.awt.*;

/**
 * Tester class
 * @author erikgregorio
 */
public class tester extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ModelParser modelParser = new ModelParser("English");
        TurtleView myTurtle = new TurtleView(modelParser.getMyTurtle());
        CommandBox myCommandLine = new CommandBox(modelParser, myTurtle);

        BorderPane mypane = new BorderPane();

        //Pane myGroup = new Pane();
        //myGroup.setPrefSize(200,200);
        //myGroup.getChildren().addAll(myTurtle.getTurtle());

        mypane.setBottom(myCommandLine.getCommandLine());
        mypane.setCenter(new Pane(myTurtle.getTurtle()));


        Scene myScene = new Scene(mypane, 500,500);

        primaryStage.setScene(myScene);
        primaryStage.show();
        //primaryStage
    }
}

