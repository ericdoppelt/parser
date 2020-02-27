package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a Sum Command
 *
 * @author Frank Tang
 */
public class QuotientCommand extends Command {

  private Number firstTerm;
  private Number secondTerm;
  private double returnArgValue;


  public QuotientCommand(Number parameterOne, Number parameterTwo) {
    firstTerm = parameterOne;
    secondTerm = parameterTwo;
  }


  /**
   * Moves the turtle forward by a pixel amount.
   */
  @Override
  public void execute() {
    returnArgValue =  firstTerm.doubleValue() / secondTerm.doubleValue();
//    System.out.println("turtle X " + turtleObject.getTurtleX());
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Double returnArgValue() {
    return this.returnArgValue;
  }

}


