package slogo.Model.CommandInfrastructure;

import java.util.Stack;
import javafx.beans.property.ListProperty;
import slogo.DisplayError;
import slogo.Model.Commands.Command;

public class CommandProducer {

  /**
   * Simple parser based on regular expressions that matches input strings to kinds of program elements.
   * @author Frank Tang
   */

  private CommandDatabase commandDatabase;
  private int argumentRunningTotal;
  private String newCommandEntry;
  private String argumentEntries;
  private ListProperty<String> HISTORY_LIST;
  private Number currentCommandReturnValue;
  private Command newCommand;
  private static final String BLANK_SPACE = " ";
  private static final String BLANK = "";


  public CommandProducer(CommandDatabase database, ListProperty<String> stringHistory){
    HISTORY_LIST = stringHistory;
    commandDatabase = database;
  }

  /**
   * Adds the given resource file to this language's recognized types
   */
  public Number parseStacks (Stack<String> commStack, Stack<Number> argStack, int argumentThreshold) {
    argumentRunningTotal = argumentThreshold;
    while (commStack.size() > 0 && argStack.size() >= argumentRunningTotal){
      newCommand = makeCommand(commStack.peek());
      int parametersNeeded = newCommand.getArgumentsNeeded();
      newCommandEntry = commStack.peek();
      argumentEntries = BLANK;
      for (int i = 0; i < parametersNeeded; i++){
        commandDatabase.getParameterStack().push(argStack.peek());
        argumentEntries = BLANK_SPACE + argStack.pop().toString() + argumentEntries;
      }
      newCommandEntry = newCommandEntry + argumentEntries;
      HISTORY_LIST.getValue().add(newCommandEntry);

      for(int i = 0; i < commandDatabase.getTurtleList().size(); i++){
        if(commandDatabase.getTurtleList().get(i).getTurtleActive()) {
          commandDatabase.setActiveTurtle(commandDatabase.getTurtleList().get(i));
          newCommand = makeCommand(commStack.peek());
          currentCommandReturnValue = newCommand.executeAndReturnValue();
        }
      }

      for(int i = 0; i < newCommand.getArgumentsNeeded(); i++){
        commandDatabase.getParameterStack().pop();
      }
      commStack.pop();
      argumentRunningTotal--;
      if(commStack.size() == 0){
        break;
      }
      else if(argStack.size() <= argumentRunningTotal || argStack.size() == 0){
         argStack.push(currentCommandReturnValue);
      }

    }

    return currentCommandReturnValue;
  }



  public Command makeCommand(String commandName){
    try {

      Class commandClass = Class.forName("slogo.Model.Commands.ConcreteCommands." + commandName);
      Command command = (Command) commandClass.getConstructors()[0].newInstance(commandDatabase);
      return command;
    }
    catch (Exception e){
      new DisplayError("NullCommand");
    }
    return null;
  }

}
