package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class Right extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private Number degreesChanged;

  public Right(TurtleData turtle, Number degreeAngleChange) {
    turtleObject = turtle;
    degreesChanged = degreeAngleChange;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    returnArgValue = degreesChanged.doubleValue();
    System.out.println("turtle heading " + turtleObject.getTurtleHeading());
    turtleObject.rotateTurtleHeading(-degreesChanged.doubleValue());
    System.out.println("turtle heading " + turtleObject.getTurtleHeading());
//    System.out.println("turtle X " + turtleObject.getTurtleX());
//    System.out.println("turtle Y " + turtleObject.getTurtleY());
    return this.returnArgValue;

  }


}


