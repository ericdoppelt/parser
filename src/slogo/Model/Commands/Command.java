package slogo.Model.Commands;

/**
 * Abstract parent class that acts as the base for all the commands in the program.
 *
 * @author Frank Tang
 */
public abstract class Command {

  /**
   * Superconstructor for a Turtle Command
   */
  public Command() {
  }

  public abstract Number executeAndReturnValue();

}
