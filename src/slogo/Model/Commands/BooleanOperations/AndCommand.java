package slogo.Model.Commands.BooleanOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class AndCommand extends Command {
  //moves turtle to an absolute screen position, where (0, 0) is the center of the screen
  //returns the distance turtle moved

  private double returnArgValue;

  public AndCommand(double parameterOne, double parameterTwo) {
    if(parameterOne != 0 && parameterTwo != 0) {
      returnArgValue = 1;
    }
    else {
      returnArgValue = 0;
    }
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {

    //System.out.println(returnArgValue);
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Integer returnArgValue() {
    return (int) this.returnArgValue;
  }
}



