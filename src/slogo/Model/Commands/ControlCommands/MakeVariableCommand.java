//package slogo.Model.Commands.ControlCommands;
//
//import java.util.List;
//import slogo.Model.Commands.Command;
//import slogo.Model.ModelParser;
//
///**
// * Subclass to create a BackCommand
// *
// * @author Frank Tang
// */
//public class MakeVariableCommand extends Command {
//
//  private double returnArgValue = 0;
//  private List<String> linesSubArray;
//  private int currentIndex;
//  private double amountOfIterations;
//  private ModelParser parser;
//
//
//
//  public MakeVariableCommand(String name, double ) {
//    parser = modelParser;
//    amountOfIterations = iterations;
//
//  }
//
//  /**
//   * Moves the turtle backwards by a pixel amount.
//   */
//  @Override
//  public void execute() {
//    currentIndex = parser.getCurrentLinesIndex();
//    System.out.println(currentIndex);
//    List<String> parserLineArray = parser.getLinesArray();
//    System.out.println("BigArray " + parserLineArray);
//
//    List<String> currentSubList = parserLineArray.subList(currentIndex, parserLineArray.size());
//
//    int listStart = currentSubList.indexOf("[") + 1;
//    int listEnd = currentSubList.indexOf("]");
//    linesSubArray = currentSubList.subList(listStart, listEnd);
//    System.out.println("test" + linesSubArray);
//
//    for(int i = 0; i < amountOfIterations; i++){
//      parser.parseText(linesSubArray);
//    }
//  }
//
//  @Override
//  public Double returnArgValue() {
//    return this.returnArgValue;
//  }
//
//}
//
//
