package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class SetPositionCommand extends Command {
  //moves turtle to an absolute screen position, where (0, 0) is the center of the screen
  //returns the distance turtle moved

  private TurtleData turtleObject;
  private double returnArgValue;
  private double originalTurtleDirection;
  private double newX;
  private double newY;

  private static final int zero = 0;

  public SetPositionCommand(TurtleData turtle, double inputX, double inputY) {
    turtleObject = turtle;
    newX = inputX;
    newY = inputY;
    double differenceX = newX - turtle.getTurtleX();
    double differenceY = newY - turtle.getTurtleY();

    returnArgValue = Math.sqrt(differenceX * differenceX + differenceY * differenceY);

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    System.out.println("turtleX before " + turtleObject.getTurtleX());
    System.out.println("turtleY before " + turtleObject.getTurtleY());
    turtleObject.setXCoord(newX);
    turtleObject.setYCoord(newY);
    System.out.println("turtleX after " + turtleObject.getTurtleX());
    System.out.println("turtleY after " + turtleObject.getTurtleY());
    System.out.println("return " + returnArgValue);
    //System.out.println(returnArgValue);
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Integer returnArgValue() {

    return (int) this.returnArgValue;
  }
}



