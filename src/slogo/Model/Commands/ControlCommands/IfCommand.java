package slogo.Model.Commands.ControlCommands;

import java.util.List;
import slogo.Model.Commands.Command;
import slogo.Model.ModelParser;

/**
 * Subclass to create a BackCommand
 *
 * @author Frank Tang
 */
public class IfCommand extends Command {

  private double returnArgValue = 0;
  private List<String> linesSubArray;
  private List<String> currentSubList;
  private int currentIndex;
  private Number expression;
  private ModelParser parser;



  public IfCommand(Number parameterOne, ModelParser modelParser) {
    parser = modelParser;
    expression = parameterOne;

  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public void execute() {
    if (expression.doubleValue() > 0) {
      currentIndex = parser.getCurrentLinesIndex();
//    System.out.println("current index " + currentIndex);
      linesSubArray = parser.getLinesArray();
//    System.out.println("BigArray " + linesSubArray);

      currentSubList = linesSubArray.subList(currentIndex + 1, linesSubArray.size());
//    System.out.println("currentSublist " + currentSubList);
      currentSubList = currentSubList.subList(currentSubList.indexOf("["), currentSubList.size());
      int listStart = currentSubList.indexOf("[");
//    System.out.println("listStart" + listStart);
      int listEnd = parser.findListEnd(currentSubList) + listStart;
      linesSubArray = currentSubList.subList(listStart + 1, listEnd);
      System.out.println("test" + linesSubArray);
      parser.parseText(linesSubArray);
    }
  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }

}


