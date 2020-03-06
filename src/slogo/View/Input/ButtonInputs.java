package slogo.View.Input;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.Model.FileManager.FileReader;
import slogo.Model.FileManager.FileWriter;
import slogo.View.SlogoView;

import java.io.File;
import java.util.*;

public class ButtonInputs extends Inputs {

    private HBox myButtons;

    private static final String BUTTONS_BUNDLE = "buttons";
    private ResourceBundle myButtonsBundle = ResourceBundle.getBundle(BUTTONS_BUNDLE);
    private static final List<String> ALL_BUTTONS = new ArrayList<>(Arrays.asList("turtle", "preferences", "save", "workspace"));

    private static final String TURTLE_KEY = "turtle";
    private static final String ACCEPTABLE_TURTLE_FILE = "*.png";
    private static final String TURTLE_FILE_EXTENSION = ".png";
    private static final String TYPE_OF_TURTLE_FILE = "PNG";
    private static final String INITIAL_TURTLE_DIRECTORY = "user.dir";
    private final FileChooser TURTLE_FILE_CHOOSER = createFileChooser(ACCEPTABLE_TURTLE_FILE, TYPE_OF_TURTLE_FILE, INITIAL_TURTLE_DIRECTORY);
    private static final String TURTLEIMAGE_PACKAGE = "turtleImages/";

    private Property<Image> myTurtleImage;

    private static final int VBOX_LABEL_INDEX = 0;
    private static final int BUTTON_TEXT_INDEX = 1;
    private static final int BUTTON_METHOD_INDEX = 2;
    private static final String PROPERTIES_REGEX_SPLITTER = ", ";

    private ColorPickerInputs myColorPickers;
    private ComboBoxInputs myComboButtonInputs;

    private FileReader myReader;
    private FileWriter myWriter;

    public ButtonInputs(ColorPickerInputs cp, ComboBoxInputs cb) {
        myButtons = new HBox();
        for (String buttonType : ALL_BUTTONS) makeButtonVBox(buttonType);
        myButtons = formatButtons(myButtons);
        myColorPickers = cp;
        myComboButtonInputs = cb;

        myReader = new FileReader();
        myWriter = new FileWriter();
    }

    public Property<Image> getTurtleImage() {return myTurtleImage;}

    public HBox getButtonsHBox() {return myButtons;}

    private void makeButtonVBox(String buttonType) {
        if (buttonType.equals(TURTLE_KEY)) setDefaultTurtle();

        VBox addedVBox = new VBox();
        addedVBox.setAlignment(Pos.CENTER);
        String[] buttonInfo = myButtonsBundle.getString(buttonType).split(PROPERTIES_REGEX_SPLITTER);
        Label addedLabel = new Label(buttonInfo[VBOX_LABEL_INDEX]);
        Button addedButton = new Button(buttonInfo[BUTTON_TEXT_INDEX]);
        // FIXME: this is bad code
        addedButton.setOnAction(e -> {
            try {
                this.getClass().getDeclaredMethod(buttonInfo[BUTTON_METHOD_INDEX], null).invoke(this);
            } catch(Exception ex) {
                System.out.println(ex);
            }
        });
        addedVBox.getChildren().addAll(addedLabel, addedButton);
        myButtons.getChildren().add(addedVBox);
    }

    private void setDefaultTurtle() {
        myTurtleImage = new SimpleObjectProperty<>();
        String defaultFilePath = TURTLEIMAGE_PACKAGE + "perfectTurtle" + TURTLE_FILE_EXTENSION;
        System.out.println(defaultFilePath);
        //TODO: need an exception for an invalid Turtle; in theory this could just be a FileNotFoundException
        try {
            myTurtleImage.setValue(new Image(this.getClass().getClassLoader().getResourceAsStream(defaultFilePath)));
        } catch (Exception e) {
            String errorMessage = "INVALID TURTLE";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(errorMessage);
            Platform.runLater(alert::showAndWait);
            inputTurtleFile();
        }
    }

    private void inputTurtleFile() {
        File turtleFile = TURTLE_FILE_CHOOSER.showOpenDialog(new Stage());
        //TODO: Handle this error if the Turtle File selected is null and check type of image
        if (turtleFile == null) {
            return;
        }
        myTurtleImage.setValue(new Image(turtleFile.toURI().toString()));
    }

    private FileChooser createFileChooser(String extension, String fileType, String directory) {
        FileChooser returnedChooser = new FileChooser();
        returnedChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter(fileType, extension));
        returnedChooser.setInitialDirectory(new File(System.getProperty(directory)));
        return returnedChooser;
    }

    private void inputPrefFile() {
       TextInputDialog prefProperties = new TextInputDialog("Duvall");
       prefProperties.setHeaderText("What Preference Would You Like to Load?");
       prefProperties.showAndWait();
       ResourceBundle prefBundle = null;
       try {
           prefBundle = ResourceBundle.getBundle(prefProperties.getEditor().getText());
           myColorPickers.setBackground(prefBundle.getString("background"));
           myColorPickers.setPen(prefBundle.getString("pen"));
           myComboButtonInputs.getLanguageProperty().setValue(prefBundle.getString("language"));
           // TODO: duplication

           String defaultFilePath = TURTLEIMAGE_PACKAGE + prefBundle.getString("turtle") + TURTLE_FILE_EXTENSION;
           myTurtleImage.setValue(new Image(this.getClass().getClassLoader().getResourceAsStream(defaultFilePath)));
       } catch (Exception e) {
           System.out.println(e);
       }
    }

    private void saveProperties() {
        Map<String, String> savedPreferences = new HashMap<String, String>();
        savedPreferences.put("turtle", myTurtleImage.getName());
        savedPreferences.put("background", myColorPickers)
        myWriter.
    }

    private void createNewWindow() {
        SlogoView newWindow = new SlogoView(new Stage());
    }
}
