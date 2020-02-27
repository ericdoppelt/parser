package slogo.Model.Commands.BooleanOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class NotCommand extends Command {
  //moves turtle to an absolute screen position, where (0, 0) is the center of the screen
  //returns the distance turtle moved

  private double returnArgValue;
  private Number firstTerm;
  private Number secondTerm;

  public NotCommand(Number parameterOne, Number parameterTwo) {
    firstTerm = parameterOne;
    secondTerm = parameterTwo;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    if(firstTerm.equals(0) && secondTerm.equals(0)) {
      returnArgValue = 1;
    }
    else {
      returnArgValue = 0;
    }

    System.out.println(returnArgValue);
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Integer returnArgValue() {
    return (int) this.returnArgValue;
  }
}



