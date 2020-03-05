package slogo.View;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.converter.NumberStringConverter;
import slogo.Model.TurtleData;

import java.awt.*;

public class TurtlePopUp {

    public static final String TURTLE_ID = "Turtle ID: ";
    public static final String TURTLE_HEADING = "Turtle Direction: ";
    public static final String TURTLE_POSITION = "Turtle Position: ";
    public static final String PEN_WIDTH = "Modify Pen Width";
    public static final String COLOR_PICKER_TITLE = "Choose Pen Color:";

    private ColorPicker penColor;
    private VBox penProperties;
    private VBox infoMenu;
    private Popup myPopup;
    private TextField penWidth;
    private TurtleView myTurtle;
    private SimpleStringProperty turtleID;
    private boolean isActive;


    public TurtlePopUp(TurtleView turtleInfo){
        myTurtle = turtleInfo;
        myPopup = new Popup();
        setUpPane();
    }

    private void setUpPane(){
        infoMenu = new VBox();
        infoMenu.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        infoMenu.backgroundProperty().setValue(new Background( new BackgroundFill(Color.WHITE, null, null)));
        infoMenu.getChildren().addAll(new Label(TURTLE_ID), new Label(TURTLE_HEADING), new Label(TURTLE_POSITION), getPenProperties());
        myPopup.getContent().add(infoMenu);
        infoMenu.setOnMouseEntered(event -> isActive = true);
        infoMenu.setOnMouseExited(event -> {
            isActive = false;
            hide();
        });
    }

    private VBox getPenProperties(){
        penProperties = new VBox();
        addColorPicker();
        addPenWidth();
        return penProperties;
    }

    private void addPenWidth() {
        penWidth = new TextField();
        penWidth.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));
        penWidth.setPrefWidth(3);
        penWidth.setPromptText(PEN_WIDTH);
        penProperties.getChildren().add(penWidth);
    }

    private void addColorPicker(){
        Label colorTitle = new Label(COLOR_PICKER_TITLE);
        penColor = new ColorPicker();
        VBox pColor = new VBox();
        pColor.getChildren().addAll(colorTitle, penColor);
        penProperties.getChildren().add(pColor);
    }

    public void show(Window display){
        myPopup.show(display);
    }

    public void hide(){
        if(!isActive) myPopup.hide();
    }

    public void updatePosition(Double x, Double y){
        myPopup.setX(x - 20);
        myPopup.setY(y + 5);

    }
}
