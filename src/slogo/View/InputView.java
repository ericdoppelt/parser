package slogo.View;

import javafx.beans.property.*;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;


public class InputView {

    private static final List<String> ALL_BUTTONS = new ArrayList<>(Arrays.asList("turtle", "preferences", "save", "workspace"));
    private static final List<String> ALL_COLOR_PICKERS = new ArrayList<>(Arrays.asList("background", "pen"));
    private static final List<String> ALL_COMBO_BOXES = new ArrayList<>(Arrays.asList("language"));
    private static final List<String> SAVED_PREFERENCES = new ArrayList<>(Arrays.asList("turtle", "language", "background", "pen"));

    private static final String BUTTONS_BUNDLE = "buttons";
    private static final String COLORPICKERS_BUNDLE = "colorPickers";
    private static final String COMBOBOXES = "comboBoxes";
    private static final String PROPERTIES_REGEX_SPLITTER = ", ";
    private static final int VBOX_LABEL_INDEX = 0;
    private static final int BUTTON_TEXT_INDEX = 1;
    private static final int BUTTON_METHOD_INDEX = 2;

    private ResourceBundle myButtonsBundle = ResourceBundle.getBundle(BUTTONS_BUNDLE);
    private ResourceBundle myColorPickersBundle  = ResourceBundle.getBundle(COLORPICKERS_BUNDLE);
    private ResourceBundle myComboBoxesBundle  = ResourceBundle.getBundle(COMBOBOXES);

    private ObservableList allLanguages = initLanguageOptions();
    private static final String PATH_TO_RESOURCE_LANGUAGES = "././././resources/languages";
    private static final int LENGTH_OF_FILE_ENDING = 11;

    private static final String ACCEPTABLE_TURTLE_FILE = "*.png";
    private static final String TURTLE_FILE_EXTENSION = ".png";
    private static final String TYPE_OF_TURTLE_FILE = "PNG";
    private static final String INITIAL_TURTLE_DIRECTORY = "user.dir";

    private static final String TURTLE_KEY = "turtle";
    private static final String BACKGROUND_KEY = "background";
    private static final String PEN_KEY = "pen";
    private static final String LANGUAGE_KEY = "language";

    //TODO: bad convention"
    private final FileChooser TURTLE_FILE_CHOOSER = createFileChooser(ACCEPTABLE_TURTLE_FILE, TYPE_OF_TURTLE_FILE, INITIAL_TURTLE_DIRECTORY);
    private static final String TURTLEIMAGE_PACKAGE = "turtleImages/";

    private ColorPicker myBackGroundPicker;
    private ColorPicker myPenPicker;
    private ComboBox myLanguageBox;
    private Property<Image> myTurtleImage;


    HBox myButtons;

    public InputView() {
        initButtons();
        formatButtons();
    }

    //FIXME: find a way to make this not return the HBox?
    public Node getInputPanel() {return myButtons;}

    public Property<Color> getBackgroundPropertyColor() {return myBackGroundPicker.valueProperty();}

    public Property<Color> getPenPropertyColor()  {return myPenPicker.valueProperty();}

    public Property<String> getLanguage() {return myLanguageBox.valueProperty();}

    public Property<Image> getTurtleImage() {return myTurtleImage;}

    private void initButtons() {
        myButtons = new HBox();
        for (String pickerType : ALL_COLOR_PICKERS) makeColorVBox(pickerType);
        for (String buttonType : ALL_BUTTONS) makeButtonVBox(buttonType);
        for (String comboBoxType : ALL_COMBO_BOXES) makeComboVBox(comboBoxType);
    }

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

    private void makeColorVBox(String pickerType) {
        VBox addedVBox = new VBox();
        addedVBox.setAlignment(Pos.CENTER);

        String[] ColorPickerInfo = myColorPickersBundle.getString(pickerType).split(PROPERTIES_REGEX_SPLITTER);

        Label addedLabel = new Label(ColorPickerInfo[VBOX_LABEL_INDEX]);
        ColorPicker addedColorPicker = new ColorPicker();

        if (pickerType.equals(BACKGROUND_KEY)) myBackGroundPicker = addedColorPicker;
        else if (pickerType.equals(PEN_KEY)) myPenPicker = addedColorPicker;

        addedVBox.getChildren().addAll(addedLabel, addedColorPicker);
        myButtons.getChildren().add(addedVBox);
    }

    private void makeComboVBox(String comboBoxType) {
        VBox addedVBox = new VBox();
        addedVBox.setAlignment(Pos.CENTER);

        String[] ComboBoxInfo  = myComboBoxesBundle.getString(comboBoxType).split(PROPERTIES_REGEX_SPLITTER);

        Label addedLabel = new Label(ComboBoxInfo[VBOX_LABEL_INDEX]);
        ComboBox addedComboBox = new ComboBox(allLanguages);

        if (comboBoxType.equals(LANGUAGE_KEY)) myLanguageBox = addedComboBox;
        addedVBox.getChildren().addAll(addedLabel, addedComboBox);
        myButtons.getChildren().add(addedVBox);
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


    private FileChooser createFileChooser(String extension, String fileType, String directory) {
        FileChooser returnedChooser = new FileChooser();
        returnedChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter(fileType, extension));
        returnedChooser.setInitialDirectory(new File(System.getProperty(directory)));
        return returnedChooser;
    }

    //FIXME: clear duplication here
    private void inputTurtleFile() {
        File turtleFile = TURTLE_FILE_CHOOSER.showOpenDialog(new Stage());
        //TODO: Handle this error if the Turtle File selected is null and check type of image
        if (turtleFile == null) {
            return;
        }
        myTurtleImage.setValue(new Image(turtleFile.toURI().toString()));
    }

    private void inputPrefFile() {
        TextInputDialog prefProperties = new TextInputDialog("Duvall");
        prefProperties.setHeaderText("What Preference Would You Like to Load?");
        prefProperties.showAndWait();
        ResourceBundle prefBundle = null;
        try {
            prefBundle = ResourceBundle.getBundle(prefProperties.getEditor().getText());
            myBackGroundPicker.setValue(Color.web(prefBundle.getString("background")));
            myPenPicker.setValue(Color.web(prefBundle.getString("pen")));
            myLanguageBox.setValue(prefBundle.getString("language"));
            // TODO: duplication

            String defaultFilePath = TURTLEIMAGE_PACKAGE + prefBundle.getString("turtle") + TURTLE_FILE_EXTENSION;
            myTurtleImage.setValue(new Image(this.getClass().getClassLoader().getResourceAsStream(defaultFilePath)));
        } catch (Exception e) {
            System.out.println(e);
        }
   }

   private void saveProperties() {
       File savedPreferences = new File("././././resources/preferences/test.properties");
   }

   private void createNewWindow() {
        SlogoView newWindow = new SlogoView(new Stage());
   }

    private void formatButtons() {
        for (Node button : myButtons.getChildren()) {
            myButtons.setHgrow(button, Priority.ALWAYS);
        }
    }
}
