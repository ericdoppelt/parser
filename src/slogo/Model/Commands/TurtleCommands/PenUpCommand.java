package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class PenUpCommand extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private static final int penStatusValue = 0;
  private boolean isPenDown = false;

  public PenUpCommand(TurtleData turtle) {
    turtleObject = turtle;
    returnArgValue = penStatusValue;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    turtleObject.setPenStatus(isPenDown);
    System.out.println("return " + returnArgValue);
    //System.out.println(returnArgValue);
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Integer returnArgValue() {

    return (int) this.returnArgValue;
  }
  }



