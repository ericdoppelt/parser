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
  private double originalArgValue;

  public RightCommand(TurtleData turtle, double degreeAngleChange) {
    turtleObject = turtle;
    originalArgValue = degreeAngleChange;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    double angleChangeCC = -originalArgValue;
    System.out.println("turtle heading " + turtleObject.getTurtleHeading());
    turtleObject.rotateTurtleHeading(angleChangeCC);
    System.out.println("turtle heading " + turtleObject.getTurtleHeading());
//    System.out.println("turtle X " + turtleObject.getTurtleX());
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Integer returnArgValue() {
    return (int) this.originalArgValue;
  }

}


