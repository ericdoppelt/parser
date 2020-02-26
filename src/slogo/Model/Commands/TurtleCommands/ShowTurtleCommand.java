package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class ShowTurtleCommand extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private static final int turtleVisibilityStatus = 1;
  private boolean isTurtleVisible = true;

  public ShowTurtleCommand(TurtleData turtle) {
    turtleObject = turtle;
    returnArgValue = turtleVisibilityStatus;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public void execute() {
    turtleObject.setTurtleVisibility(isTurtleVisible);
    System.out.println("return " + returnArgValue);
    //System.out.println(returnArgValue);
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Integer returnArgValue() {

    return (int) this.returnArgValue;
  }
  }



