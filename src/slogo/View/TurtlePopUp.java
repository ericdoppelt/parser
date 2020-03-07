package slogo.View;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
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
    public static final String TRUE = "DOWN";
    public static final String FALSE = "UP";
    public static final String TOGGLE = "Click To Toggle Pen Property";
    public static final String ZERO = "0.0";
    public static final String UNDO = "Undo";
    public static final String REDO = "Redo";

    private ColorPicker penColor;
    private VBox penProperties;
    private VBox infoMenu;
    private HBox myButtons;
    private Popup myPopup;
    private TextField penWidth;
    private TurtleView myTurtle;
    private Button penStatus;
    private boolean isActive;
    private boolean choosingColor;

    private Label turtleID;
    private Label turtleHeading;
    private Label turtlePosition;


    public TurtlePopUp(TurtleView turtleInfo,Double initX, Double initY){
        myTurtle = turtleInfo;
        myPopup = new Popup();
        myPopup.getContent().add(getInfoMenu());
        bindProperties();
        setTurtlePosition(initX, initY);
    }

    private VBox getInfoMenu(){
        infoMenu = getNewMenu();
        infoMenu.getChildren().addAll(turtleIDLabel(), headingLabel(), positionLabel(), getPenProperties(), getButtons());
        infoMenu.setOnMouseEntered(event -> isActive = true);
        infoMenu.setOnMouseExited(event -> {
            isActive = false;
            if(!choosingColor) hide();
        });
        return infoMenu;
    }

    private VBox getNewMenu(){
        VBox menu = new VBox();
        menu.setBorder(new Border(
                new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        menu.backgroundProperty().setValue(
                new Background(new BackgroundFill(Color.WHITE, null, null)));
        return menu;
    }

    private HBox turtleIDLabel(){
        HBox idLabel = new HBox();
        turtleID = new Label(myTurtle.getID());
        idLabel.getChildren().addAll(new Label(TURTLE_ID), turtleID);

        return idLabel;
    }

    private HBox headingLabel(){
        HBox angleLabel = new HBox();
        turtleHeading = new Label(ZERO);
        angleLabel.getChildren().addAll(new Label(TURTLE_HEADING), turtleHeading);
        return angleLabel;
    }

    private HBox positionLabel(){
        HBox positionLabel = new HBox();
        turtlePosition = new Label();
        positionLabel.getChildren().addAll(new Label(TURTLE_POSITION), turtlePosition);
        return positionLabel;
    }

    /**
     * Bind all properties to be displayed
     */
    private void bindProperties(){
        // TODO Bind all label properties to the respective values
        myTurtle.getPenColorProperty().bind(penColor.valueProperty());
        //turtleHeading.labelForProperty().bind(myTurtle);
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
        penWidth.setOnKeyPressed(event -> handleWidthInput(event.getCode()));
    }

    private void handleWidthInput(KeyCode keyPressed){
        if(keyPressed == KeyCode.ENTER){
            Double newWidth = Double.parseDouble(penWidth.getText());
            myTurtle.setNewWidth(newWidth);
            penWidth.clear();
        }
    }

    private void addColorPicker(){
        VBox pColor = new VBox();
        penColor = new ColorPicker();
        penColor.setOnMouseClicked(event -> choosingColor = !choosingColor);
        penColor.setOnAction(event -> {
            hide();
            choosingColor = false;
        });

        pColor.getChildren().addAll(new Label(COLOR_PICKER_TITLE), penColor);
        penProperties.getChildren().add(pColor);
    }

    private HBox getButtons(){
        myButtons = new HBox();
        addPenToggle();
        addUndoRedo();
        return myButtons;
    }

    private void addPenToggle(){
        penStatus = new Button(FALSE);
        penStatus.setOnMouseClicked(event -> {
            myTurtle.setPenDown(!myTurtle.getPenDownProperty().get());
            if(myTurtle.getPenDownProperty().get()) penStatus.setText(TRUE);
            else penStatus.setText(FALSE);
        });
        penStatus.setTooltip(new Tooltip(TOGGLE));
        myButtons.getChildren().add(penStatus);
    }

    public void setPenStatus(boolean status){
        if(status) penStatus.setText(TRUE);
        else penStatus.setText(FALSE);
    }

    private void addUndoRedo(){
        Button undo = new Button(UNDO);
        Button redo = new Button(REDO);

        undo.setOnAction(event -> myTurtle.undoMovement());
        redo.setOnAction(event -> myTurtle.redoMovement());

        myButtons.getChildren().addAll(undo, redo);
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
    public void updatePopupPosition(Double x, Double y){
        myPopup.setX(x);
        myPopup.setY(y);
    }

    /**
     * Update the x,y coordinates of the turtle
     * @param x new x coordinate
     * @param y new y coordinate
     */
    public void setTurtlePosition(Double x, Double y){
        turtlePosition.setText("("+x.intValue()+","+(-1*y.intValue())+")");
    }
    /**
     *
     */
    public void updateHeading(Double newHeading){
        if(newHeading == TurtleView.OFFSET) turtleHeading.setText(ZERO);
        else turtleHeading.setText(newHeading.toString());
    }
}
