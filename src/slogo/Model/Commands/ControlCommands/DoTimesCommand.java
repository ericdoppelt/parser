package slogo.Model.Commands.ControlCommands;

import java.util.List;
import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;
import slogo.Model.ModelParser;

/**
 * Subclass to create a BackCommand
 *
 * @author Frank Tang
 */
public class DoTimesCommand extends Command {

  private double returnArgValue = 0;
  private List<String> linesSubArray;
  private List<String> currentSubList;
  private List<String> commandSubList;
  private int currentIndex;
  private Number variableLimit;
  private String variableName;
  private ModelParser parser;
  private CommandDatabase commandDatabase;



  public DoTimesCommand(ModelParser modelParser, CommandDatabase database) {
    parser = modelParser;
    commandDatabase = database;

  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public void execute() {
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
    commandSubList = currentSubList.subList(listEnd + 1, currentSubList.size());
    linesSubArray = currentSubList.subList(listStart + 1, listEnd);
    System.out.println("test" + linesSubArray);

    variableName = linesSubArray.get(0);
    variableLimit = Double.parseDouble(linesSubArray.get(1));

    int commandListEnd = parser.findListEnd(commandSubList);
    commandSubList = commandSubList.subList(1, commandListEnd);
    System.out.println("Commandsublist " + commandSubList);

    for(int i = 1; i <= variableLimit.doubleValue(); i++){
      commandDatabase.addToVariables(variableName, i);
      parser.parseText(commandSubList);
    }
  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }

}


