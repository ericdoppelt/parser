package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class RandomCommand extends Command {

  private double returnArgValue;
  private Number maxRange;


  public RandomCommand(Number max) {
    maxRange = max;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Integer executeAndReturnValue() {
    double randomValue = (int) Math.random() * maxRange.doubleValue();
    returnArgValue = randomValue;
    System.out.println(returnArgValue);
    return (int) this.returnArgValue;
  }

}


