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
  private double originalArgValue;

  public LeftCommand(TurtleData turtle, double degreeAngleChange) {
    turtleObject = turtle;
    originalArgValue = degreeAngleChange;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    double angleChangeCCW = originalArgValue;
    turtleObject.rotateTurtleHeading(angleChangeCCW);
//    System.out.println(turtleObject.getTurtleHeading());
  }

  @Override
  public Integer returnArgValue() {
    return (int) this.originalArgValue;
  }


}


