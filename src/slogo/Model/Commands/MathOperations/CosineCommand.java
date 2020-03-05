package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class CosineCommand extends Command {

  private double returnArgValue;
  private Number amountOfDegrees;

  public CosineCommand(Number degrees) {
    amountOfDegrees = degrees;

   }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    returnArgValue = Math.cos(Math.toRadians(amountOfDegrees.doubleValue()));
    System.out.println(returnArgValue);
    return this.returnArgValue;
  }

}


