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
//public class Towards extends Command {
//
//
//  private TurtleData turtleObject;
//  private double returnArgValue;
//  private double originalTurtleDirection;
//  private double newTurtleDirection;
//  private Number towardsX;
//  private Number towardsY;
//  private static final int halfRevolution = 180;
//  private static final int fullRevolution = 360;
//  private static final int argumentsNeeded = 2;
//  private CommandDatabase database;
//
//
//  private static final int zero = 0;
//
//  public Towards(CommandDatabase data) {
//    super(data);
//    database = data;
//    turtleObject = turtle;
//    towardsX = inputX;
//    towardsY = inputY;
//
//  }
//
//  /**
//   * Rotates a turtle by a counterclockwise rotation of a degree amount.
//   */
//  @Override
//  public Double executeAndReturnValue() {
//    double differenceX = towardsX.doubleValue() - turtleObject.getTurtleX();
//    double differenceY = towardsY.doubleValue() - turtleObject.getTurtleY();
//    double towardsAngle = Math.toDegrees(Math.atan(differenceY/differenceX));
////    System.out.println("a " + towardsAngle);
//
//
//    if(differenceX < zero){
//      newTurtleDirection = halfRevolution + towardsAngle;
//    }
//    else{
//      newTurtleDirection = towardsAngle;
//    }
//    constrainAngle();
//    originalTurtleDirection = turtleObject.getTurtleHeading();
//    returnArgValue = newTurtleDirection - originalTurtleDirection;
//
//    System.out.println("turtle heading before " + turtleObject.getTurtleHeading());
//    turtleObject.setTurtleDirection(newTurtleDirection);
//    System.out.println("turtle heading after " + turtleObject.getTurtleHeading());
//    System.out.println("3 " + newTurtleDirection);
//    System.out.println("return " + returnArgValue);
//    //System.out.println(returnArgValue);
////    System.out.println("turtle Y " + turtleObject.getTurtleY());
//    // TODO THIS IS TESTER CODE
//    turtleObject.addCoord(turtleObject.getTurtleX(), turtleObject.getTurtleY());
//    return this.returnArgValue;
//
//  }
//
//  private void constrainAngle(){
//    if(newTurtleDirection < zero) {
//      newTurtleDirection += fullRevolution;
//    }
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
