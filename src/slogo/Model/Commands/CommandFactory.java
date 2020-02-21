package slogo.Model.Commands;

import static java.util.Map.entry;

import java.util.Map;
import slogo.Model.Commands.TurtleCommands.*;
import slogo.Model.TurtleData;

public class CommandFactory {

  private static TurtleData targetTurtle;
  private static String targetCommand;
  private static double argValue;
  private static Map<String, Command> POSSIBLE_COMMANDS_MAPS;

  public CommandFactory(String command, TurtleData turtle, double value){
    targetTurtle = turtle;
    argValue = value;
    targetCommand = command;
    POSSIBLE_COMMANDS_MAPS = Map.ofEntries(
        entry("Forward", new ForwardCommand(targetTurtle, argValue)),
        entry("Back", new BackCommand(targetTurtle, argValue)),
        entry("Left", new LeftCommand(targetTurtle, argValue)),
        entry("Right", new RightCommand(targetTurtle, argValue))
    );
  }

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public Command makeCommand () {
    return POSSIBLE_COMMANDS_MAPS.get(targetCommand);
  }

}

