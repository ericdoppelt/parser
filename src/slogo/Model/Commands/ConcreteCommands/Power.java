package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class Power extends Command {

  private double returnArgValue;
  private Number baseNumber;
  private Number exponentNumber;


  public Power(Number base, Number exponent) {
    baseNumber = base;
    exponentNumber = exponent;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    returnArgValue = Math.pow(baseNumber.doubleValue(), exponentNumber.doubleValue());
    System.out.println(returnArgValue);
    return this.returnArgValue;
  }

}


