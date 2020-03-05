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
  private Number degreesChanged;

  public LeftCommand(TurtleData turtle, Number degreeAngleChange) {
    turtleObject = turtle;
    degreesChanged = degreeAngleChange;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    returnArgValue = degreesChanged.doubleValue();
    System.out.println(turtleObject.getTurtleHeading());
    turtleObject.rotateTurtleHeading(degreesChanged.doubleValue());
    System.out.println(turtleObject.getTurtleHeading());
    return this.returnArgValue;
  }


}


