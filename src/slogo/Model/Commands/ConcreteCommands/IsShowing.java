package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class IsShowing extends Command {

  private double returnArgValue;

  public IsShowing(TurtleData turtleObject) {
    returnArgValue = turtleObject.getTurtleVisibility();
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Integer executeAndReturnValue() {
    System.out.println(returnArgValue);
    return (int) this.returnArgValue;
  }


}


