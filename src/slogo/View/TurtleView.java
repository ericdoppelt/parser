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

    private static final String DEFAULT_IMAGE_PATH = "turtleImages/perfectTurtle.png";

    private SimpleBooleanProperty isPenDown;
    private SimpleDoubleProperty turtleAngle;
    private List<Double> previousPosition;
    private ObservableList<List<Double>> positions;
    private Pane myBackground;
    private ImageView turtleView;

    private double heightOffset;
    private double widthOffset;
    private ObjectProperty<Color> penColor= new SimpleObjectProperty<>(Color.BLACK);
    private double lineWidth = 2.0;
    private SimpleDoubleProperty paneHeightOffset = new SimpleDoubleProperty();
    private SimpleDoubleProperty paneWidthOffset = new SimpleDoubleProperty();
    private TurtlePopUp myTurtleInfo;

    /**
     * Constructor used to build a new Turtle Display. One per backend Turtle.
     * @param turtle Backend Turtle To Bind
     * @param pane Platform to display Turtle
     */
    public TurtleView(TurtleData turtle, Pane pane){
        myBackground = pane;
        setUpTurtle(turtle, pane);
        previousPosition = setUpInitialPosition();
        bindProperties(turtle);
        addTurtlePopup();
    }

    private void setUpTurtle(TurtleData turtle, Pane pane) {
        turtleView = new ImageView(getImage(DEFAULT_IMAGE_PATH));
        turtleView.setRotate(turtle.getTurtleHeading() + ANGLE_OFFSET);
        pane.getChildren().add(turtleView);

        turtleView.setY(CENTER_Y - heightOffset);
        turtleView.setX(CENTER_X - widthOffset);

        paneWidthOffset.bind(pane.widthProperty());
        paneHeightOffset.bind(pane.heightProperty());
    }

    private void bindProperties(TurtleData turtle){
        bindPositions(turtle.getCoordHistory());
        isPenDown = new SimpleBooleanProperty();
        turtleAngle = new SimpleDoubleProperty();
        Bindings.bindBidirectional(isPenDown, turtle.getPenDownProperty());
        Bindings.bindBidirectional(turtleAngle, turtle.directionProperty());
        turtleView.visibleProperty().bind(turtle.turtleVisibility());
        addAngleChangeListener();
    }

    private void bindPositions(ObservableList<List<Double>> turtlepos){
        positions = FXCollections.observableArrayList();
        Bindings.bindContentBidirectional(positions, turtlepos);
        addPositionChangeListener(positions);
    }

    private void addAngleChangeListener(){
        turtleAngle.addListener( (observable, oldValue, newValue) ->
                turtleView.setRotate((newValue.doubleValue()+ ANGLE_OFFSET)));
    }

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

    private void addPositionChangeListener(ObservableList<List<Double>> x){
        x.addListener((ListChangeListener<List<Double>>) c -> {
            List<Double> currentPosition = c.getList().get(c.getList().size()-1);

            if(isPenDown.get()) addPath(getNewLine(previousPosition, currentPosition));
            updateTurtlePosition(currentPosition.get(X_COORDINATE), currentPosition.get(Y_COORDINATE));
        });
    }

    private void updateTurtlePosition(double x, double y){
        turtleView.setX(x + paneWidthOffset.get()/2);
        turtleView.setY(y + paneHeightOffset.get()/2);
        previousPosition.clear();
        previousPosition.add(X_COORDINATE, turtleView.getX());
        previousPosition.add(Y_COORDINATE, turtleView.getY());
    }

    private void addPath(Line newPath){
        newPath.setStroke(penColor.getValue());
        newPath.setStrokeWidth(lineWidth);
        myBackground.getChildren().add(newPath);
    }

    private Line getNewLine(List<Double> oldValues, List<Double> newValues){
        Line newPath = new Line();
        newPath.setStartX(oldValues.get(X_COORDINATE) + widthOffset);
        newPath.setStartY(oldValues.get(Y_COORDINATE) + heightOffset);
        newPath.setEndX(newValues.get(X_COORDINATE) + widthOffset + paneWidthOffset.get()/2);
        newPath.setEndY(newValues.get(Y_COORDINATE) + heightOffset + paneHeightOffset.get()/2);
        return newPath;
    }

    /**
     * Method that allows setting a new image for the turtle
     */
    public ObjectProperty<Image> getImageProperty(){
        return turtleView.imageProperty();
    }

    /**
     * Turtle Method returns Color property to bind for automatic Pen Color changing
     * @return Color Property
     */
    public ObjectProperty<Color> getPenColorProperty(){
        return penColor;
    }

}