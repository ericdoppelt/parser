package slogo.Model.Commands.TurtleCommands;

import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class RightCommand extends TurtleCommand {

  private TurtleData turtleObject;
  private double turtleHeading;
  private double angleChangeCC;


  public RightCommand(TurtleData turtle, double degreeAngleChange) {
    turtleObject = turtle;
    turtleHeading = turtleObject.getTurtleHeading();
    angleChangeCC = -degreeAngleChange;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  public void execute() {
    System.out.println(turtleObject.getTurtleHeading());
    turtleObject.rotateTurtleHeading(angleChangeCC);
    System.out.println(turtleObject.getTurtleHeading());
  }

}


