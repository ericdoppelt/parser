package slogo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class InputButtons {

    HBox allButtons;
    ColorPicker backgroundPicker;
    ColorPicker penPicker;

    public InputButtons() {
        allButtons = new HBox();
        initButtons();
    }

    private void initButtons() {
        makeButton("Set Background Color");

    }

    private void makeColorChooser(String label, EventHandler<ActionEvent> e) {
        Button tempButton = new Button(label);
        tempButton.setOnAction(e);
        tempButton.setMaxWidth(Double.MAX_VALUE);
        buttonList.add(tempButton);
    }
}
