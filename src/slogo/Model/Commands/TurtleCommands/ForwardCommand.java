package slogo.Model.Commands.TurtleCommands;

import slogo.Model.TurtleData;

/**
 * Subclass to create a Forward Command
 *
 * @author Frank Tang
 */
public class ForwardCommand extends TurtleCommand {

  private TurtleData turtleObject;
  private double distanceToTravel;
  private double turtleHeading;
  private double distanceProportionX;
  private double distanceProportionY;


  public ForwardCommand(TurtleData turtle, double distance) {
    turtleObject = turtle;
    distanceToTravel = distance;
    turtleHeading = turtleObject.getTurtleHeading();

    distanceProportionY = Math.sin(Math.toRadians(turtleHeading));
    distanceProportionX = Math.cos(Math.toRadians(turtleHeading));


  }

  /**
   * Moves the turtle forward by a pixel amount.
   */
  public void execute() {
    turtleObject.moveXCoord(distanceToTravel * distanceProportionX);
    turtleObject.moveYCoord(distanceToTravel * distanceProportionY);
  }

}


