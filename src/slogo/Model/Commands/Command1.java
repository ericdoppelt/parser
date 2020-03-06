package slogo.Model.Commands;

import slogo.Model.CommandInfrastructure.CommandDatabase;

/**
 * Abstract parent class that acts as the base for all the commands in the program.
 *
 * @author Frank Tang
 */
public abstract class Command1 {
  private CommandDatabase data;
  /**
   * Superconstructor for a Turtle Command
   */
  public Command1(CommandDatabase database) {
    data = database;
  }

  public abstract Number executeAndReturnValue();

  public abstract int getArgumentsNeeded();

}
