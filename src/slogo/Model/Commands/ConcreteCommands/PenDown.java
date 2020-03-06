package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class PenDown extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private static final int penStatusValue = 1;
  private boolean isPenDown = true;

  public PenDown(TurtleData turtle) {
    turtleObject = turtle;
    returnArgValue = penStatusValue;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Integer executeAndReturnValue() {
    turtleObject.setPenStatus(isPenDown);
    System.out.println("return " + returnArgValue);
    //System.out.println(returnArgValue);
//    System.out.println("turtle Y " + turtleObject.getTurtleY());
    return (int) this.returnArgValue;

  }


}



