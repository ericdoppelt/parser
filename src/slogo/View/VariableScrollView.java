package slogo.View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Map;

public class VariableScrollView {

    private ScrollPane myScrollPane;
    private VBox myLabels;
    private Map<String, Integer> myInfo;

    public VariableScrollView(Map info) {
        myScrollPane = new ScrollPane();
        myScrollPane.setFitToHeight(true);
        myLabels = new VBox();
        myLabels.setAlignment(Pos.CENTER);
        myInfo = info;

        createLabels();
        fillScrollPane();
    }

    private void createLabels() {
        for (String s : myInfo.keySet()) {
            HBox labelWrapper = new HBox();
            Label tempInfo = new Label();
            tempInfo.setText(s + ": " + myInfo.get(s));
            labelWrapper.getChildren().add(tempInfo);
            labelWrapper.setHgrow(tempInfo, Priority.ALWAYS);
            myLabels.getChildren().add(labelWrapper);
        }
    }

    private void fillScrollPane() {
        myScrollPane.setContent(myLabels);
        myScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        myScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    public ScrollPane getScrollPane() {
        return myScrollPane;
    }
}
