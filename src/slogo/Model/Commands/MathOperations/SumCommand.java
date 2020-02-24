package slogo.Model.Commands.MathOperations;

import slogo.Model.Commands.Command;

/**
 * Subclass to create a Sum Command
 *
 * @author Frank Tang
 */
public class SumCommand extends Command {

  private double firstTerm;
  private double secondTerm;
  private double returnArgValue;


  public SumCommand(double parameterOne, double parameterTwo) {
    firstTerm = parameterOne;
    secondTerm = parameterTwo;
  }


  /**
   * Moves the turtle forward by a pixel amount.
   */
  @Override
  public void execute() {
    returnArgValue = firstTerm + secondTerm;
//    System.out.println("1 " + this.sumResult);
//    System.out.println("2 " + this.secondTerm);
//    System.out.println("turtle X " + turtleObject.getTurtleX());
//    System.out.println("turtle Y " + turtleObject.getTurtleY());

  }

  @Override
  public Double returnArgValue() {
    //System.out.println("return " + this.returnArgValue);
    return this.returnArgValue;
  }

}


