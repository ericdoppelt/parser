package slogo;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import slogo.Model.TurtleData;

/**
 * Turtle View is a class that redraws the current turtle.
 * @author erikgregorio
 */
public class TurtleView {

    private boolean isPenDown;
    private Circle myTurtle;

    public TurtleView(TurtleData turtle){
        myTurtle = new Circle(10);
        myTurtle.centerXProperty().bind(turtle.getTurtleXProperty());
        myTurtle.centerYProperty().bind(turtle.getTurtleYProperty());
        addListeners();
    }

    private void addListeners(){
        myTurtle.centerXProperty().addListener((observable, oldValue, newValue) -> {
            if(isPenDown) addNewX((double) oldValue,(double) newValue);
        });
        myTurtle.centerYProperty().addListener((observable, oldValue, newValue) -> {
            if(isPenDown) addNewY((double) oldValue,(double) newValue);
        });
    }

    private void addNewX(Double oldX, Double newX){


    }
    private void addNewY(Double oldX, Double newX){

    }


    public Node getTurtle(){
        return new Pane(myTurtle);
    }

}
