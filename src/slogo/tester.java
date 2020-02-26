package slogo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import slogo.Model.ModelParser;
import slogo.View.CommandBox;
import slogo.View.InfoPanel;
import slogo.View.InputPanel;
import slogo.View.TurtleView;

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
        InputPanel myButtons = new InputPanel();
        BorderPane mypane = new BorderPane();
        InfoPanel myInfo = new InfoPanel();

        //Pane myGroup = new Pane();
        //myGroup.setPrefSize(200,200);
        //myGroup.getChildren().addAll(myTurtle.getTurtle());
        VBox commandAndInput = new VBox();
        commandAndInput.getChildren().addAll(myButtons.getInputPanel(), myCommandLine.getCommandLine());

        mypane.setBottom(commandAndInput);
        mypane.setCenter(myTurtle.getTurtle());
        mypane.setRight(myInfo.getInfoPanel());

        Scene myScene = new Scene(mypane, 600,600);

        primaryStage.setScene(myScene);
        primaryStage.show();
        //primaryStage
    }
}

