//package slogo.Model.Commands.ConcreteCommands;
//
//import slogo.Model.CommandInfrastructure.CommandDatabase;
//import slogo.Model.Commands.Command;
//
///**
// * Subclass to create a LeftCommand
// *
// * @author Frank Tang
// */
//public class Tangent extends Command {
//
//
//  private double returnArgValue;
//  private Number amountOfDegrees;
//  private static final int argumentsNeeded = 1;
//  private CommandDatabase database;
//
//
//  public Tangent(CommandDatabase data) {
//    super(data);
//    database = data;
//    amountOfDegrees = degrees;
//
//  }
//
//  /**
//   * Rotates a turtle by a counterclockwise rotation of a degree amount.
//   */
//  @Override
//  public Double executeAndReturnValue() {
//    returnArgValue = Math.tan(Math.toRadians(amountOfDegrees.doubleValue()));
//    System.out.println(returnArgValue);
//    return this.returnArgValue;
//  }
//
//  @Override
//  public int getArgumentsNeeded(){
//    return this.argumentsNeeded;
//  }
//
//
//}
//
//
