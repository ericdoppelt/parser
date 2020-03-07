package slogo.View.Info;

import javafx.beans.property.MapProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleMapProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class VariableInfoView  {

    private static final String COMMAND_TEXT_DEFAULT = "Enter Value";
    private static final String COMMAND_TEXT_HEADER = "Specify a New Value for the Variable";
    private static final String VARIABLE_SETUP = "make :";
    private static final String WHITESPACE = "\\s+";
    private static final String COLON_REGEX = ":";
    private static final String SPACE = " ";
    private static final int VARIABLE_NAME_INDEX = 1;

    private MapProperty<String, Integer> myVariables;
    private Consumer<List<String>> myParserCommand;
    private VBox displayedInfo;

    public VariableInfoView(Consumer<List<String>> parser) {
        displayedInfo = new VBox();
        myParserCommand = parser;

        myVariables = new SimpleMapProperty<>();
        myVariables.addListener(((observable, oldValue, newValue) -> {
            displayedInfo.getChildren().clear();
            for (String s : newValue.keySet()) {
                Label addedLabel = new Label(s.substring(1) + ": " + newValue.get(s));
                addedLabel.setOnMouseClicked(e -> updateVariable(s));
                displayedInfo.getChildren().add(addedLabel);
            }
        }));
    }

    public VBox getInfoVBox() {
        return displayedInfo;
    }

    public Property getBindableInfo() {
        return myVariables;
    }

    private void updateVariable(String varInfo) {
        TextInputDialog inputVar = new TextInputDialog(COMMAND_TEXT_DEFAULT);
        inputVar.setHeaderText(COMMAND_TEXT_HEADER);
        inputVar.showAndWait();

        int newVariableValue;
        try {
            newVariableValue = Integer.parseInt(inputVar.getEditor().getText());
        } catch (Exception e) {
            System.out.println("Error in updateVariable");
            return;
        }

        String varCommand = formatVariableCommand(varInfo, newVariableValue);
        System.out.println(varCommand);
        passCommand(varCommand);
    }

    private void passCommand(String command) {
        myParserCommand.accept(Arrays.asList(command.split(WHITESPACE)));
    }

    private String formatVariableCommand(String variableInfo, int newValue) {
        System.out.println(variableInfo);
        String variableName = variableInfo.split(COLON_REGEX)[VARIABLE_NAME_INDEX];
        return VARIABLE_SETUP + variableName + SPACE + newValue;
    }
}
