package slogo.View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import slogo.Model.ModelParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * CommandBox class holds a node with all appropriate buttons and fields for a command line
 * @author erikgregorio
 */
public class CommandBox {
    public static final String COMMAND_PROMPT = "Enter a Command";
    public static final String RUN = "Run";
    public static final String CLEAR = "Clear";
    public static final String EXPAND = "Expand";
    public static final String MINIMIZE = "Minimize";
    public static final String WHITESPACE = "\\s+";
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

    private ModelParser myParser;
    private TurtleView turtle;
    /**
     * Initializes command line text field as well as the buttons to run, clear and expand
     * text field.
     */
    public CommandBox(ModelParser parser, TurtleView t){
        turtle = t;
        myParser = parser;
        shortCommandField = getCommandField();
        myCommandField = shortCommandField;
        stetUpButtons();
        setUpCommandLine();
        formatButtons();
    }

    /**
     * Returns command line node to be displayed
     */
    public Node getCommandLine() {
        return myCommandLine;
    }
    /**
     * Returns all user fed command instructions
     */
    public List<String> getAllCommands(){
        if(myCommands == null) return Collections.unmodifiableList(new ArrayList<>());
        return Collections.unmodifiableList(myCommands);
    }
    /**
     * Returns the most recent user written command
     */
    public String getCommand(){
        return myCurrentCommand;
    }
    /**
     * Allows the other programs to display errors with the most recent command given
     */
    public void displayError(String x){
        //myCommandField.setText(x);
    }

    /**
     * Methods created to deal with button actions.
     */
    private void retrieveText(){
        // TODO add mechanism to deal with parser call\
        if(myCommands == null) myCommands = new ArrayList<>();
        myCurrentCommand = myCommandField.getText();
        myCommands.add(myCurrentCommand);
        myCommandField.clear();
        myParser.parseText(Arrays.asList(myCurrentCommand.split(WHITESPACE)));
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

    /**
     * Methods that create both the shorten and extended versions of the command line
     */
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
    // Returns a button with a specified label, and action
    private Button getButton(String label, EventHandler<ActionEvent> event){
        Button myButton = new Button(label);
        myButton.setOnAction(event);
        myButton.setPrefWidth(BUTTON_WIDTH);
        return myButton;
    }
    // Places all buttons and textfields into a singular node
    private void setUpCommandLine(){
        myCommandLine = new HBox();
        myCommandLine.getChildren().addAll(myCommandField,runButton, clearButton, expandButton);
    }
    // Switches the visible command line to minimize or expand
    private void switchCommandLine(TextInputControl currentField, TextInputControl newField, String newLabel){
        myCommandLine.getChildren().remove(currentField);
        myCommandLine.getChildren().add(0, newField);
        myCommandField = newField;
        expandButton.setText(newLabel);
    }
    // Method to create all buttons
    private void stetUpButtons(){
        runButton = getButton(RUN, event -> retrieveText());
        clearButton = getButton(CLEAR, event -> clearText());
        expandButton = getButton(EXPAND, event -> resizeCommandField());
    }

    private void formatButtons() {
        for (Node button : myCommandLine.getChildren()) {
            myCommandLine.setHgrow(button, Priority.ALWAYS);
        }
    }
}
