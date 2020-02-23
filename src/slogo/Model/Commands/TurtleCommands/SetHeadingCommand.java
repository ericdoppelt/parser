package slogo.Model.Commands.TurtleCommands;

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
  private static final int fullRevolution = 360;

  public SetHeadingCommand(TurtleData turtle, double degreeAngle) {
    turtleObject = turtle;
    originalTurtleDirection = turtle.getTurtleHeading();
    newTurtleDirection = degreeAngle;
    //returnArgValue = min(abs(newTurtleDirection - originalTurtleDirection), fullRevolution - abs(newTurtleDirection - originalTurtleDirection));
    returnArgValue = newTurtleDirection - originalTurtleDirection;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    //System.out.println("turtle heading " + turtleObject.getTurtleHeading());
    turtleObject.setTurtleDirection(newTurtleDirection);
    //System.out.println("turtle heading " + turtleObject.getTurtleHeading());
    //System.out.println(returnArgValue);
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Integer returnArgValue() {
    return (int) this.returnArgValue;
  }

}


