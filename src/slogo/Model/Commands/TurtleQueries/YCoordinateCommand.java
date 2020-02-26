package slogo.Model.Commands.TurtleQueries;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class YCoordinateCommand extends Command {

  private double returnArgValue;

  public YCoordinateCommand(TurtleData turtleObject) {
    returnArgValue = turtleObject.getTurtleY();
   }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    System.out.println(returnArgValue);
  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }


}


