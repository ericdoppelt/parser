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
  private double originalArgValue;

  public BackCommand(TurtleData turtle, double distance) {
    turtleObject = turtle;
    originalArgValue = distance;
  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public void execute() {
    double turtleHeading = turtleObject.getTurtleHeading();
    double distanceToTravel = originalArgValue;
    double distanceProportionY = Math.sin(Math.toRadians(turtleHeading));
    double distanceProportionX = Math.cos(Math.toRadians(turtleHeading));

    turtleObject.moveXCoord(-distanceToTravel * distanceProportionX);
    turtleObject.moveYCoord(-distanceToTravel * distanceProportionY);
    System.out.println(turtleObject.getTurtleX());
    System.out.println(turtleObject.getTurtleY());
  }

  @Override
  public Integer returnArgValue() {
    return (int) this.originalArgValue;
  }

}


