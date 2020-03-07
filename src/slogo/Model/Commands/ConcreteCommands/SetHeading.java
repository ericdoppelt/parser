package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a LeftCommand
 *
 * @author Frank Tang
 */
public class SetHeading extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private double originalTurtleDirection;
  private Number newTurtleDirection;
  private static final int fullRevolution = 360;
  private static final int argumentsNeeded = 1;
  private CommandDatabase database;


  public SetHeading(CommandDatabase data) {
    super(data);
    database = data;

    //returnArgValue = min(abs(newTurtleDirection - originalTurtleDirection), fullRevolution - abs(newTurtleDirection - originalTurtleDirection));

  }

  /**
   * Rotates a turtle by a counterclockwise rotation of a degree amount.
   */
  @Override
  public Double executeAndReturnValue() {
    turtleObject = database.getTurtle();
    originalTurtleDirection = turtleObject.getTurtleHeading();
    newTurtleDirection = database.getParameterStack().pop();
    returnArgValue = newTurtleDirection.doubleValue() - originalTurtleDirection;
    turtleObject.setTurtleDirection(newTurtleDirection.doubleValue());
    return this.returnArgValue;

  }
  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }



}


