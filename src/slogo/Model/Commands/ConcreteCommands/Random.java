package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class Random extends Command {

  private double returnArgValue;
  private Number maxRange;
  private static final int argumentsNeeded = 1;
  private CommandDatabase database;


  public Random(CommandDatabase data) {
    super(data);
    database = data;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Integer executeAndReturnValue() {
    maxRange = database.getParameterStack().pop();
    double randomValue = (int) Math.random() * maxRange.doubleValue();
    returnArgValue = randomValue;
    System.out.println(returnArgValue);
    return (int) this.returnArgValue;
  }
  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }


}


