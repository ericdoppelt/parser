package slogo.View.Input;

import javafx.beans.property.Property;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ColorPickerInputs extends Inputs {

    private static final List<String> ALL_COLOR_PICKERS = new ArrayList<>(Arrays.asList("background", "pen"));
    private static final String COLORPICKERS_BUNDLE = "colorPickers";
    private ResourceBundle myColorPickersBundle  = ResourceBundle.getBundle(COLORPICKERS_BUNDLE);

    private static final String BACKGROUND_KEY = "background";
    private static final String PEN_KEY = "pen";

    private ColorPicker myBackGroundPicker;
    private ColorPicker myPenPicker;

    private HBox myColorPickers;

    private static final String PROPERTIES_REGEX_SPLITTER = ", ";
    private static final int VBOX_LABEL_INDEX = 0;

    public ColorPickerInputs() {
        myColorPickers = new HBox();
        for (String pickerType : ALL_COLOR_PICKERS) makeColorVBox(pickerType);
        myColorPickers = formatButtons(myColorPickers);
    }

    public HBox getColorPickersHBox() {return myColorPickers;}

    public Property<Color> getBackgroundProperty() {return myBackGroundPicker.valueProperty();}

    public Property<Color> getPenProperty() {return myPenPicker.valueProperty();}

    private void makeColorVBox(String pickerType) {
        VBox addedVBox = new VBox();
        addedVBox.setAlignment(Pos.CENTER);

        String[] ColorPickerInfo = myColorPickersBundle.getString(pickerType).split(PROPERTIES_REGEX_SPLITTER);

        Label addedLabel = new Label(ColorPickerInfo[VBOX_LABEL_INDEX]);
        ColorPicker addedColorPicker = new ColorPicker();

        if (pickerType.equals(BACKGROUND_KEY)) myBackGroundPicker = addedColorPicker;
        else if (pickerType.equals(PEN_KEY)) myPenPicker = addedColorPicker;

        addedVBox.getChildren().addAll(addedLabel, addedColorPicker);
        myColorPickers.getChildren().add(addedVBox);
    }
}
