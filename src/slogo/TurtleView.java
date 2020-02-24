package slogo;


import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import slogo.Model.TurtleData;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * Turtle View handles all turtle display methods along with its path.
 * @author erikgregorio
 */
public class TurtleView {
    public static final int X_COORDINATE = 0;
    public static final int Y_COORDINATE = 1;
    public static final double ANGLE_OFFSET = 90.0;

    private static final String DEFAULT_IMAGE_PATH = "resources/languages/perfectTurtle.png";

    private SimpleBooleanProperty isPenDown;
    private SimpleDoubleProperty turtleAngle;
    private List<Double> previousPosition;
    private ObservableList<List<Double>> positions;
    private Pane myBackground;
    private ImageView turtleView;

    private double heightOffset;
    private double widthOffset;
    private Color lineColor = Color.BLACK;
    private double lineWidth = 2.0;

    public TurtleView(TurtleData turtle, Pane pane){
        myBackground = pane;
        setUpTurtle(turtle, pane);
        previousPosition = setUpInitialPosition();
        bindPositions(turtle.getCoordHistory());
        bindProperties(turtle);
    }

    private void setUpTurtle(TurtleData turtle, Pane pane) {
        Image t = new Image(DEFAULT_IMAGE_PATH);
        turtleView = new ImageView(t);
        turtleView.xProperty().bind(turtle.getTurtleXProperty());
        turtleView.yProperty().bind(turtle.getTurtleYProperty());
        turtleView.setRotate(turtle.getTurtleHeading()+ ANGLE_OFFSET);
        pane.getChildren().add(turtleView);
        heightOffset = t.getHeight()/2;
        widthOffset = t.getWidth()/2;
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
            previousPosition = currentPosition;
        });
    }

    private void addPath(Line newPath){
        newPath.setFill(lineColor);
        newPath.setStrokeWidth(lineWidth);
        myBackground.getChildren().add(newPath);
    }

    private Line getNewLine(List<Double> oldValues, List<Double> newValues){
        Line newPath = new Line();
        newPath.setStartX(oldValues.get(X_COORDINATE) + widthOffset);
        newPath.setStartY(oldValues.get(Y_COORDINATE) + heightOffset);
        newPath.setEndX(newValues.get(X_COORDINATE) + widthOffset);
        newPath.setEndY(newValues.get(Y_COORDINATE) + heightOffset);
        return newPath;
    }

    private void bindPositions(ObservableList<List<Double>> turtlepos){
        positions = FXCollections.observableArrayList();
        Bindings.bindContentBidirectional(positions, turtlepos);
        addPositionChangeListener(positions);
    }

    private void bindProperties(TurtleData turtle){
        isPenDown = new SimpleBooleanProperty();
        turtleAngle = new SimpleDoubleProperty();
        Bindings.bindBidirectional(isPenDown, turtle.getPenDownProperty());
        Bindings.bindBidirectional(turtleAngle, turtle.directionProperty());
        addAngleChangeListener();
    }

    private void addAngleChangeListener(){
        turtleAngle.addListener( (observable, oldValue, newValue) ->
                turtleView.setRotate((newValue.doubleValue()+ ANGLE_OFFSET)));
    }

}