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
  private double returnArgValue;
  private Number distanceToTravel;


  public BackCommand(TurtleData turtle, Number distance) {
    turtleObject = turtle;
    distanceToTravel = distance;
  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public void execute() {
    returnArgValue = distanceToTravel.doubleValue();
    double turtleHeading = turtleObject.getTurtleHeading();
    double distanceProportionY = Math.sin(Math.toRadians(turtleHeading));
    double distanceProportionX = Math.cos(Math.toRadians(turtleHeading));

    turtleObject.moveXCoord(-distanceToTravel.doubleValue() * distanceProportionX);
    turtleObject.moveYCoord(-distanceToTravel.doubleValue() * distanceProportionY);
    // TODO THIS IS TESTER CODE
    turtleObject.addCoord(turtleObject.getTurtleX(), turtleObject.getTurtleY());
    System.out.println(turtleObject.getTurtleX());
    System.out.println(turtleObject.getTurtleY());
  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }

}


