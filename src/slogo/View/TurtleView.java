package slogo.View;


import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javafx.scene.shape.Line;
import slogo.Model.TurtleData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Turtle View handles all turtle display methods along with its path.
 * @author erikgregorio
 */
public class TurtleView {
    public static final int X_COORDINATE = 0;
    public static final int Y_COORDINATE = 1;
    public static final double ANGLE_OFFSET = 90.0;
    public static final double CENTER_X = 385;
    public static final double CENTER_Y = 265;

    private static final String TURTLEIMAGES_DIRECTORY = "turtleImages";

    private SimpleBooleanProperty isPenDown;
    private SimpleDoubleProperty turtleAngle;
    private List<Double> currentPosition;
    private ObservableList<List<Double>> positions;
    private Pane myBackground;
    private ImageView turtleView;
    private ObjectProperty<File> myTurtleFile;

    private double heightOffset;
    private double widthOffset;
    private ObjectProperty<Color> penColor= new SimpleObjectProperty<>(Color.BLACK);
    private double lineWidth = 2.0;
    private SimpleDoubleProperty paneHeightOffset = new SimpleDoubleProperty();
    private SimpleDoubleProperty paneWidthOffset = new SimpleDoubleProperty();
    private TurtlePopUp myTurtleInfo;

    private int currentIndex;
    private ArrayList<Line> turtleLines;


    /**
     * Constructor used to build a new Turtle Display. One per backend Turtle.
     * @param turtle Backend Turtle To Bind
     * @param pane Platform to display Turtle
     */
    public TurtleView(TurtleData turtle, Pane pane){
        myBackground = pane;
        setUpTurtle(turtle, pane);
        currentPosition = setUpInitialPosition();
        bindProperties(turtle);
        addTurtlePopup();
    }

    private void setUpTurtle(TurtleData turtle, Pane pane) {
        turtleView = new ImageView(getImage("turtleImages/perfectTurtle.png"));
        myTurtleFile = new SimpleObjectProperty<File>();
        myTurtleFile.addListener((observable, oldValue, newValue) -> {
            String filePath = newValue.toString().substring(newValue.toString().indexOf(TURTLEIMAGES_DIRECTORY));
            turtleView.setImage(new Image(filePath));
        });

        myTurtleFile.setValue(new File("turtleImages/perfectTurtle.png"));

        System.out.println(turtleView);
        turtleView.setRotate(turtle.getTurtleHeading() + ANGLE_OFFSET);

        pane.getChildren().add(turtleView);
        turtleView.setY(CENTER_Y - heightOffset);
        turtleView.setX(CENTER_X - widthOffset);

        paneWidthOffset.bind(pane.widthProperty());
        paneHeightOffset.bind(pane.heightProperty());
    }

    private Image getImage(String Path){
        Image newImage = new Image(Path);
        heightOffset = newImage.getHeight()/2;
        widthOffset = newImage.getWidth()/2;
        return newImage;
    }

    private List<Double> setUpInitialPosition(){
        ArrayList<Double > myList = new ArrayList<>();
        myList.add(turtleView.getX());
        myList.add(turtleView.getY());
        return myList;
    }

    /**
     * Methods used to bind the properties of all the backend turtle to the front end turtle
     * @param turtle
     */

    private void bindProperties(TurtleData turtle){
        bindPositions(turtle.getCoordHistory());
        bindPen(turtle.getPenDownProperty());
        bindAngle(turtle.directionProperty());
        turtleView.visibleProperty().bind(turtle.turtleVisibility());
    }

    private void bindPositions(ObservableList<List<Double>> turtlepos){
        positions = FXCollections.observableArrayList();
        Bindings.bindContentBidirectional(positions, turtlepos);
        positions.addListener((ListChangeListener<List<Double>>) c -> newTurtlePosition(c));
    }

