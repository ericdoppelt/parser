package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class ClearScreenCommand extends Command {
  //moves turtle to an absolute screen position, where (0, 0) is the center of the screen
  //returns the distance turtle moved

  private TurtleData turtleObject;
  private double returnArgValue;
  private static final double zeroX = 0;
  private static final double zeroY = 0;
  private static final boolean areTrailsVisible = false;

  public ClearScreenCommand(TurtleData turtle) {
    turtleObject = turtle;
    double differenceX = zeroX - turtle.getTurtleX();
    double differenceY = zeroY - turtle.getTurtleY();

    returnArgValue = Math.sqrt(differenceX * differenceX + differenceY * differenceY);

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    System.out.println("turtleX before " + turtleObject.getTurtleX());
    System.out.println("turtleY before " + turtleObject.getTurtleY());
    turtleObject.setXCoord(zeroX);
    turtleObject.setYCoord(zeroY);
    System.out.println("turtleX after " + turtleObject.getTurtleX());
    System.out.println("turtleY after " + turtleObject.getTurtleY());
    System.out.println("return " + returnArgValue);
    turtleObject.setTurtleTrails(areTrailsVisible);
  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }
}



