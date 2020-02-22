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
  private double originalArgValue;


  public ForwardCommand(TurtleData turtle, double distance) {
    turtleObject = turtle;
    originalArgValue = distance;
  }


  /**
   * Moves the turtle forward by a pixel amount.
   */
  @Override
  public void execute() {
    double turtleHeading = turtleObject.getTurtleHeading();
    double distanceToTravel = originalArgValue;

    double distanceProportionY = Math.sin(Math.toRadians(turtleHeading));
    double distanceProportionX = Math.cos(Math.toRadians(turtleHeading));

    turtleObject.moveXCoord(distanceToTravel * distanceProportionX);
    turtleObject.moveYCoord(distanceToTravel * distanceProportionY);
    System.out.println("turtle X " + turtleObject.getTurtleX());
    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Integer returnArgValue() {
    return (int) this.originalArgValue;
  }

}


