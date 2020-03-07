package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class Right extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private Number degreesChanged;
  private static final int argumentsNeeded = 1;
  private CommandDatabase database;


  public Right(CommandDatabase data) {
    super(data);
    database = data;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    turtleObject = database.getTurtle();
    degreesChanged = database.getParameterStack().peek();
    returnArgValue = degreesChanged.doubleValue();
    System.out.println("turtle heading " + turtleObject.getTurtleHeading());
    turtleObject.rotateTurtleHeading(degreesChanged.doubleValue());
    System.out.println("turtle heading " + turtleObject.getTurtleHeading());
//    System.out.println("turtle X " + turtleObject.getTurtleX());
//    System.out.println("turtle Y " + turtleObject.getTurtleY());
    return this.returnArgValue;

  }
  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }



}


