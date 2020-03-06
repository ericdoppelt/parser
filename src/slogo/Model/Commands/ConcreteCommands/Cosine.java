package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class Cosine extends Command {

  private double returnArgValue;
  private Number amountOfDegrees;

  public Cosine(Number degrees) {
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


