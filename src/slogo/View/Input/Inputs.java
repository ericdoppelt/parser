package slogo.View.Input;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Inputs {

    protected static final String PROPERTIES_REGEX_SPLITTER = ", ";
    protected static final int VBOX_LABEL_INDEX = 0;

    protected VBox createInputVBox() {
        VBox addedVBox = new VBox();
        addedVBox.setAlignment(Pos.CENTER);
        return addedVBox;
    }

    protected HBox formatButtons(HBox inputs) {
        for (Node input : inputs.getChildren()) {
            inputs.setHgrow(input, Priority.ALWAYS);
        }
        return inputs;
    }
}
