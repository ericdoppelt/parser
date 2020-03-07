package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Subclass to create a BackCommand
 *
 * @author Frank Tang
 */
public class Backward extends Command {

  private TurtleData turtleObject;
  private double returnArgValue;
  private Number distanceToTravel;
  private static final int argumentsNeeded = 1;
  private CommandDatabase database;


  public Backward(CommandDatabase data) {
    super(data);
    database = data;

  }

  /**
   * Moves the turtle backwards by a pixel amount.
   */
  @Override
  public Double executeAndReturnValue() {
    turtleObject = database.getTurtle();
    distanceToTravel = database.getParameterStack().pop();
    returnArgValue = distanceToTravel.doubleValue();
    double turtleHeading = turtleObject.getTurtleHeading();
    double distanceProportionY = Math.sin(Math.toRadians(turtleHeading));
    double distanceProportionX = Math.cos(Math.toRadians(turtleHeading));

    turtleObject.moveXCoord(-distanceToTravel.doubleValue() * distanceProportionX);
    turtleObject.moveYCoord(-distanceToTravel.doubleValue() * distanceProportionY);
    // TODO THIS IS TESTER CODE
    turtleObject.addCoord(turtleObject.getTurtleX(), turtleObject.getTurtleY());
    System.out.println("backX " + turtleObject.getTurtleX());
    System.out.println("backY " + turtleObject.getTurtleY());
    return this.returnArgValue;
  }
  @Override
  public int getArgumentsNeeded(){
    return this.argumentsNeeded;
  }



}


