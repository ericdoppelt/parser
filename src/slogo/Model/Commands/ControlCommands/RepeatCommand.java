package slogo.Model.Commands.ControlCommands;

import java.util.List;
import java.util.function.Function;
import slogo.Model.Commands.Command;

/**
 * Subclass to create a BackCommand
 *
 * @author Frank Tang
 */
public class RepeatCommand extends Command {

  private double returnArgValue = 0;
  private List<String> linesSubArray;
  private Number amountOfIterations;
  private Function<List<String>, Number> parseTextFunction;
  private Function<List<String>, Number> listEndFunction;




  public RepeatCommand(Number iterations, List<String> commandList, Function<List<String>, Number> parseFunction, Function<List<String>, Number> listFunction) {
    amountOfIterations = iterations;
    parseTextFunction = parseFunction;
    listEndFunction = listFunction;
    linesSubArray = commandList;
  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public Double executeAndReturnValue() {
    List<String> tempList = linesSubArray.subList(linesSubArray.indexOf("["), linesSubArray.size());
    int listEnd = listEndFunction.apply(tempList).intValue();
    linesSubArray = tempList.subList(1, listEnd);
    for(int i = 1; i <= amountOfIterations.intValue(); i++) {
      returnArgValue = parseTextFunction.apply(linesSubArray).doubleValue();
    }
    System.out.println("out of loop");
    System.out.println(this.returnArgValue);
    return returnArgValue;
  }

}


