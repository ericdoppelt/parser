package slogo;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import slogo.Model.TurtleData;

/**
 * Turtle View is a class that redraws the current turtle.
 * @author erikgregorio
 */
public class TurtleView {

    private Circle myTurtle;

    public TurtleView(TurtleData turtle){
        myTurtle = new Circle(10);
        myTurtle.centerXProperty().bind(turtle.getTurtleXProperty());
        myTurtle.centerYProperty().bind(turtle.getTurtleYProperty());
    }

    public Node getTurtle(){
        return myTurtle;
    }

}
