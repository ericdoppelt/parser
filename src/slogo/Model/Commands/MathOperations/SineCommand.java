package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class SineCommand extends Command {

  private double returnArgValue;
  private Number amountOfDegrees;


  public SineCommand(Number degrees) {
    amountOfDegrees = degrees;

   }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    returnArgValue = Math.sin(Math.toRadians(amountOfDegrees.doubleValue()));
    System.out.println(returnArgValue);
  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }


}