    private void newTurtlePosition(ListChangeListener.Change<? extends List<Double>> allPositions){
        List<Double> newPosition = allPositions.getList().get(allPositions.getList().size()-1);
        if(isPenDown.get()) addPath(getNewLine(currentPosition, newPosition));
        updateTurtlePosition(newPosition.get(X_COORDINATE), newPosition.get(Y_COORDINATE));
    }

    private void bindPen(SimpleBooleanProperty backendPen){
        isPenDown = new SimpleBooleanProperty();
        Bindings.bindBidirectional(isPenDown, backendPen);
    }

    private void bindAngle(SimpleDoubleProperty backendAngle){
        turtleAngle = new SimpleDoubleProperty();
        Bindings.bindBidirectional(turtleAngle, backendAngle);
        turtleAngle.addListener( (observable, oldValue, newValue) ->
                turtleView.setRotate((newValue.doubleValue()+ ANGLE_OFFSET)));
    }

    /**
     * Methods to handle turtle popup menu as well as when to hide it and show it
     */
    private void addTurtlePopup(){
        turtleView.setOnMouseEntered(event -> showPopup());
        turtleView.setOnMouseExited(event -> hidePopup());
    }

    private void showPopup(){
        if(myTurtleInfo == null) myTurtleInfo = new TurtlePopUp(this);
        updatePopupPosition();
        myTurtleInfo.show(turtleView.getScene().getWindow());
    }

    private void updatePopupPosition(){
        if(myTurtleInfo == null) return;
        myTurtleInfo.updatePosition(turtleView.getX() + turtleView.getScene().getWindow().getX() + 2*widthOffset,
                turtleView.getY() + turtleView.getScene().getWindow().getY() + 2*heightOffset);
    }

    private void hidePopup(){
        myTurtleInfo.hide();
    }

    /**
     * Update turtle position using the given new coordinates
     */
    private void updateTurtlePosition(double x, double y){
        turtleView.setX(x + paneWidthOffset.get()/2);
        turtleView.setY(y + paneHeightOffset.get()/2);
        currentPosition.clear();
        currentPosition.add(X_COORDINATE, turtleView.getX());
        currentPosition.add(Y_COORDINATE, turtleView.getY());
    }

    /**
     * Methods for adding a new line/path for the turtle and keeping track of them
     * @param newPath
     */

    private void addPath(Line newPath){
        myBackground.getChildren().add(newPath);
        if(turtleLines == null) turtleLines = new ArrayList<>();
        if(undoButtonClicked()) clearOldPath();
        turtleLines.add(newPath);
        currentIndex = turtleLines.size();
    }

    private boolean undoButtonClicked(){
        return (currentIndex < turtleLines.size()-1);
    }
    private void clearOldPath(){
        //TODO REMOVE LINES OF OLD PATH;
    }

    private Line getNewLine(List<Double> oldValues, List<Double> newValues){
        Line newPath = new Line();
        newPath.setStartX(oldValues.get(X_COORDINATE) + widthOffset);
        newPath.setStartY(oldValues.get(Y_COORDINATE) + heightOffset);
        newPath.setEndX(newValues.get(X_COORDINATE) + widthOffset + paneWidthOffset.get()/2);
        newPath.setEndY(newValues.get(Y_COORDINATE) + heightOffset + paneHeightOffset.get()/2);
        newPath.setStroke(penColor.getValue());
        newPath.setStrokeWidth(lineWidth);
        return newPath;
    }

    /**
     * Method that allows setting a new image for the turtle
     */
    public ObjectProperty<File> getTurtleFile(){
        return myTurtleFile;
    }

    /**
     * Turtle Method returns Color property to bind for automatic Pen Color changing
     * @return Color Property
     */
    public ObjectProperty<Color> getPenColorProperty(){
        return penColor;
    }
    /**
     * Undo button to restore turtle to its second most recent postition
     */
    public void undoMovement(){
        // TODO move turtle back to its position and remove path
    }

}