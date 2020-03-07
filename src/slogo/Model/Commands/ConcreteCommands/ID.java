package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class ID extends Command {
  private static final int argumentsNeeded = 0;
  private CommandDatabase database;


  public ID(CommandDatabase data) {
    super(data);
    database = data;


  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Integer executeAndReturnValue() {
    System.out.println("Current Turtle ID: " + database.getTurtle().getTurtleID());
    return Integer.parseInt(database.getTurtle().getTurtleID());
  }

  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }
}



