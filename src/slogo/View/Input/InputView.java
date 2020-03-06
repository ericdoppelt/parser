package slogo.View.Input;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.File;

public class InputView extends Inputs {

    private HBox myInputBar;
    private ButtonInputs myButtons;
    private ComboBoxInputs myComboBoxes;

    private ObjectProperty<Color> myBackgroundColor;
    private ObjectProperty<Color> myPenColor;
    private ObjectProperty<String> myLanguage;
    private ObjectProperty<File> myTurtleFile;

    public InputView() {
        instantiateProperties();
        myInputBar = new HBox();
        ColorPickerInputs ColorPickers = new ColorPickerInputs(myBackgroundColor, myPenColor);
        ComboBoxInputs ComboBoxes = new ComboBoxInputs(myLanguage);
        ButtonInputs Buttons = new ButtonInputs(myBackgroundColor, myPenColor, myLanguage, myTurtleFile);

        myInputBar.getChildren().addAll(ColorPickers.getColorPickersHBox(), ComboBoxes.getComboBoxesHBox(), Buttons.getButtonsHBox());
        myInputBar = formatButtons(myInputBar);
    }

    //FIXME: find a way to make this not return the HBox?
    public Node getInputPanel() {return myInputBar;}

    public Property<Color> getBackgroundPropertyColor() {return myBackgroundColor;}

    public Property<Color> getPenPropertyColor()  {return myPenColor;}

    public Property<String> getLanguage() {return myLanguage;}

    public Property<File> getTurtleFile() {
        return myTurtleFile;
    }

    private void instantiateProperties() {
        myBackgroundColor = new SimpleObjectProperty<Color>();
        myPenColor = new SimpleObjectProperty<Color>();
        myLanguage = new SimpleObjectProperty<String>();
        myTurtleFile = new SimpleObjectProperty<File>();
    }
}
