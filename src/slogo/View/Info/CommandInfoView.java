package slogo.View.Info;

import javafx.beans.property.MapProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import slogo.Model.Commands.Command;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class CommandInfoView {

    private Property myInfo;
    private Property<String> myLanguage;
    private Consumer<List<String>> myParserCommand;
    private ResourceBundle myLanguageBundle;

    private static final String WHITESPACE = "\\s+";
    private static final String TRANSLATION_SPLITTER = "\\|";
    private static final int DISPLAYED_TRANSLATION_INDEX = 0;
    private static final String SPACE = " ";
    private static final String NUMBER_REGEX = "^-?\\d+\\.\\d+$";

    private VBox displayedInfo;

    public CommandInfoView(Consumer<List<String>> parser, Property<String> currentLanguage) {
        displayedInfo = new VBox();
        myInfo = new SimpleMapProperty();
        myLanguage = currentLanguage;
        myParserCommand = parser;

        myLanguageBundle = ResourceBundle.getBundle(currentLanguage.getValue());
        myLanguage = new SimpleObjectProperty<String>();
        myLanguage.bind(currentLanguage);
        myLanguage.addListener((observable, oldValue, newValue) -> updateLanguage(newValue));
    }

    public Property getInfoProperty() {
        return myInfo;
    }
    public VBox getInfoVBox() {
        return displayedInfo;
    }

    private void updateLanguage(String newLanguage) {}

    private String changeCommandLanguage(String oldLanguage) {
        StringBuilder translatedCommand = new StringBuilder();
        for (String commandBlock : oldLanguage.split(WHITESPACE)) {
            if (commandBlock.matches(NUMBER_REGEX)) translatedCommand.append(commandBlock + SPACE);
            else {
                String fullTranslation = myLanguageBundle.getString(commandBlock);
                String displayedTranslation = fullTranslation.split(TRANSLATION_SPLITTER)[DISPLAYED_TRANSLATION_INDEX];
                translatedCommand.append(displayedTranslation + SPACE);
            }
        }
        return translatedCommand.toString();
    }

    private void passCommand(String command) {
        myParserCommand.accept(Arrays.asList(command.split(WHITESPACE)));
    }

}

//myCommands.addListener((observable, oldValue, newValue) -> {
//        ((VBox) myCommandToggle.getUserData()).getChildren().clear();
//        for (String commandName : newValue.keySet()) {
//        String translatedCommand = changeCommandLanguage(commandName);
//        Label addedLabel = new Label(translatedCommand + COLON_REGEX + SPACE + newValue.get(translatedCommand));
//        addedLabel.setOnMouseClicked(e -> passCommand(translatedCommand));
//        ((VBox) myCommandToggle.getUserData()).getChildren().add(addedLabel);
//        }
//        });


