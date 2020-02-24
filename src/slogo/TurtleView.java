package slogo;


import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import slogo.Model.TurtleData;
import java.util.ArrayList;
import java.util.List;

/**
 * Turtle View is a class that redraws the current turtle.
 * @author erikgregorio
 */
public class TurtleView {
    public static final int XCOORDINATE = 0;
    public static final int YCOORDINATE = 1;

    private boolean isPenDown = true;
    private Circle myTurtle;
    private List<Double> previousPosition;
    private ObservableList<List<Double>> positions;
    private Pane myBackground;

    public TurtleView(TurtleData turtle, Pane pane){
        myBackground = pane;
        myTurtle = setUpTurtle(turtle);
        previousPosition = setUpInitialPosition();
        pane.getChildren().add(myTurtle);
        positions = FXCollections.observableArrayList();
        bindList(turtle.getCoordHistory());
    }

    private Circle setUpTurtle(TurtleData turtle){
        Circle newTurtle = new Circle(10);
        newTurtle.centerXProperty().bind(turtle.getTurtleXProperty());
        newTurtle.centerYProperty().bind(turtle.getTurtleYProperty());
        return newTurtle;
    }

    private List<Double> setUpInitialPosition(){
        ArrayList<Double > myList = new ArrayList<>();
        myList.add(myTurtle.getCenterX());
        myList.add(myTurtle.getCenterY());
        return myList;
    }

    private void addListener(ObservableList<List<Double>> x){
        x.addListener((ListChangeListener<List<Double>>) c -> {
            List<Double> currentPosition = c.getList().get(c.getList().size()-1);
            if(isPenDown) addPath(getNewLine(previousPosition, currentPosition));
            previousPosition = currentPosition;
        });
    }

    private void addPath(Line newPath){
        myBackground.getChildren().add(newPath);
    }

    private Line getNewLine(List<Double> oldValues, List<Double> newValues){
        Line newPath = new Line();
        newPath.setStartX(oldValues.get(XCOORDINATE));
        newPath.setStartY(oldValues.get(YCOORDINATE));
        newPath.setEndX(newValues.get(XCOORDINATE));
        newPath.setEndY(newValues.get(YCOORDINATE));
        return newPath;
    }

    public Node getTurtle(){
        return (myTurtle);
    }

    private void bindList(ObservableList<List<Double>> turtlepos){
        Bindings.bindContentBidirectional(positions, turtlepos);
        addListener(positions);
    }

}
