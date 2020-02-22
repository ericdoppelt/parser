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
  private double angleChangeCC;


  public RightCommand(TurtleData turtle, double degreeAngleChange) {
    turtleObject = turtle;
    angleChangeCC = -degreeAngleChange;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    turtleObject.rotateTurtleHeading(angleChangeCC);
//    System.out.println(turtleObject.getTurtleHeading());
  }

  @Override
  public Integer returnArgValue() {
    return (int) this.angleChangeCC;
  }

}


