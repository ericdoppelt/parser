//package slogo.Model.Commands.ConcreteCommands;
//
//import java.util.List;
//import slogo.Model.CommandInfrastructure.CommandDatabase;
//import slogo.Model.Commands.Command;
//import slogo.Model.ModelParser;
//
///**
// * Subclass to create a BackCommand
// *
// * @author Frank Tang
// */
//public class IfElse extends Command {
//
//  private double returnArgValue = 0;
//  private List<String> linesSubArray;
//  private List<String> currentSubList;
//  private List<String> ifElseSubList;
//  private int currentIndex;
//  private Number expression;
//  private ModelParser parser;
//  private static final int argumentsNeeded = 1;
//  private CommandDatabase database;
//
//
//
//  public IfElse(CommandDatabase data) {
//    super(data);
//    database = data;
//
//  }
//
//  /**
//   * Moves the turtle backwards by a pixel amount.
//   */
//  @Override
//  public Double executeAndReturnValue() {
//    parser = modelParser;
//    expression = database.getParameterOne();
//
//    currentIndex = parser.getCurrentLinesIndex();
////    System.out.println("current index " + currentIndex);
//      linesSubArray = parser.getLinesArray();
////    System.out.println("BigArray " + linesSubArray);
//
//      currentSubList = linesSubArray.subList(currentIndex + 1, linesSubArray.size());
////    System.out.println("currentSublist " + currentSubList);
//      currentSubList = currentSubList.subList(currentSubList.indexOf("["), currentSubList.size());
//      int listStart = currentSubList.indexOf("[");
////    System.out.println("listStart" + listStart);
//      int listEnd = parser.findListEnd(currentSubList) + listStart;
//      ifElseSubList = currentSubList.subList(listEnd + 1, currentSubList.size());
//      linesSubArray = currentSubList.subList(listStart + 1, listEnd);
//      System.out.println("test" + linesSubArray);
//
//
//      int commandListEnd = parser.findListEnd(ifElseSubList);
//      ifElseSubList = ifElseSubList.subList(1, commandListEnd);
//      System.out.println("ifelseSublist " + ifElseSubList);
//    if (expression.doubleValue() > 0) {
//      parser.parseText(linesSubArray);
//    }
//    else{
//      parser.parseText(ifElseSubList);
//    }
//    return this.returnArgValue;
//  }
//  @Override
//  public int getArgumentsNeeded(){
//    return this.argumentsNeeded;
//  }
//
//
//
//}
//
//
