package slogo.Model.Commands.TurtleQueries;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class HeadingCommand extends Command {

  private double returnArgValue;

  public HeadingCommand(TurtleData turtleObject) {
    returnArgValue = turtleObject.getTurtleHeading();
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    System.out.println(returnArgValue);
    return this.returnArgValue;
  }

}


