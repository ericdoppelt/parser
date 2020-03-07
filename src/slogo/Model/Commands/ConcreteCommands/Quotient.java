package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

/**
 * Subclass to create a Sum Command
 *
 * @author Frank Tang
 */
public class Quotient extends Command {

  private Number firstTerm;
  private Number secondTerm;
  private double returnArgValue;
  private static final int argumentsNeeded = 2;
  private CommandDatabase database;


  public Quotient(CommandDatabase data) {
    super(data);
    database = data;

  }


  /**
   * Moves the turtle forward by a pixel amount.
   */
  @Override
  public Double executeAndReturnValue() {
    firstTerm = database.getParameterStack().pop();
    secondTerm = database.getParameterStack().pop();
    returnArgValue = firstTerm.doubleValue() / secondTerm.doubleValue();
    return this.returnArgValue;

  }
  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }


}


