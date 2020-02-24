package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class TowardsCommand extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private double originalTurtleDirection;
  private double newTurtleDirection;
  private double towardsX;
  private double towardsY;
  private static final int halfRevolution = 180;
  private static final int fullRevolution = 360;

  private static final int zero = 0;

  public TowardsCommand(TurtleData turtle, double inputX, double inputY) {
    turtleObject = turtle;
    towardsX = inputX;
    towardsY = inputY;
    double differenceX = towardsX - turtle.getTurtleX();
    double differenceY = towardsY - turtle.getTurtleY();
    double towardsAngle = Math.toDegrees(Math.atan(differenceY/differenceX));
//    System.out.println("a " + towardsAngle);


    if(differenceX < zero){
      newTurtleDirection = halfRevolution + towardsAngle;
    }
    else{
      newTurtleDirection = towardsAngle;
    }
    constrainAngle();
    originalTurtleDirection = turtle.getTurtleHeading();
    returnArgValue = newTurtleDirection - originalTurtleDirection;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    System.out.println("turtle heading before " + turtleObject.getTurtleHeading());
    turtleObject.setTurtleDirection(newTurtleDirection);
    System.out.println("turtle heading after " + turtleObject.getTurtleHeading());
    System.out.println("3 " + newTurtleDirection);
    System.out.println("return " + returnArgValue);
    //System.out.println(returnArgValue);
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Double returnArgValue() {

    return this.returnArgValue;
  }

  private void constrainAngle(){
    if(newTurtleDirection < zero) {
      newTurtleDirection += fullRevolution;
    }
  }

}


