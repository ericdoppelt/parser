package slogo.Commands.TurtleCommands;

import slogo.TurtleData;

/**
 * Subclass to create a Forward Command
 *
 * @author Frank Tang
 */
public class ForwardCommand extends TurtleCommand {

  private TurtleData turtleObject;
  private double distanceToTravel;


  public ForwardCommand(TurtleData turtle, double distance) {
    super(turtle, distance);
    turtleObject = turtle;
    distanceToTravel = distance;
  }

  /**
   * Executes a ForwardCommand object
   */
  public void execute() {
    turtleObject.moveXCoord(distanceToTravel);
    //System.out.println(turtleObject.getTurtleX());
  }

}


