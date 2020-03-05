package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a Sum Command
 *
 * @author Frank Tang
 */
public class RemainderCommand extends Command {

  private Number firstTerm;
  private Number secondTerm;
  private double returnArgValue;


  public RemainderCommand(Number parameterOne, Number parameterTwo) {
    firstTerm = parameterOne;
    secondTerm = parameterTwo;
  }


  /**
   * Moves the turtle forward by a pixel amount.
   */
  @Override
  public Integer executeAndReturnValue() {
    returnArgValue = firstTerm.doubleValue() % secondTerm.doubleValue();
//    System.out.println("turtle X " + turtleObject.getTurtleX());
//    System.out.println("turtle Y " + turtleObject.getTurtleY());
    return (int) this.returnArgValue;
  }


}


