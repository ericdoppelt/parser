package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class SetTowards extends Command {


  private TurtleData turtleObject;
  private double returnArgValue;
  private double originalTurtleDirection;
  private double newTurtleDirection;
  private Number towardsX;
  private Number towardsY;
  private static final int halfRevolution = 180;
  private static final int fullRevolution = 360;
  private static final int argumentsNeeded = 2;
  private CommandDatabase database;


  private static final int zero = 0;

  public SetTowards(CommandDatabase data) {
    super(data);
    database = data;


  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    turtleObject = database.getTurtle();
    towardsX = database.getParameterStack().pop();
    towardsY = database.getParameterStack().pop();
    double differenceX = towardsX.doubleValue() - turtleObject.getTurtleX();
    double differenceY = towardsY.doubleValue() - turtleObject.getTurtleY();
    double towardsAngle = Math.toDegrees(Math.atan(differenceY/differenceX));


    if(differenceX < zero){
      newTurtleDirection = halfRevolution + towardsAngle;
    }
    else{
      newTurtleDirection = towardsAngle;
    }
    originalTurtleDirection = turtleObject.getTurtleHeading();
    returnArgValue = newTurtleDirection - originalTurtleDirection;

    System.out.println("turtle heading before " + turtleObject.getTurtleHeading());
    turtleObject.setTurtleDirection(newTurtleDirection);
    System.out.println("turtle heading after " + turtleObject.getTurtleHeading());
    System.out.println("3 " + newTurtleDirection);
    System.out.println("return " + returnArgValue);
    // TODO THIS IS TESTER CODE
    turtleObject.addCoord(turtleObject.getTurtleX(), turtleObject.getTurtleY());
    return this.returnArgValue;

  }

  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }



}


