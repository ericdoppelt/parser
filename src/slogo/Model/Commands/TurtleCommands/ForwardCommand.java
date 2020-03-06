package slogo.Model.Commands.TurtleCommands;

import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a Forward Command
 *
 * @author Frank Tang
 */
public class ForwardCommand extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private double turtleHeading;
  private Number distanceToTravel;

  private double distanceProportionY;
  private double distanceProportionX;

  public ForwardCommand(TurtleData turtle, Number distance) {
    turtleObject = turtle;
    distanceToTravel = distance;
    turtleHeading = turtleObject.getTurtleHeading();

    distanceProportionY = Math.sin(Math.toRadians(turtleHeading));
    distanceProportionX = Math.cos(Math.toRadians(turtleHeading));

  }

  /**
   * Moves the turtle forward by a pixel amount.
   */
  @Override
  public Double executeAndReturnValue() {
    turtleObject.moveXCoord(distanceToTravel.doubleValue() * distanceProportionX);
    turtleObject.moveYCoord(distanceToTravel.doubleValue() * distanceProportionY);

    // TODO THIS IS TESTER CODE
    turtleObject.addCoord(turtleObject.getTurtleX(), turtleObject.getTurtleY());
    System.out.println("turtle X " + turtleObject.getTurtleX());
    System.out.println("turtle Y " + turtleObject.getTurtleY());
    returnArgValue = distanceToTravel.doubleValue();
    return this.returnArgValue;

  }


}


