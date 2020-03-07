package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class Turtles extends Command {
  private static final int argumentsNeeded = 0;
  private CommandDatabase database;


  public Turtles(CommandDatabase data) {
    super(data);
    database = data;


  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Integer executeAndReturnValue() {
    System.out.println("Turtles made: " + database.getTurtleList().size());
    return database.getTurtleList().size();
  }

  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }
}



