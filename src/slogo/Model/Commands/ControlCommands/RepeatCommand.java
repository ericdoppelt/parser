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
  private double amountOfIterations;
  private ModelParser parser;



  public RepeatCommand(double iterations, ModelParser modelParser) {
    parser = modelParser;
    amountOfIterations = iterations;

  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public void execute() {
    currentIndex = parser.getCurrentLinesIndex();
    System.out.println("current index " + currentIndex);
    linesSubArray = parser.getLinesArray();
    System.out.println("BigArray " + linesSubArray);

    currentSubList = linesSubArray.subList(currentIndex + 1, linesSubArray.size());
    System.out.println("currentSublist " + currentSubList);
    currentSubList = currentSubList.subList(currentSubList.indexOf("["), currentSubList.size());
    int listStart = currentSubList.indexOf("[");
    System.out.println("listStart" + listStart);
    int listEnd = findListEnd(currentSubList) + listStart;
    linesSubArray = currentSubList.subList(listStart + 1, listEnd);
    System.out.println("test" + linesSubArray);

    for(int i = 0; i < amountOfIterations; i++){
      parser.parseText(linesSubArray);
    }
  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }

  public int findListEnd(List<String> listToCheck){
    int listStartCounter = 0;
    int listEndCounter = 0;
    for(int i = 0; i < listToCheck.size(); i++){
    //  System.out.println(currentSubList.get(i));
      if(listToCheck.get(i).equals("]")){
        listEndCounter++;
      }
      else if(listToCheck.get(i).equals("[")){
        listStartCounter++;
        //System.out.println(listStartCounter);
      }
      if(listEndCounter == listStartCounter){
//        System.out.println("index " + (i + currentIndex));
        return i;
      }
    }
    return 0;
  }

}


