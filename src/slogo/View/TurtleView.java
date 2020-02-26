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
import javafx.scene.paint.Paint;
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

    private static final String DEFAULT_IMAGE_PATH = "resources/languages/perfectTurtle.png";

    private SimpleBooleanProperty isPenDown;
    private SimpleDoubleProperty turtleAngle;
    private List<Double> previousPosition;
    private ObservableList<List<Double>> positions;
    private Pane myBackground;
    private ImageView turtleView;

    private double heightOffset;
    private double widthOffset;
    private ObjectProperty<Color> penColor;
    private final Color DEFAULT_PEN_COLOR = Color.TURQUOISE;
    private double lineWidth = 2.0;

    /**
     * Constructor used to build a new Turtle Display. One per backend Turtle.
     * @param turtle Backend Turtle To Bind
     * @param pane Platform to display Turtle
     */
    public TurtleView(TurtleData turtle, Pane pane){
        myBackground = pane;
        System.out.println(turtle.getTurtleX());
        setUpTurtle(turtle, pane);
        previousPosition = setUpInitialPosition();
        bindPositions(turtle.getCoordHistory());
        bindProperties(turtle);

        penColor = new SimpleObjectProperty<Color>(DEFAULT_PEN_COLOR);
        penColor.addListener(new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
                System.out.println("CHANGE");
                System.out.println(oldValue);
                System.out.println(newValue);
            }
        });
    }

    private void setUpTurtle(TurtleData turtle, Pane pane) {
        turtleView = new ImageView(getImage(DEFAULT_IMAGE_PATH));
        turtleView.xProperty().bind(turtle.getTurtleXProperty());
        turtleView.yProperty().bind(turtle.getTurtleYProperty());
        turtleView.setRotate(turtle.getTurtleHeading() + ANGLE_OFFSET);
        pane.getChildren().add(turtleView);
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
            previousPosition = currentPosition;
        });
    }

    private void addPath(Line newPath){
        System.out.println("FILLCOLOR" + penColor.getValue());
        newPath.setFill((Paint)penColor.getValue());
        System.out.println(penColor.getValue());
        
        newPath.setStrokeWidth(lineWidth);
        myBackground.getChildren().add(newPath);
        System.out.println(penColor.getValue());
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
        turtleView.visibleProperty().bind(turtle.turtleVisibility());
        addAngleChangeListener();
    }

    private void addAngleChangeListener(){
        turtleAngle.addListener( (observable, oldValue, newValue) ->
                turtleView.setRotate((newValue.doubleValue()+ ANGLE_OFFSET)));
    }

    /**
     * Method that allows setting a new image for the turtle
     * @param newImage: New Image File to be used to replace default turtle image
     */
    public void setNewImage(File newImage){
        turtleView.setImage(getImage(newImage.toURI().toString()));
    }

    /**
     * Turtle Method returns Color property to bind for automatic Pen Color changing
     * @return Color Property
     */
    public ObjectProperty<Color> getPenColorProperty(){
        return penColor;
    }

}