package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class IsPenDown extends Command {

  private double returnArgValue;

  public IsPenDown(TurtleData turtleObject) {
    returnArgValue = turtleObject.getPenStatus();
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


