package slogo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CommandBox class was created to develop the object in which users feed commands. There is
 */
public class CommandBox {
    public static final String COMMAND_PROMPT = "Enter a Command";
    public static final String RUN = "Run";
    public static final String CLEAR = "Clear";
    public static final String EXPAND = "Expand";
    public static final String MINIMIZE = "Minimize";
    public static final int COMMAND_WIDTH = 200;
    public static final int COMMAND_HEIGHT = 100;
    public static final int BUTTON_WIDTH = 60;

    private TextInputControl myCommandField;
    private TextArea extendedCommandField;
    private TextField shortCommandField;
    private Button runButton;
    private Button clearButton;
    private Button expandButton;
    private HBox myCommandLine;
    private VBox myCommandButtons;
    private ArrayList<String> myCommands;
    private String myCurrentCommand;
    private boolean commandLineIsExtended;

    public CommandBox(){
        shortCommandField = getCommandField();
        myCommandField = shortCommandField;
        runButton = getButton(RUN, event -> retrieveText());
        clearButton = getButton(CLEAR, event -> clearText());
        expandButton = getButton(EXPAND, event -> resizeCommandField());
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

    /**
     * Methods created to deal with button actions.
     */
    private void retrieveText(){
        // TODO add mechanism to deal with parser call\
        System.out.println("here");
        if(myCommands == null) myCommands = new ArrayList<>();
        myCurrentCommand = myCommandField.getText();
        myCommands.add(myCurrentCommand);
        myCommandField.clear();
        System.out.println(myCurrentCommand);
    }
    private void clearText(){
        myCommandField.clear();
    }
    private void resizeCommandField(){
        if(extendedCommandField == null) extendedCommandField = getExtendedCommandField();
        if(commandLineIsExtended){
            switchCommandLine(extendedCommandField, shortCommandField,EXPAND);
            commandLineIsExtended = false;
        }
        else {
            switchCommandLine(shortCommandField, extendedCommandField, MINIMIZE);
            commandLineIsExtended = true;
        }
    }

    private TextField getCommandField(){
        TextField myText = new TextField();
        myText.setPromptText(COMMAND_PROMPT);
        myText.setPrefWidth(COMMAND_WIDTH);
        return myText;
    }
    private TextArea getExtendedCommandField(){
        TextArea myText = new TextArea();
        myText.setPromptText(COMMAND_PROMPT);
        myText.setPrefWidth(COMMAND_WIDTH);
        myText.setPrefHeight(COMMAND_HEIGHT);
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
        myCommandLine.getChildren().addAll(myCommandField,runButton, clearButton, expandButton);
    }

    private void switchCommandLine(TextInputControl currentField, TextInputControl newField, String newLabel){
        myCommandLine.getChildren().remove(currentField);
        myCommandLine.getChildren().add(0, newField);
        myCommandField = newField;
        expandButton.setText(newLabel);
    }
}
