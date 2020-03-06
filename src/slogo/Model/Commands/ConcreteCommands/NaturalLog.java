package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class NaturalLog extends Command {

  private double returnArgValue;
  private Number firstTerm;
  private static final int argumentsNeeded = 1;
  private CommandDatabase database;


  public NaturalLog(CommandDatabase data) {
    super(data);
    database = data;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    firstTerm = database.getParameterStack().pop();
    returnArgValue = Math.log(firstTerm.doubleValue());
    System.out.println(returnArgValue);
    return this.returnArgValue;
  }
  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }




}


