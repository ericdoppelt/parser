package slogo.View;

import javafx.beans.property.*;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class InputView {

    private ResourceBundle myResourceBundle  = ResourceBundle.getBundle(DEFAULT_LANGUAGE);

    private static final Color DEFAULT_BACKGROUND_COLOR = Color.ANTIQUEWHITE;
    private static final Color DEFAULT_PEN_COLOR = Color.web("0xcc8099ff");

    private ObservableList allLanguages;
    private static final String PATH_TO_RESOURCE_LANGUAGES = "././././resources/languages";
    private static final int LENGTH_OF_FILE_ENDING = 11;
    private static final String DEFAULT_LANGUAGE = "English";

    private static final String ACCEPTABLE_FILE_EXTENSION = "*.png";
    private static final String TYPE_OF_FILE_EXTENSION = "PNG";
    private static final String INITIAL_FILE_DIRECTORY = "user.dir";
    private final FileChooser FILE_CHOOSER = createFileChooser(ACCEPTABLE_FILE_EXTENSION);

    private static final String DEFAULT_TURTLE_IMAGE = "turtleImages/perfectTurtle.png";

    private ColorPicker myBackGroundPicker;
    private ColorPicker myPenPicker;
    private ComboBox myLanguageBox;
    private Property<Image> myTurtleImage;

    HBox myButtons;

    public InputView() {
        initLanguageOptions();
        initButtons();
        formatButtons();
    }

    //FIXME: find a way to make this not return the HBox?
    public Node getInputPanel() {return myButtons;}

    public Property<Color> getBackgroundPropertyColor() {return myBackGroundPicker.valueProperty();}

    public Property<Color> getPenPropertyColor()  {return myPenPicker.valueProperty();}

    public Property<String> getLanguage() {return myLanguageBox.valueProperty();}

    public Property<Image> getTurtleImage() {
        return myTurtleImage;
    }

    private void initLanguageOptions() {
        //TODO: exception here
        File languageDirectory = new File(PATH_TO_RESOURCE_LANGUAGES);
        List languageNames = new ArrayList<String>();
        for (File tempFile : languageDirectory.listFiles()) {
            String fileName = tempFile.getName();
            fileName = fileName.substring(0, fileName.length() - LENGTH_OF_FILE_ENDING);
            languageNames.add(fileName);
        }
        allLanguages = FXCollections.observableArrayList(languageNames);
    }

    private void initButtons() {
        myButtons = new HBox();
        initColorPickers();
        initLanguageButton();
        initTurtleButton();
    }

    private void initColorPickers() {
        myBackGroundPicker = new ColorPicker(DEFAULT_BACKGROUND_COLOR);
        createColorBox(myResourceBundle.getString("setBackground"), myBackGroundPicker);

        myPenPicker = new ColorPicker(DEFAULT_PEN_COLOR);
        createColorBox(myResourceBundle.getString("setPen"), myPenPicker);
    }

    private void createColorBox(String text, ColorPicker picker) {
        Label addedLabel = new Label(text);
        addVBox(addedLabel, picker);
    }

    private void initLanguageButton() {
        Label addedLabel = new Label(myResourceBundle.getString("setLanguage"));
        myLanguageBox = new ComboBox(allLanguages);
        myLanguageBox.getSelectionModel().select(DEFAULT_LANGUAGE);
        addVBox(addedLabel, myLanguageBox);
    }

    private void initTurtleButton() {
        myTurtleImage = new SimpleObjectProperty<>();
        //TODO: need an exception for an invalid Turtle; in theory this could just be a FileNotFoundException
        try {
            myTurtleImage.setValue(new Image(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE_IMAGE)));
        } catch (Exception e) {
            String errorMessage = "INVALID TURTLE";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(errorMessage);
            Platform.runLater(alert::showAndWait);
            inputFile();
        }

        Label turtleLabel = new Label(myResourceBundle.getString("setTurtle"));
        Button turtleButton = new Button(myResourceBundle.getString("loadTurtleImage"));
        turtleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                inputFile();
            }
        });
        addVBox(turtleLabel, turtleButton);
    }

    private FileChooser createFileChooser(String extension) {
        FileChooser returnedChooser = new FileChooser();
        returnedChooser.setTitle(myResourceBundle.getString("turtleImageChooser"));
        returnedChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter(TYPE_OF_FILE_EXTENSION, extension));
        returnedChooser.setInitialDirectory(new File(System.getProperty(INITIAL_FILE_DIRECTORY)));
        return returnedChooser;
    }

    private VBox addVBox(Label label, Node input) {
        VBox returnedBox = new VBox();
        returnedBox.setAlignment(Pos.CENTER);
        returnedBox.getChildren().addAll(label, input);
        myButtons.getChildren().add(returnedBox);
        return returnedBox;
    }

    private void inputFile() {
        File turtleFile = FILE_CHOOSER.showOpenDialog(new Stage());
        //TODO: Handle this error if the Turtle File selected is null and check type of image
        if (turtleFile == null) {
            return;
        }
        myTurtleImage.setValue(new Image(turtleFile.toURI().toString()));
    }

    private void formatButtons() {
        for (Node button : myButtons.getChildren()) {
            myButtons.setHgrow(button, Priority.ALWAYS);
        }
    }
}
