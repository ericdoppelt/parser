package slogo.Model.Commands.TurtleCommands;

import static java.lang.Math.abs;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class SetHeadingCommand extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private double originalTurtleDirection;
  private double newTurtleDirection;

  public SetHeadingCommand(TurtleData turtle, double degreeAngle) {
    turtleObject = turtle;
    originalTurtleDirection = turtle.getTurtleHeading();
    newTurtleDirection = degreeAngle;
    returnArgValue = abs(newTurtleDirection - originalTurtleDirection);

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
//    System.out.println("turtle heading " + turtleObject.getTurtleHeading());
    turtleObject.setTurtleDirection(newTurtleDirection);
//    System.out.println("turtle heading " + turtleObject.getTurtleHeading());
//    System.out.println("turtle X " + turtleObject.getTurtleX());
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Integer returnArgValue() {
    return (int) this.returnArgValue;
  }

}


