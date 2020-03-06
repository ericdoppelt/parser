package slogo.View;

import javafx.beans.property.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class InfoView {

    private MapProperty<String, Integer> myVariables;
    private ListProperty<String> myHistory;
    private MapProperty<String, String> myCommands;

    // TODO: duplicated with space below?
    private static final String WHITESPACE = "\\s+";

    private VBox myInfoPanel;

    private ToggleGroup myToggleGroup = new ToggleGroup();
    private HBox myButtonPanel;
    private ToggleButton myHistoryToggle;
    private ToggleButton myCommandToggle;
    private ToggleButton myVariableToggle;

    private final String HELP_IMAGE_PATH = "infoGraphic.png";
    private Button myHelpButton;

    private ScrollPane myScrollPane;

    private final String RESOURCE_LANGUAGE = "English";
    private final ResourceBundle myBundle = ResourceBundle.getBundle(RESOURCE_LANGUAGE);

    private Consumer<List<String>> myParserCommand;

    private static final String COMMAND_TEXT_DEFAULT = "Enter Value";
    private static final String COMMAND_TEXT_HEADER = "Specify a New Value for the Variable";

    private static final String COLON_REGEX = ":";
    private static final int VARIABLE_NAME_INDEX = 1;
    private static final String VARIABLE_SETUP = "make :";
    private static final String SPACE = " ";

    private Property<String> myLanguage;

    private static final String NUMBER_REGEX = "^-?\\d+\\.\\d+$";
    private static final int LENGTH_REMOVED_FROM_TRANSLATION = 1;
    private static final String TRANSLATION_SPLITTER = "\\|";
    private static final int DISPLAYED_TRANSLATION_INDEX = 0;

    public InfoView(Consumer<List<String>> parserCommand, Property<String> language) {
        myParserCommand = parserCommand;
        myLanguage = new SimpleObjectProperty<String>();
        myLanguage.bind(language);
        myLanguage.addListener((observable, oldValue, newValue) -> updateLanguage());

        initInfoPanel();
        initProperties();
        initButtons();

        setToggledInfo();
        setButtonActions();
    }

    public MapProperty getVariableProperty() {
        return myVariables;
    }

    public ListProperty getHistoryProperty() {
        return myHistory;
    }

    public MapProperty getCommandProperty() {
        return myCommands;
    }

    private void initInfoPanel() {
        myButtonPanel = new HBox();
        myScrollPane = new ScrollPane();
        myScrollPane.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));

        myInfoPanel = new VBox();
        myInfoPanel.getChildren().addAll(myButtonPanel, myScrollPane);
        myInfoPanel.setVgrow(myScrollPane, Priority.ALWAYS);
    }

    //TODO: REMOVE DUPLICATION
    private void initProperties() {
        myVariables = new SimpleMapProperty<>();
        myVariables.addListener(((observable, oldValue, newValue) -> {
            ((VBox) myVariableToggle.getUserData()).getChildren().clear();
            for (String s : newValue.keySet()) {
                Label addedLabel = new Label(s.substring(1) + ": " + newValue.get(s));
                addedLabel.setOnMouseClicked(e -> updateVariable(s));
                ((VBox) myVariableToggle.getUserData()).getChildren().add(addedLabel);

            }
        }));

        myCommands = new SimpleMapProperty<>();
        myCommands.addListener((observable, oldValue, newValue) -> {
            ((VBox) myCommandToggle.getUserData()).getChildren().clear();
            for (String s : newValue.keySet())
                ((VBox) myCommandToggle.getUserData()).getChildren().add(new Label(s.substring(1) + newValue.get(s)));
        });

        myHistory = new SimpleListProperty<>();
        myHistory.addListener(((observable, oldValue, newValue) -> {
            if (newValue.size() > 0) {
                Label addedLabel = new Label(newValue.get(newValue.size() - 1));
                addedLabel.setOnMouseClicked(e -> passCommand(addedLabel.getText()));
                ((VBox) myHistoryToggle.getUserData()).getChildren().add(addedLabel);
            }
        }));
    }

    private void passCommand(String command) {
        myParserCommand.accept(Arrays.asList(command.split(WHITESPACE)));
    }

    private void updateVariable(String varInfo) {
        // TODO: THIS COULD BE A CLASS/METHOD
        TextInputDialog inputVar = new TextInputDialog(COMMAND_TEXT_DEFAULT);
        inputVar.setHeaderText(COMMAND_TEXT_HEADER);
        inputVar.showAndWait();

        int newVariableValue;
        try {
            newVariableValue = Integer.parseInt(inputVar.getEditor().getText());
            //TODO: exception
        } catch (Exception e) {
            System.out.println("Error in updateVariable");
            return;
        }

        String varCommand = formatVariableCommand(varInfo, newVariableValue);
        System.out.println(varCommand);
        passCommand(varCommand);
    }

    private String formatVariableCommand(String variableInfo, int newValue) {
        System.out.println(variableInfo);
        String variableName = variableInfo.split(COLON_REGEX)[VARIABLE_NAME_INDEX];
        return VARIABLE_SETUP + variableName + SPACE + newValue;
    }

    private void initButtons() {
        myHistoryToggle = createToggleButton(myBundle.getString("historyHeader"));
        myCommandToggle = createToggleButton(myBundle.getString("commandHeader"));
        myVariableToggle = createToggleButton(myBundle.getString("variableHeader"));
        myHelpButton = createHelpButton();
    }

    private ToggleButton createToggleButton(String s) {
        ToggleButton tempToggle = new ToggleButton(s);
        tempToggle.setToggleGroup(myToggleGroup);
        myButtonPanel.getChildren().add(tempToggle);
        return tempToggle;
    }

    private Button createHelpButton() {
        Button tempButton = new Button();
        try {
            Image helpImage = new Image(this.getClass().getClassLoader().getResourceAsStream(HELP_IMAGE_PATH), 0, 25, true, false);
            tempButton.setGraphic(new ImageView(helpImage));
            //TODO: fill this in
        } catch (Exception e) {
        }
        tempButton.setPadding(new Insets(0));
        myButtonPanel.getChildren().add(tempButton);
        return tempButton;
    }

    private void setToggledInfo() {
        setHistoryInfo();
        setCommandInfo();
        setVariableInfo();
    }

    private void setHistoryInfo() {
        VBox historyToggleInfo = new VBox();
        myHistoryToggle.setUserData(historyToggleInfo);
    }

    private void setVariableInfo() {
        VBox variableToggleInfo = new VBox();
        myVariableToggle.setUserData(variableToggleInfo);
    }

    private void setCommandInfo() {
        VBox commandToggleInfo = new VBox();
        myCommandToggle.setUserData(commandToggleInfo);
    }

    private void setButtonActions() {
        for (Toggle tempToggle : myToggleGroup.getToggles()) {
            ToggleButton tempToggleButton = (ToggleButton) tempToggle;
            tempToggleButton.setOnAction(event -> myScrollPane.setContent((Node) tempToggleButton.getUserData()));
        }
        //TODO: helpButton exceptions
        myHelpButton.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URL("https://www2.cs.duke.edu/courses/compsci308/current/assign/03_parser/commands.php").toURI());
            } catch (IOException | URISyntaxException e) {
                System.out.println("Bad Error!");
            }
        });
    }

    public VBox getCompletePanel() {
        return myInfoPanel;
    }

    private void updateLanguage() {
        updateHistoryLanguage();
    }

    private void updateHistoryLanguage() {
        ((VBox)myHistoryToggle.getUserData()).getChildren().clear();

        ResourceBundle tempLanguageBundle = ResourceBundle.getBundle(myLanguage.getValue());
        for (String command : myHistory.getValue()) {
            StringBuilder translatedCommand = new StringBuilder();
            for (String commandBlock : command.split(WHITESPACE)) {
                if (commandBlock.matches(NUMBER_REGEX)) translatedCommand.append(commandBlock + SPACE);
                else {
                    String fullTranslation = tempLanguageBundle.getString(commandBlock);
                    String displayedTranslation = fullTranslation.split(TRANSLATION_SPLITTER)[DISPLAYED_TRANSLATION_INDEX];
                    translatedCommand.append(displayedTranslation + SPACE);
                }
            }
            translatedCommand.deleteCharAt(translatedCommand.length() - LENGTH_REMOVED_FROM_TRANSLATION);
            // TODO: DUPLICATED CODE
            Label addedLabel = new Label(translatedCommand.toString());
            addedLabel.setOnMouseClicked(e -> passCommand(addedLabel.getText()));
            ((VBox)myHistoryToggle.getUserData()).getChildren().add(addedLabel);
        }
    }
}
