package slogo.Model.Commands.ControlCommands;

import java.util.List;
import slogo.Model.Commands.Command;
import slogo.Model.ModelParser;

/**
 * Subclass to create a BackCommand
 *
 * @author Frank Tang
 */
public class RepeatCommand extends Command {

  private double returnArgValue = 0;
  private List<String> linesSubArray;
  private List<String> currentSubList;
  private int currentIndex;
  private Number amountOfIterations;
  private ModelParser parser;



  public RepeatCommand(Number iterations, ModelParser modelParser) {
    parser = modelParser;
    amountOfIterations = iterations;

  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public Double executeAndReturnValue() {
    //currentIndex = parser.getCurrentLinesIndex();
//    System.out.println("current index " + currentIndex);
    linesSubArray = parser.getLinesArray();
    System.out.println("shortened array from parser " + linesSubArray);

    currentSubList = linesSubArray.subList(1, linesSubArray.size());
    System.out.println("currentSublist " + currentSubList);
    int listStart = currentSubList.indexOf("[");
    currentSubList = currentSubList.subList(listStart, currentSubList.size());
//    System.out.println("listStart" + listStart);
    int listEnd = parser.findListEnd(currentSubList) + listStart;
    linesSubArray = currentSubList.subList(listStart + 1, listEnd);
    System.out.println("test" + linesSubArray);

    for(int i = 0; i < amountOfIterations.intValue(); i++){
      System.out.println("linessub "  + linesSubArray);
      parser.parseText(linesSubArray);

    }
    return this.returnArgValue;
  }

}


