package slogo.View.Info;

import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

public class ColorInfoView  {

    private MapProperty<Integer, List> myInfo;

    private VBox displayedInfo;

    private static final String COLON_REGEX = ":";
    private static final String SPACE = " ";
    private static final int R_INDEX = 0;
    private static final int G_INDEX = 1;
    private static final int B_INDEX = 2;

    public ColorInfoView() {
        displayedInfo = new VBox();

        myInfo = new SimpleMapProperty<Integer, List>();
        myInfo.addListener((observable, oldValue, newValue) -> {
            displayedInfo.getChildren().clear();
            for (Integer index : (newValue.keySet())) {
                List<Integer> RGBColors = myInfo.get(index);
                String displayedText = index + COLON_REGEX + SPACE + RGBColors.get(R_INDEX) + SPACE + RGBColors.get(G_INDEX) + SPACE + RGBColors.get(B_INDEX);
                Label addedLabel = new Label(displayedText);
                addedLabel.setBackground(backgroundFromRGB(RGBColors.get(R_INDEX), RGBColors.get(G_INDEX), RGBColors.get(B_INDEX)));
                displayedInfo.getChildren().add(addedLabel);
            }
        });
    }

    public VBox getInfoVBox() {
        return displayedInfo;
    }

    public MapProperty getInfoProperty() {
        return myInfo;
    }

    private Background backgroundFromRGB(Integer r, Integer g, Integer b) {
        return new Background(new BackgroundFill(Color.rgb(r, g, b), CornerRadii.EMPTY, Insets.EMPTY));
    }
}
