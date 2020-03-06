package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class Minus extends Command {

  private double returnArgValue;
  private Number firstTerm;


  public Minus(Number parameterOne) {
    firstTerm = parameterOne;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    returnArgValue = -firstTerm.doubleValue();
//    System.out.println(turtleObject.getTurtleHeading());
    return this.returnArgValue;
  }

}


