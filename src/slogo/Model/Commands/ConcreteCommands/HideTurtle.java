package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class HideTurtle extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private static final int turtleVisibilityStatus = 0;
  private boolean isTurtleVisible = false;

  public HideTurtle(TurtleData turtle) {
    turtleObject = turtle;
    returnArgValue = turtleVisibilityStatus;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Integer executeAndReturnValue() {
    turtleObject.setPenStatus(isTurtleVisible);
    System.out.println("return " + returnArgValue);
    //System.out.println(returnArgValue);
//    System.out.println("turtle Y " + turtleObject.getTurtleY());
    return (int) this.returnArgValue;

  }

}



