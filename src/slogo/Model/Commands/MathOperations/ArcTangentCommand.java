package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class ArcTangentCommand extends Command {

  private double returnArgValue;
  private Number amountOfDegrees;


  public ArcTangentCommand(Number degrees) {
    amountOfDegrees = degrees;

   }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    returnArgValue = Math.atan(Math.toRadians(amountOfDegrees.doubleValue()));
    System.out.println(returnArgValue);
    return this.returnArgValue;
  }

}


