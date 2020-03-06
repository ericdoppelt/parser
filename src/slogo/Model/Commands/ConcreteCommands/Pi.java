package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class Pi extends Command {

  private final double returnArgValue = Math.PI;
  private static final int argumentsNeeded = 0;
  private CommandDatabase database;

  public Pi(CommandDatabase data) {
    super(data);
    database = data;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    System.out.println("pi" + returnArgValue);
    return this.returnArgValue;
  }
  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }




}


