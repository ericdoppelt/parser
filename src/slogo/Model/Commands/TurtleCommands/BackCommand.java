package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a BackCommand
 *
 * @author Frank Tang
 */
public class BackCommand extends Command {

  private TurtleData turtleObject;
  private double distanceToTravel;
  private double turtleHeading;
  private double distanceProportionX;
  private double distanceProportionY;
  private double reverseDirection = -1;



  public BackCommand(TurtleData turtle, double distance) {
    turtleObject = turtle;
    distanceToTravel = distance;
    turtleHeading = turtleObject.getTurtleHeading();

    distanceProportionY = Math.sin(Math.toRadians(turtleHeading));
    distanceProportionX = Math.cos(Math.toRadians(turtleHeading));
  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public void execute() {
    turtleObject.moveXCoord(reverseDirection * distanceToTravel * distanceProportionX);
    turtleObject.moveYCoord(reverseDirection * distanceToTravel * distanceProportionY);
//    System.out.println(turtleObject.getTurtleX());
//    System.out.println(turtleObject.getTurtleY());
  }

  @Override
  public Integer returnArgValue() {
    return (int) this.distanceToTravel;
  }

}


