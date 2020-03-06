package slogo.View;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.converter.NumberStringConverter;

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
        myPopup.getContent().add(getInfoMenu());
    }

    private VBox getInfoMenu(){
        infoMenu = getNewMenu();
        infoMenu.getChildren().addAll(new Label(TURTLE_ID), new Label(TURTLE_HEADING), new Label(TURTLE_POSITION), getPenProperties());
        infoMenu.setOnMouseEntered(event -> isActive = true);
        infoMenu.setOnMouseExited(event -> {
            isActive = false;
            hide();
        });
        return infoMenu;
    }

    private VBox getNewMenu(){
        VBox menu = new VBox();
        menu.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        menu.backgroundProperty().setValue(new Background( new BackgroundFill(Color.WHITE, null, null)));
        return menu;
    }

    private void bindProperties(){
        // TODO Bind all label properties to the respective values
    }

    /**
     * Methods used to create the Pen properties and adding them to the menu
     *
     */
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

    /**
     * Display the turtle menu within the given window
     * @param display where to show popup
     */
    public void show(Window display){
        myPopup.show(display);
    }

    /**
     * Call to hide the turtle menu
     */
    public void hide(){
        if(!isActive) myPopup.hide();
    }

    /**
     * Update the position of the window
     * @param x new x coordinate
     * @param y new y coordinate
     */
    public void updatePosition(Double x, Double y){
        myPopup.setX(x);
        myPopup.setY(y);
    }
}
