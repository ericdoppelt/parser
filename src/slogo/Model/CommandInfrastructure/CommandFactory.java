package slogo.Model.CommandInfrastructure;

import static java.util.Map.entry;

import java.util.Map;
import javafx.util.Pair;
import slogo.Model.Commands.Command;
import slogo.Model.Commands.MathOperations.DifferenceCommand;
import slogo.Model.Commands.MathOperations.ProductCommand;
import slogo.Model.Commands.MathOperations.QuotientCommand;
import slogo.Model.Commands.MathOperations.RemainderCommand;
import slogo.Model.Commands.MathOperations.SumCommand;
import slogo.Model.Commands.TurtleCommands.BackCommand;
import slogo.Model.Commands.TurtleCommands.ForwardCommand;
import slogo.Model.Commands.TurtleCommands.LeftCommand;
import slogo.Model.Commands.TurtleCommands.RightCommand;
import slogo.Model.Commands.TurtleCommands.SetHeadingCommand;
import slogo.Model.Commands.TurtleCommands.SetPositionCommand;
import slogo.Model.Commands.TurtleCommands.TowardsCommand;
import slogo.Model.TurtleData;

public class CommandFactory {

  private TurtleData targetTurtle;
  private String targetCommand;
  private static final Integer oneParameterNeeded = 1;
  private static final Integer twoParametersNeeded = 2;
  private double parameterOne;
  private double parameterTwo;
  private Map<String, Pair<Command, Integer>> POSSIBLE_COMMANDS_MAP;

  public CommandFactory(String command, TurtleData turtle){
    targetTurtle = turtle;
    targetCommand = command;
    updateCommandMap();
  }

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public Command makeOneParameterCommand (double value) {
    parameterOne = value;
    updateCommandMap();
    return POSSIBLE_COMMANDS_MAP.get(targetCommand).getKey();
  }

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public Command makeTwoParameterCommand (double value1, double value2) {
    parameterOne = value1;
    parameterTwo = value2;
    updateCommandMap();
    return POSSIBLE_COMMANDS_MAP.get(targetCommand).getKey();
  }

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public Integer getAmountOfParametersNeeded() {
    return POSSIBLE_COMMANDS_MAP.get(targetCommand).getValue();
  }

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  private void updateCommandMap() {
    POSSIBLE_COMMANDS_MAP = Map.ofEntries(
        //Turtle Commands
        entry("Forward", new Pair<>(new ForwardCommand(targetTurtle, parameterOne), oneParameterNeeded)),
        entry("Backward", new Pair<>(new BackCommand(targetTurtle, parameterOne), oneParameterNeeded)),
        entry("Left", new Pair<>(new LeftCommand(targetTurtle, parameterOne), oneParameterNeeded)),
        entry("Right", new Pair<>(new RightCommand(targetTurtle, parameterOne), oneParameterNeeded)),
        entry("SetHeading", new Pair<>(new SetHeadingCommand(targetTurtle, parameterOne), oneParameterNeeded)),
        //Math Operations
        entry("Sum", new Pair<>(new SumCommand(parameterOne, parameterTwo), twoParametersNeeded)),
        entry("Difference", new Pair<>(new DifferenceCommand(parameterOne, parameterTwo), twoParametersNeeded)),
        entry("Product", new Pair<>(new ProductCommand(parameterOne, parameterTwo), twoParametersNeeded)),
        entry("Quotient", new Pair<>(new QuotientCommand(parameterOne, parameterTwo), twoParametersNeeded)),
        entry("Remainder", new Pair<>(new RemainderCommand(parameterOne, parameterTwo), twoParametersNeeded)),
        entry("SetTowards", new Pair<>(new TowardsCommand(targetTurtle, parameterOne, parameterTwo), twoParametersNeeded)),
        entry("SetPosition", new Pair<>(new SetPositionCommand(targetTurtle, parameterOne, parameterTwo), twoParametersNeeded))
    );

  }



}

