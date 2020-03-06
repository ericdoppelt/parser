//package slogo.Model.Commands.ConcreteCommands;
//
//import slogo.Model.CommandInfrastructure.CommandDatabase;
//import slogo.Model.Commands.Command;
//
///**
// * Subclass to create a Sum Command
// *
// * @author Frank Tang
// */
//public class Sum extends Command {
//
//
//  private Number firstTerm;
//  private Number secondTerm;
//  private double returnArgValue;
//  private static final int argumentsNeeded = 2;
//  private CommandDatabase database;
//
//
//
//  public Sum(CommandDatabase data) {
//    super(data);
//    database = data;
//    firstTerm = parameterOne;
//    secondTerm = parameterTwo;
//  }
//
//
//  /**
//   * Moves the turtle forward by a pixel amount.
//   */
//  @Override
//  public Double executeAndReturnValue() {
//    returnArgValue = firstTerm.doubleValue() + secondTerm.doubleValue();
////    System.out.println("1 " + this.sumResult);
////    System.out.println("2 " + this.secondTerm);
////    System.out.println("turtle X " + turtleObject.getTurtleX());
////    System.out.println("turtle Y " + turtleObject.getTurtleY());
//    return this.returnArgValue;
//
//
//  }
//  @Override
//  public int getArgumentsNeeded(){
//    return this.argumentsNeeded;
//  }
//
//
//}
//
//
