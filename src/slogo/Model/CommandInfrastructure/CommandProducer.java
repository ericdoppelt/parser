package slogo.Model.CommandInfrastructure;

import java.util.Stack;
import javafx.beans.property.ListProperty;
import slogo.Model.Commands.Command;

public class CommandProducer {

  /**
   * Simple parser based on regular expressions that matches input strings to kinds of program elements.
   * @author Frank Tang
   */

  private CommandDatabase commandDatabase;
  private int argumentRunningTotal;
  private static final int zeroParametersNeeded = 0;
  private static final int oneParametersNeeded = 1;
  private static final int twoParametersNeeded = 2;
  private String newCommandEntry;
  private ListProperty<String> HISTORY_LIST;
  private ListProperty<Command> COMMAND_LIST;
  private Number currentCommandReturnValue;
  private Command newCommand;
  private static final String BLANK_SPACE = " ";
  private Stack<String> oldCommStack;
  private Stack<Number> oldArgStack;


  public CommandProducer(CommandDatabase database, ListProperty<String> stringHistory, ListProperty<Command> commandHistory){
    HISTORY_LIST = stringHistory;
    COMMAND_LIST = commandHistory;
    commandDatabase = database;
    oldCommStack = new Stack<>();
    oldArgStack = new Stack<>();
  }

  /**
   * Adds the given resource file to this language's recognized types
   */
  public Number parseStacks (Stack<String> commStack, Stack<Number> argStack, int argumentThreshold) {
    argumentRunningTotal = argumentThreshold;
//    checkStackSizesandRefresh(commStack, argStack);
//    System.out.println(argumentRunningTotal);
    while (commStack.size() > 0 && argStack.size() >= argumentRunningTotal){
//      System.out.println("BeforeA" + argStack);
//      System.out.println("BeforeC" + commStack);
      newCommand = makeCommand(commStack.peek());
      int parametersNeeded = newCommand.getArgumentsNeeded();
      if(parametersNeeded == zeroParametersNeeded){
        newCommandEntry = commStack.peek();
      }
      else if(parametersNeeded == oneParametersNeeded) {
        Number firstParameter = argStack.pop();
        commandDatabase.setParameterOne(firstParameter);
        newCommandEntry = commStack.peek() + BLANK_SPACE + firstParameter.toString();
      }
      else if (parametersNeeded == twoParametersNeeded){
        Number secondParameter = argStack.pop(); //must be in this order because the second parameter is popped off first
        Number firstParameter = argStack.pop();
        commandDatabase.setParameterOne(firstParameter);
        commandDatabase.setParameterTwo(secondParameter);
        newCommandEntry = commStack.peek() + BLANK_SPACE + firstParameter.toString() + BLANK_SPACE + secondParameter.toString();
      }
      HISTORY_LIST.getValue().add(newCommandEntry);
      makeCommand(commStack.pop());
      currentCommandReturnValue = newCommand.executeAndReturnValue();
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

  private void checkStackSizesandRefresh(Stack<String> newCommStack, Stack<Number> newArgStack){
    if(newCommStack.size() > oldCommStack.size()){
      argumentRunningTotal = oldArgStack.size() + makeCommand(newCommStack.peek()).getArgumentsNeeded();
    }
    oldCommStack = newCommStack;
    oldArgStack = newArgStack;
  }


  public Command makeCommand(String commandName){
    try {
      Class commandClass = Class.forName("slogo.Model.Commands.ConcreteCommands." + commandName);
      Command command = (Command) commandClass.getConstructors()[0].newInstance(commandDatabase);
      return command;
    }
    catch (Exception e){
      e.printStackTrace();
      // TODO: FIX THIS SO WE DON'T FAIL
    }
    return null;
  }

}
