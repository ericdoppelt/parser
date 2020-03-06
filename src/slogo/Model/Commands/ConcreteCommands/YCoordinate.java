package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class YCoordinate extends Command {

  private double returnArgValue;

  public YCoordinate(TurtleData turtleObject) {
    returnArgValue = turtleObject.getTurtleY();
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


