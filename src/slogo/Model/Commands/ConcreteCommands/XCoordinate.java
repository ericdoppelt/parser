package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class XCoordinate extends Command {


  private double returnArgValue;
  private static final int argumentsNeeded = 0;
  private CommandDatabase database;
  private TurtleData turtleObject;



  public XCoordinate(CommandDatabase data) {
    super(data);
    database = data;

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    turtleObject = database.getTurtle();
    returnArgValue = turtleObject.getTurtleX();
    System.out.println(returnArgValue);
    return this.returnArgValue;
  }

  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }


}


