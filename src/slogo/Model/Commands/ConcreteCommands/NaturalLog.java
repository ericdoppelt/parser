package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class NaturalLog extends Command {

  private double returnArgValue;
  private Number firstTerm;


  public NaturalLog(Number parameterOne) {
    firstTerm = parameterOne;
  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {

    returnArgValue = Math.log(firstTerm.doubleValue());
    System.out.println(returnArgValue);
    return this.returnArgValue;
  }


}


