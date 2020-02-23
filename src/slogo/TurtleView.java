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
        myTurtle = new Circle(5);
        myTurtle.centerXProperty().bind(turtle.getTurtleXProperty());
        myTurtle.centerYProperty().bind(turtle.getTurtleYProperty());
    }

    public Node getTurtle(){
        return myTurtle;
    }

    public void printXY(){
        System.out.println(myTurtle.getCenterX());
        System.out.println(myTurtle.getCenterY());
    }
}
