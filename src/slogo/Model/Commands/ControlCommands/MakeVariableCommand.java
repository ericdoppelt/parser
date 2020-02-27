package slogo.Model.Commands.ControlCommands;

import java.util.Map;
import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

/**
 * Subclass to create a BackCommand
 *
 * @author Frank Tang
 */
public class MakeVariableCommand extends Command {

  private double returnArgValue = 0;
  private double expression;
  private String variable;
  private Map<String, Number> variableMap;



  public MakeVariableCommand(String variableName, double parameterOne, CommandDatabase commandDatabase) {
    variableMap = commandDatabase.getVariableMap();
    variable = variableName;
    expression = parameterOne;
  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public void execute() {
    System.out.println(variable);
    System.out.println(expression);
    variableMap.putIfAbsent(variable, expression);
    variableMap.put(variable, expression);

    returnArgValue = expression;



  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }

}


