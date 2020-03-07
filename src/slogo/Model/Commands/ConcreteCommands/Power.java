package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class Power extends Command {

  private double returnArgValue;
  private Number baseNumber;
  private Number exponentNumber;
  private static final int argumentsNeeded = 2;
  private CommandDatabase database;



  public Power(CommandDatabase data) {
    super(data);
    database = data;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    baseNumber = database.getParameterStack().peek();
    exponentNumber = database.getParameterStack().peek();
    returnArgValue = Math.pow(baseNumber.doubleValue(), exponentNumber.doubleValue());
    System.out.println(returnArgValue);
    return this.returnArgValue;
  }
  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }



}


