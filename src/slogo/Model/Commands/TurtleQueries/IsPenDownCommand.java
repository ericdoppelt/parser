package slogo.Model.Commands.TurtleQueries;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class IsPenDownCommand extends Command {

  private double returnArgValue;

  public IsPenDownCommand(TurtleData turtleObject) {
    returnArgValue = turtleObject.getPenStatus();
   }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    System.out.println(returnArgValue);
  }

  @Override
  public Integer returnArgValue() {
    return (int) this.returnArgValue;
  }


}


