package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class ClearScreen extends Command {
  //moves turtle to an absolute screen position, where (0, 0) is the center of the screen
  //returns the distance turtle moved

  private TurtleData turtleObject;
  private double returnArgValue;
  private static final double zeroX = 0;
  private static final double zeroY = 0;
  private static final boolean areTrailsVisible = false;
  private static final int argumentsNeeded = 0;
  private CommandDatabase database;

  public ClearScreen(CommandDatabase data) {
    super(data);
    database = data;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    turtleObject = database.getTurtle();
    double differenceX = zeroX - turtleObject.getTurtleX();
    double differenceY = zeroY - turtleObject.getTurtleY();

    returnArgValue = Math.sqrt(differenceX * differenceX + differenceY * differenceY);
    System.out.println("turtleX before " + turtleObject.getTurtleX());
    System.out.println("turtleY before " + turtleObject.getTurtleY());
    turtleObject.setXCoord(zeroX);
    turtleObject.setYCoord(zeroY);
    System.out.println("turtleX after " + turtleObject.getTurtleX());
    System.out.println("turtleY after " + turtleObject.getTurtleY());
    System.out.println("return " + returnArgValue);
    turtleObject.setTurtleTrails(areTrailsVisible);
    return this.returnArgValue;
  }
  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }



}



