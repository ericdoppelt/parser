package slogo.Commands.TurtleCommands;

import slogo.Commands.Command;
import slogo.TurtleData;

/**
 * Abstract parent class for making the shapes representing the grid of the simulation
 *
 * @author Frank Tang
 */
public abstract class TurtleCommand implements Command {

  /**
   * Parent class to make ShapeGrid object
   */
  public TurtleCommand(TurtleData turtle, double distance) {
  }

}
