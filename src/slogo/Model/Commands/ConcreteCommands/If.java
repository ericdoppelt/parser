package slogo.Model.Commands.ConcreteCommands;

import java.util.List;
import java.util.function.Function;
import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;
import slogo.Model.ModelParser;

/**
 * Subclass to create a BackCommand
 *
 * @author Frank Tang
 */
public class If extends Command {

  private double returnArgValue = 0;
  private List<String> linesSubArray;
  private List<String> currentSubList;
  private int currentIndex;
  private Number expression;
  private ModelParser parser;
  private static final int argumentsNeeded = 1;
  private CommandDatabase database;
  private Function<List<String>, Number> parseTextFunction;
  private Function<List<String>, Number> listEndFunction;


  public If(CommandDatabase data) {
    super(data);
    database = data;

  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public Double executeAndReturnValue() {
    expression = database.getParameterStack().peek();
    parseTextFunction = database.getParseFunction();
    listEndFunction = database.getListFunction();
    linesSubArray = database.getCurrentLineArray();

    if (expression.doubleValue() > 0) {
      List<String> tempList = linesSubArray.subList(linesSubArray.indexOf("["), linesSubArray.size());
      int listEnd = listEndFunction.apply(tempList).intValue();
      linesSubArray = tempList.subList(1, listEnd);

      returnArgValue = parseTextFunction.apply(linesSubArray).doubleValue();
    }

    return returnArgValue;

  }
  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }




}


