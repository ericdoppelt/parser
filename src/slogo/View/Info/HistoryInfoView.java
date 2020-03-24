package slogo.View.Info;

import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class HistoryInfoView {

    private static final String WHITESPACE = "\\s+";
    private static final String TRANSLATION_SPLITTER = "\\|";
    private static final int DISPLAYED_TRANSLATION_INDEX = 0;
    private static final String SPACE = " ";

    private static final String NUMBER_REGEX = "^-?\\d+\\.\\d+$";
    private ResourceBundle myLanguageBundle;
    private Property<String> myLanguage;
    private Consumer<List<String>> myParserCommand;

    private ListProperty<String> myHistory;

    private VBox displayedHistory;

    public HistoryInfoView(Consumer<List<String>> parser, Property<String> currentLanguage) {
        displayedHistory = new VBox();
        myParserCommand = parser;

        myLanguageBundle = ResourceBundle.getBundle(currentLanguage.getValue());
        myLanguage = new SimpleObjectProperty<String>();
        myLanguage.bind(currentLanguage);
        myLanguage.addListener((observable, oldValue, newValue) -> updateLanguage(newValue));

        myHistory = new SimpleListProperty<>();
        myHistory.addListener(((observable, oldValue, newValue) -> {
            if (newValue.size() > 0) {
                String newCommand = newValue.get(newValue.size() - 1);
                Label addedLabel = new Label(changeCommandLanguage(newCommand));
                addedLabel.setOnMouseClicked(e -> passCommand(addedLabel.getText()));
                displayedHistory.getChildren().add(addedLabel);
            }
        }));
    }

    public VBox getInfoVBox() {
        return displayedHistory;
    }

    public Property getHistoryProperty() {
        return myHistory;
    }

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

    private void updateLanguage(String newLanguage) {
        myLanguageBundle = ResourceBundle.getBundle(newLanguage);
        displayedHistory.getChildren().clear();
        for (String command : myHistory.getValue()) {
            Label addedLabel = new Label(changeCommandLanguage(command));
            addedLabel.setOnMouseClicked(e -> passCommand(addedLabel.getText()));
            displayedHistory.getChildren().add(addedLabel);
        }
    }
}
