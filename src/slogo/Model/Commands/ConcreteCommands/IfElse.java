package slogo.Model.Commands.ConcreteCommands;

import java.util.List;
import java.util.function.Function;
import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

/**
 * Subclass to create a BackCommand
 *
 * @author Frank Tang
 */
public class IfElse extends Command {

  private double returnArgValue = 0;
  private List<String> linesSubArray;
  private Number expression;
  private static final int argumentsNeeded = 1;
  private CommandDatabase database;
  private Function<List<String>, Number> parseTextFunction;
  private Function<List<String>, Number> listEndFunction;



  public IfElse(CommandDatabase data) {
    super(data);
    database = data;

  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public Double executeAndReturnValue() {
    expression = database.getParameterStack().pop();
    parseTextFunction = database.getParseFunction();
    listEndFunction = database.getListFunction();
    linesSubArray = database.getCurrentLineArray();

    List<String> ifCommands = linesSubArray.subList(linesSubArray.indexOf("["), linesSubArray.size());
    int listEnd = listEndFunction.apply(ifCommands).intValue();
    ifCommands = ifCommands.subList(1, listEnd);

    List<String> ifElseCommands = linesSubArray.subList(listEnd + 2, linesSubArray.size());
    ifElseCommands = ifElseCommands.subList(ifElseCommands.indexOf("["), ifElseCommands.size());
    listEnd = listEndFunction.apply(ifElseCommands).intValue();
    ifElseCommands = ifElseCommands.subList(1, listEnd);


    if (expression.doubleValue() > 0) {
      returnArgValue = parseTextFunction.apply(ifCommands).doubleValue();
    }
    else{
      returnArgValue = parseTextFunction.apply(ifElseCommands).doubleValue();
    }

    return returnArgValue;
  }
  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }



}


