package slogo.View.Input;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ComboBoxInputs extends Inputs {

    private static final List<String> ALL_COMBO_BOXES = new ArrayList<>(Arrays.asList("language"));

    private static final String COMBOBOXES = "comboBoxes";

    private ResourceBundle myComboBoxesBundle  = ResourceBundle.getBundle(COMBOBOXES);

    private ObservableList allLanguages = initLanguageOptions();
    private static final String PATH_TO_RESOURCE_LANGUAGES = "././././resources/languages";
    private static final int LENGTH_OF_FILE_ENDING = 11;
    private static final String LANGUAGE_KEY = "language";

    private static final String PROPERTIES_REGEX_SPLITTER = ", ";
    private static final int VBOX_LABEL_INDEX = 0;

    private ComboBox myLanguageBox;

    private HBox myComboBoxes;

    public ComboBoxInputs() {
        myComboBoxes = new HBox();
        for (String comboBoxType : ALL_COMBO_BOXES) makeComboVBox(comboBoxType);
        myComboBoxes = formatButtons(myComboBoxes);
    }

    public HBox getComboBoxesHBox() {
        return myComboBoxes;
    }

    public Property<String> getLanguageProperty() {
        return myLanguageBox.valueProperty();
    }

    private void makeComboVBox(String comboBoxType) {
        VBox addedVBox = new VBox();
        addedVBox.setAlignment(Pos.CENTER);

        String[] ComboBoxInfo  = myComboBoxesBundle.getString(comboBoxType).split(PROPERTIES_REGEX_SPLITTER);

        Label addedLabel = new Label(ComboBoxInfo[VBOX_LABEL_INDEX]);
        ComboBox addedComboBox = new ComboBox(allLanguages);

        if (comboBoxType.equals(LANGUAGE_KEY)) myLanguageBox = addedComboBox;
        myLanguageBox.setValue("English");

        addedVBox.getChildren().addAll(addedLabel, addedComboBox);
        myComboBoxes.getChildren().add(addedVBox);
    }

    private ObservableList initLanguageOptions() {
        //TODO: exception here
        File languageDirectory = new File(PATH_TO_RESOURCE_LANGUAGES);
        List languageNames = new ArrayList<String>();
        for (File tempFile : languageDirectory.listFiles()) {
            String fileName = tempFile.getName();
            fileName = fileName.substring(0, fileName.length() - LENGTH_OF_FILE_ENDING);
            languageNames.add(fileName);
        }
        return FXCollections.observableArrayList(languageNames);
    }
}
