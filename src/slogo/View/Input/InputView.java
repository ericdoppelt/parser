package slogo.View.Input;

import javafx.beans.property.Property;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import slogo.View.Input.ButtonInputs;
import slogo.View.Input.ColorPickerInputs;
import slogo.View.Input.ComboBoxInputs;
import slogo.View.Input.Inputs;

public class InputView extends Inputs {

    private HBox allInputs;
    private ButtonInputs myButtons;
    private ColorPickerInputs myColorPickers;
    private ComboBoxInputs myComboBoxes;

    public InputView() {
        allInputs = new HBox();
        myButtons = new ButtonInputs();
        myColorPickers = new ColorPickerInputs();
        myComboBoxes = new ComboBoxInputs();
        allInputs.getChildren().addAll(myButtons.getButtonsHBox(), myColorPickers.getColorPickersHBox(), myComboBoxes.getComboBoxesHBox());
        allInputs = formatButtons(allInputs);
    }

    //FIXME: find a way to make this not return the HBox?
    public Node getInputPanel() {return allInputs;}

    public Property<Color> getBackgroundPropertyColor() {return myColorPickers.getBackgroundProperty();}

    public Property<Color> getPenPropertyColor()  {return myColorPickers.getBackgroundProperty();}

    public Property<String> getLanguage() {return myComboBoxes.getLanguageProperty();}

    public Property<Image> getTurtleImage() {
        return myButtons.getTurtleImage();
    }
}
