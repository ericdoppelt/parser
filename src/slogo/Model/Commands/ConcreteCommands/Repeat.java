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
public class Repeat extends Command {

  private double returnArgValue = 0;
  private static final int argumentsNeeded = 1;
  private List<String> linesSubArray;
  private Number amountOfIterations;
  private CommandDatabase database;
  private Function<List<String>, Number> parseTextFunction;
  private Function<List<String>, Number> listEndFunction;

  public Repeat(CommandDatabase data) {
    super(data);
    database = data;
  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public Double executeAndReturnValue() {
    amountOfIterations = database.getParameterStack().pop();
    parseTextFunction = database.getParseFunction();
    listEndFunction = database.getListFunction();
    linesSubArray = database.getCurrentLineArray();

    List<String> tempList = linesSubArray.subList(linesSubArray.indexOf("["), linesSubArray.size());
    int listEnd = listEndFunction.apply(tempList).intValue();
    linesSubArray = tempList.subList(1, listEnd);
    for(int i = 1; i <= amountOfIterations.intValue(); i++) {
      returnArgValue = parseTextFunction.apply(linesSubArray).doubleValue();
    }
    return returnArgValue;
  }

  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }

}


