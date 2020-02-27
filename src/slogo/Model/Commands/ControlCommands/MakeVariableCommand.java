package slogo.Model.Commands.ControlCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

/**
 * Subclass to create a BackCommand
 *
 * @author Frank Tang
 */
public class MakeVariableCommand extends Command {

  private double returnArgValue = 0;
  private Number expression;
  private String variable;
  private CommandDatabase dataBase;



  public MakeVariableCommand(String variableName, Number parameterOne, CommandDatabase commandDatabase) {
    dataBase = commandDatabase;
    variable = variableName;
    expression = parameterOne;
    System.out.println("test" + parameterOne);
  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public void execute() {
    System.out.println(variable);
    System.out.println(expression.doubleValue());
//    dataBase.getVariables().putIfAbsent(variable, expression.doubleValue());
    dataBase.addToVariables(variable, expression.doubleValue());
    returnArgValue = expression.doubleValue();

  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }

}


