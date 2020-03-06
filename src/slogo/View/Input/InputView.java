package slogo.View.Input;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class InputView extends Inputs {

    private HBox myInputBar;
    private ButtonInputs myButtons;
    private ComboBoxInputs myComboBoxes;

    private ObjectProperty<Color> myBackgroundColor;
    private ObjectProperty<Color> myPenColor;
    private ObjectProperty<String> myLanguage;
    private ObjectProperty<Image> myTurtleImage;

    public InputView() {
        instantiateProperties();
        myInputBar = new HBox();
        ColorPickerInputs ColorPickers = new ColorPickerInputs(myBackgroundColor, myPenColor);
        ComboBoxInputs ComboBoxes = new ComboBoxInputs(myLanguage);
        ButtonInputs Buttons = new ButtonInputs(myBackgroundColor, myPenColor, myLanguage, myTurtleImage);
        System.out.println(myTurtleImage);

        myInputBar.getChildren().addAll(ColorPickers.getColorPickersHBox(), ComboBoxes.getComboBoxesHBox(), Buttons.getButtonsHBox());
        myInputBar = formatButtons(myInputBar);
    }

    //FIXME: find a way to make this not return the HBox?
    public Node getInputPanel() {return myInputBar;}

    public Property<Color> getBackgroundPropertyColor() {return myBackgroundColor;}

    public Property<Color> getPenPropertyColor()  {return myPenColor;}

    public Property<String> getLanguage() {return myLanguage;}

    public Property<Image> getTurtleImage() {
        return myTurtleImage;
    }

    private void instantiateProperties() {
        myBackgroundColor = new SimpleObjectProperty<Color>();
        myPenColor = new SimpleObjectProperty<Color>();
        myLanguage = new SimpleObjectProperty<String>();
        myTurtleImage  = new SimpleObjectProperty<Image>();
    }
}
