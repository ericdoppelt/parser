package slogo.Model.Commands;

/**
 * Abstract parent class for making the shapes representing the grid of the simulation
 *
 * @author Frank Tang
 */
public abstract class Command implements CommandInterface {

  /**
   * Superconstructor for a Turtle Command
   */
  public Command() {
  }

  public abstract Number executeAndReturnValue();

}
