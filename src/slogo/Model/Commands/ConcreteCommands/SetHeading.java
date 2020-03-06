package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class SetHeading extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private double originalTurtleDirection;
  private Number newTurtleDirection;
  private static final int fullRevolution = 360;

  public SetHeading(TurtleData turtle, Number degreeAngle) {
    turtleObject = turtle;
    originalTurtleDirection = turtle.getTurtleHeading();
    newTurtleDirection = degreeAngle;
    //returnArgValue = min(abs(newTurtleDirection - originalTurtleDirection), fullRevolution - abs(newTurtleDirection - originalTurtleDirection));

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    returnArgValue = newTurtleDirection.doubleValue() - originalTurtleDirection;
    //System.out.println("turtle heading " + turtleObject.getTurtleHeading());
    turtleObject.setTurtleDirection(newTurtleDirection.doubleValue());
    //System.out.println("turtle heading " + turtleObject.getTurtleHeading());
    //System.out.println(returnArgValue);
//    System.out.println("turtle Y " + turtleObject.getTurtleY());
    return this.returnArgValue;

  }

}


