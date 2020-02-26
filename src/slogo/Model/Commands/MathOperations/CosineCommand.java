package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class CosineCommand extends Command {

  private double returnArgValue;

  public CosineCommand(double degrees) {
    returnArgValue = Math.cos(Math.toRadians(degrees));
   }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    System.out.println(returnArgValue);
  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }


}


