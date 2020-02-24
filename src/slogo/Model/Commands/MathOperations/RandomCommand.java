package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class RandomCommand extends Command {

  private double returnArgValue;

  public RandomCommand(double max) {
    double randomValue = (int) Math.random() * max;
    returnArgValue = randomValue;
   }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    System.out.println(returnArgValue);
  }

  @Override
  public Integer returnArgValue() {
    return (int) this.returnArgValue;
  }


}


