package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class LeftCommand extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private double angleChangeCCW;

  public LeftCommand(TurtleData turtle, double degreeAngleChange) {
    turtleObject = turtle;
    returnArgValue = degreeAngleChange;
    angleChangeCCW = degreeAngleChange;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    turtleObject.rotateTurtleHeading(angleChangeCCW);
    System.out.println(turtleObject.getTurtleHeading());
//    System.out.println(turtleObject.getTurtleHeading());
  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }


}


