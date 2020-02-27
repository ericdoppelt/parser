package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class PowerCommand extends Command {

  private double returnArgValue;
  private Number baseNumber;
  private Number exponentNumber;



  public PowerCommand(Number base, Number exponent) {
    baseNumber = base;
    exponentNumber = exponent;
   }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    returnArgValue = Math.pow(baseNumber.doubleValue(), exponentNumber.doubleValue());
    System.out.println(returnArgValue);
  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }


}


