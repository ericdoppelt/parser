//package slogo.Model.Commands.ConcreteCommands;
//
//import slogo.Model.CommandInfrastructure.CommandDatabase;
//import slogo.Model.Commands.Command;
//import slogo.Model.TurtleData;
//
///**
// * Subclass to create a LeftCommand
// *
// * @author Frank Tang
// */
//public class PenUp extends Command {
//
//  private TurtleData turtleObject;
//  private double returnArgValue;
//  private static final int penStatusValue = 0;
//  private boolean isPenDown = false;
//  private static final int argumentsNeeded = 0;
//  private CommandDatabase database;
//
//  public PenUp(CommandDatabase data) {
//    super(data);
//    database = data;
//    turtleObject = turtle;
//    returnArgValue = penStatusValue;
//
//  }
//
//  /**
//   * Rotates a turtle by a counterclockwise rotation of a degree amount.
//   */
//  @Override
//  public Integer executeAndReturnValue() {
//    turtleObject.setPenStatus(isPenDown);
//    System.out.println("return " + returnArgValue);
//    //System.out.println(returnArgValue);
////    System.out.println("turtle Y " + turtleObject.getTurtleY());
//    return (int) this.returnArgValue;
//
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
//
