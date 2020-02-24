package slogo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ArrayList;

/**
 * Tester class
 * @author erikgregorio
 */
public class tester extends Application {
    @Override
    public void start(Stage primaryStage) {
        ModelParser modelParser = new ModelParser("English");
        BorderPane mypane = new BorderPane();

        Pane p = new Pane();
        TurtleView myTurtle = new TurtleView(modelParser.getMyTurtle(), p);
        CommandBox myCommandLine = new CommandBox(modelParser, myTurtle);

        mypane.setBottom(myCommandLine.getCommandLine());
        mypane.setCenter(p);

        Scene myScene = new Scene(mypane, 500,500);

        primaryStage.setScene(myScene);
        primaryStage.show();
        //primaryStage

        ObservableList<ArrayList<Double>> mylist = FXCollections.observableArrayList();

    }
}

