package slogo.View.Input;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Inputs {

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
