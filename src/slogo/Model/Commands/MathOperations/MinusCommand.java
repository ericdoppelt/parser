package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class MinusCommand extends Command {

  private double returnArgValue;
  private Number firstTerm;


  public MinusCommand(Number parameterOne) {
    firstTerm = parameterOne;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    returnArgValue = -firstTerm.doubleValue();
//    System.out.println(turtleObject.getTurtleHeading());
  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }


}


