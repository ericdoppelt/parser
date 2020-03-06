package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class PiCommand extends Command {

  private final double returnArgValue = Math.PI;

  public PiCommand() {
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    System.out.println("pi" + returnArgValue);
    return this.returnArgValue;
  }


}


