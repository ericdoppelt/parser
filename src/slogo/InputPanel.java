package slogo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ResourceBundle;


public class InputPanel {

    private static final Color BACKGROUND_COLOR = Color.CORAL;
    private static final Color PEN_COLOR = Color.PURPLE;
    private static final ObservableList allLanguages = FXCollections.observableArrayList("Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish", "Urdu");

    private static final String DEFAULT_LANGUAGE = "English";
    private ResourceBundle myResourceBundle  = ResourceBundle.getBundle(DEFAULT_LANGUAGE);

    private static final String ACCEPTABLE_FILE_EXTENSION = ".png";
    private final FileChooser FILE_CHOOSER = createFileChooser(ACCEPTABLE_FILE_EXTENSION);

    HBox allButtons;

    public InputPanel() {
        allButtons = new HBox();
        initButtons();
    }

    public Node getInputPanel() {
        return allButtons;
    }

    private void initButtons() {
        createColorPickers();
        createLanguageButton();
        createTurtleButton();

    }

    private void createLanguageButton() {
        VBox languageBox = new VBox();
        languageBox.setAlignment(Pos.CENTER);

        Label addedLabel = new Label(myResourceBundle.getString("setLanguage"));
        ComboBox languageButton = new ComboBox(allLanguages);

        languageBox.getChildren().addAll(addedLabel, languageButton);
        allButtons.getChildren().add(languageBox);
    }

    private void createTurtleButton() {
        VBox turtleBox = new VBox();
        turtleBox.setAlignment(Pos.CENTER);

        Label turtleLabel = new Label(myResourceBundle.getString("setTurtle"));
        Button turtleButton = new Button(myResourceBundle.getString("loadTurtleImage"));

        turtleBox.getChildren().addAll(turtleLabel, turtleButton);
        allButtons.getChildren().add(turtleBox);
    }

    private void createColorPickers() {
        allButtons.getChildren().add(createColorBox(myResourceBundle.getString("setBackground"), BACKGROUND_COLOR));
        allButtons.getChildren().add(createColorBox(myResourceBundle.getString("setPen"), PEN_COLOR));
    }

    private VBox createColorBox(String text, Color defaultColor) {
        VBox returnedBox = new VBox();
        returnedBox.setAlignment(Pos.CENTER);
        Label addedLabel = new Label(text);
        ColorPicker addedColorPicker = new ColorPicker(defaultColor);
        addedColorPicker.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        returnedBox.getChildren().addAll(addedLabel, addedColorPicker);
        return returnedBox;
    }

    private FileChooser createFileChooser(String extension) {
        FileChooser returnedChooser = new FileChooser();
        returnedChooser.setTitle(myResourceBundle.getString("turtleImageChooser"));
        returnedChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Image Files", extension));
        returnedChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        return returnedChooser;
    }
}
