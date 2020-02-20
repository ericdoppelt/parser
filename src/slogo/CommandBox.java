package slogo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandBox {
    public static final String COMMAND_PROMPT = "Enter a Command";
    public static final String RUN = "Run";
    public static final String CLEAR = "Clear";
    public static final String EXPAND = "EXPAND";
    public static final int COMMAND_WIDTH = 200;
    public static final int BUTTON_WIDTH = 50;

    TextField myCommandField;
    TextField ExtendedCommandField;
    Button runButton;
    Button clearButton;
    Button expandButton;
    HBox myCommandLine;
    VBox myCommandButtons;
    ArrayList<String> myCommands;
    String myCurrentCommand;

    public CommandBox(){
        myCommandField = getCommandField();
        runButton = getButton(RUN, event -> retrieveText());
        clearButton = getButton(CLEAR, event -> clearText());
        setUpCommandLine();
    }

    public Node getCommandLine() {
        return myCommandLine;
    }

    public List<String> getAllCommands(){
        if(myCommands == null) return Collections.unmodifiableList(new ArrayList<>());
        return Collections.unmodifiableList(myCommands);
    }

    public String getCommand(){
        return myCurrentCommand;
    }

    public void displayError(String x){
        //myCommandField.setText(x);
    }

    private void retrieveText(){
        if(myCommands == null) myCommands = new ArrayList<>();
        myCurrentCommand = myCommandField.getText();
        myCommands.add(myCurrentCommand);
        System.out.println(myCurrentCommand);
    }

    private void clearText(){
        myCommandField.clear();
    }

    private TextField getCommandField(){
        TextField myText = new TextField();
        myText.setPromptText(COMMAND_PROMPT);
        myText.setPrefWidth(COMMAND_WIDTH);
        return myText;
    }
    private Button getButton(String label, EventHandler<ActionEvent> event){
        Button myButton = new Button(label);
        myButton.setOnAction(event);
        myButton.setPrefWidth(BUTTON_WIDTH);
        return myButton;
    }

    private void setUpCommandLine(){
        myCommandLine = new HBox();
        myCommandButtons = new VBox();
        myCommandButtons.getChildren().addAll(runButton, clearButton);
        myCommandLine.getChildren().addAll(myCommandField,myCommandButtons);
    }

}
