package slogo.Model.CommandInfrastructure;

import java.util.Stack;
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
  private String commandHistory;
  private Number currentCommandReturnValue;
  private Command newCommand;





  public CommandProducer(CommandDatabase database){
    commandDatabase = database;
  }

  /**
   * Adds the given resource file to this language's recognized types
   */
  public Number parseStacks (Stack commStack, Stack argStack, int argumentThreshold) {
    argumentRunningTotal = argumentThreshold;
//    System.out.println(argumentRunningTotal);
    while (commStack.size() > 0 && argStack.size() >= argumentRunningTotal){
      System.out.println("BeforeA" + argStack);
      System.out.println("BeforeC" + commStack);
      int parametersNeeded = commandDatabase.getAmountOfParametersNeeded(commStack.peek().toString());
      if(parametersNeeded == zeroParametersNeeded){
        commandHistory = commStack.peek().toString();
        newCommand = commandDatabase.makeZeroParameterCommand(commStack.pop().toString());
      }
      else if(parametersNeeded == oneParametersNeeded) {
        Number firstParameter = (Number) argStack.pop();
        commandHistory = commStack.peek().toString() + " " + firstParameter.toString();
        newCommand = commandDatabase.makeOneParameterCommand(commStack.pop().toString(), firstParameter);
      }
      else if (parametersNeeded == twoParametersNeeded){
        Number secondParameter = (Number) argStack.pop(); //must be in this order because the second parameter is popped off first
        Number firstParameter = (Number) argStack.pop();
        commandHistory = commStack.peek().toString() + " " + firstParameter.toString() + " " + secondParameter.toString();
        newCommand = commandDatabase.makeTwoParameterCommand(commStack.pop().toString(), firstParameter, secondParameter);
      }
      commandDatabase.addToHistory(commandHistory);
//      System.out.println(newCommand.getClass());
      currentCommandReturnValue = newCommand.executeAndReturnValue();
      argumentRunningTotal--;
//      System.out.println("command " + currentCommandReturnValue);
      System.out.println(argumentRunningTotal);
      if(commStack.size() == 0){
        break;
      }
      else if(argStack.size() <= argumentRunningTotal || argStack.size() == 0){
         argStack.push(currentCommandReturnValue);
      }

    }
//    System.out.println("command1 " + currentCommandReturnValue);


    return currentCommandReturnValue;
  }

}
