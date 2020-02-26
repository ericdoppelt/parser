package slogo.View;

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
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ResourceBundle;


public class InputPanel {

    private ResourceBundle myResourceBundle  = ResourceBundle.getBundle(DEFAULT_LANGUAGE);

    private static final Color DEFAULT_BACKGROUND_COLOR = Color.CORAL;
    private static final Color DEFAULT_PEN_COLOR = Color.PURPLE;

    private static final ObservableList allLanguages = FXCollections.observableArrayList("Chinese", "English", "French", "German", "Italian", "Portuguese", "Russian", "Spanish", "Urdu");
    private static final String DEFAULT_LANGUAGE = "English";

    private static final String ACCEPTABLE_FILE_EXTENSION = "*.png";
    private static final String TYPE_OF_FILE_EXTENSION = "PNG";
    private static final String INITIAL_FILE_DIRECTORY = "user.dir";
    private final FileChooser FILE_CHOOSER = createFileChooser(ACCEPTABLE_FILE_EXTENSION);

    private static final String DEFAULT_TURTLE_IMAGE = "turtleImages/turtleImage.png";

    private ColorPicker myBackGroundPicker;
    private ColorPicker myPenPicker;

    private String myCurrentLanguage;
    private Color myCurrentPen;
    private Color myCurrentBackground;
    private Image myTurtleImage;

    HBox myButtons;

    public InputPanel() {
        myButtons = new HBox();
        initButtons();
        formatButtons();
    }

    public Node getInputPanel() {
        return myButtons;
    }

    public Color getBackgroundColor() {
        return myCurrentBackground;
    }

    public Color getPenColor()  {
        return myCurrentPen;
    }

    public String getLanguage() {
        return myCurrentLanguage;
    }

    public Image getTurtleImage() {
        return myTurtleImage;
    }

    private void initButtons() {
        initValues();
        createColorPickers();
        createLanguageButton();
        createTurtleButton();
    }


    private void initValues() {
        myCurrentLanguage = DEFAULT_LANGUAGE;
        myCurrentBackground = DEFAULT_BACKGROUND_COLOR;
        myCurrentPen = DEFAULT_PEN_COLOR;
        myTurtleImage = new Image(this.getClass().getClassLoader().getResourceAsStream(DEFAULT_TURTLE_IMAGE));
    }
    private void createLanguageButton() {

        Label addedLabel = new Label(myResourceBundle.getString("setLanguage"));
        ComboBox languageButton = new ComboBox(allLanguages);
        languageButton.getSelectionModel().select(DEFAULT_LANGUAGE);
        languageButton.setOnAction(event -> updateLanguage((String)languageButton.getValue()));
        addVBox(addedLabel, languageButton);
    }

    private void updateLanguage(String newLanguage) {
        myCurrentLanguage = newLanguage;
    }

    private void createColorPickers() {
        myCurrentBackground = DEFAULT_BACKGROUND_COLOR;
        myBackGroundPicker = createColorPicker(DEFAULT_BACKGROUND_COLOR);
        myBackGroundPicker.setOnAction(event -> myCurrentBackground = myBackGroundPicker.getValue());
        createColorBox(myResourceBundle.getString("setBackground"), myBackGroundPicker);

        myCurrentPen = DEFAULT_PEN_COLOR;
        myPenPicker   = createColorPicker(DEFAULT_PEN_COLOR);
        myPenPicker.setOnAction(event -> myCurrentPen = myPenPicker.getValue());
        createColorBox(myResourceBundle.getString("setPen"), myPenPicker);
    }

    //TODO: implement action
    private void createColorBox(String text, ColorPicker picker) {
        Label addedLabel = new Label(text);
        addVBox(addedLabel, picker);
    }

    private ColorPicker createColorPicker(Color defaultColor) {
        ColorPicker addedColorPicker = new ColorPicker(defaultColor);
        return addedColorPicker;
    }

    private void createTurtleButton() {

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
        //TODO: Handle this error
        if (turtleFile == null) {
            return;
        }
        myTurtleImage = new Image(turtleFile.toURI().toString());
    }

    private void formatButtons() {
        for (Node button : myButtons.getChildren()) {
            myButtons.setHgrow(button, Priority.ALWAYS);
        }
    }
}
