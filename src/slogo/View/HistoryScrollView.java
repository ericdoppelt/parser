package slogo.View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Map;

public class HistoryScrollView {

    private ScrollPane myScrollPane;
    private VBox myLabels;
    private List<String> myInfo;

    public HistoryScrollView(List info) {
        myScrollPane = new ScrollPane();
        myScrollPane.setFitToHeight(true);
        myLabels = new VBox();
        myLabels.setAlignment(Pos.CENTER);
        myInfo = info;

        createLabels();
        fillScrollPane();
    }

    private void createLabels() {
        for (String s : myInfo) {
            HBox labelWrapper = new HBox();
            Label tempInfo = new Label();
            tempInfo.setText(s);
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
