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
  private double distanceToTravel;

  private double distanceProportionY;
  private double distanceProportionX;



  public ForwardCommand(TurtleData turtle, double distance) {
    turtleObject = turtle;
    returnArgValue = distance;
    distanceToTravel = distance;
    turtleHeading = turtleObject.getTurtleHeading();

    distanceProportionY = Math.sin(Math.toRadians(turtleHeading));
    distanceProportionX = Math.cos(Math.toRadians(turtleHeading));

  }


  /**
   * Moves the turtle forward by a pixel amount.
   */
  @Override
  public void execute() {

    turtleObject.moveXCoord(distanceToTravel * distanceProportionX);
    turtleObject.moveYCoord(distanceToTravel * distanceProportionY);

    // TODO THIS IS TESTER CODE
    turtleObject.addCoord(turtleObject.getTurtleX(), turtleObject.getTurtleY());
    //System.out.println("turtle X " + turtleObject.getTurtleX());
    //System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }

}


