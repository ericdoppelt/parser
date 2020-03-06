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
//public class Remainder extends Command {
//
//  private Number firstTerm;
//  private Number secondTerm;
//  private double returnArgValue;
//  private static final int argumentsNeeded = 2;
//  private CommandDatabase database;
//
//
//  public Remainder(CommandDatabase data) {
//    super(data);
//    database = data;
//
//    firstTerm = parameterOne;
//    secondTerm = parameterTwo;
//  }
//
//
//  /**
//   * Moves the turtle forward by a pixel amount.
//   */
//  @Override
//  public Integer executeAndReturnValue() {
//    returnArgValue = firstTerm.doubleValue() % secondTerm.doubleValue();
////    System.out.println("turtle X " + turtleObject.getTurtleX());
////    System.out.println("turtle Y " + turtleObject.getTurtleY());
//    return (int) this.returnArgValue;
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
