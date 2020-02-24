package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class RightCommand extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private double angleChangeCC;

  public RightCommand(TurtleData turtle, double degreeAngleChange) {
    turtleObject = turtle;
    returnArgValue = degreeAngleChange;
    angleChangeCC = -degreeAngleChange;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {

//    System.out.println("turtle heading " + turtleObject.getTurtleHeading());
    turtleObject.rotateTurtleHeading(angleChangeCC);
//    System.out.println("turtle heading " + turtleObject.getTurtleHeading());
//    System.out.println("turtle X " + turtleObject.getTurtleX());
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }

}


