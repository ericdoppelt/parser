package slogo.Model.CommandInfrastructure;

import java.util.Stack;
import slogo.Model.Commands.Command;
import slogo.Model.Commands.MathOperations.PiCommand;

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
  public Number parseStacks (Stack<String> commStack, Stack<Number> argStack, int argumentThreshold) {
    argumentRunningTotal = argumentThreshold;
//    System.out.println(argumentRunningTotal);
    while (commStack.size() > 0 && argStack.size() >= argumentRunningTotal){
//      System.out.println("BeforeA" + argStack);
//      System.out.println("BeforeC" + commStack);
      int parametersNeeded = commandDatabase.getAmountOfParametersNeeded(commStack.peek());
      makeCommand("slogo.Model.Commands.TurtleCommands.ForwardCommand");
      if(parametersNeeded == zeroParametersNeeded){
        commandHistory = commStack.peek();
        newCommand = commandDatabase.makeZeroParameterCommand(commStack.pop());
      }
      else if(parametersNeeded == oneParametersNeeded) {
        Number firstParameter = argStack.pop();
        commandHistory = commStack.peek() + " " + firstParameter.toString();
        newCommand = commandDatabase.makeOneParameterCommand(commStack.pop(), firstParameter);
      }
      else if (parametersNeeded == twoParametersNeeded){
        Number secondParameter = argStack.pop(); //must be in this order because the second parameter is popped off first
        Number firstParameter = argStack.pop();
        commandHistory = commStack.peek() + " " + firstParameter.toString() + " " + secondParameter.toString();
        newCommand = commandDatabase.makeTwoParameterCommand(commStack.pop(), firstParameter, secondParameter);
      }
      commandDatabase.addToHistory(commandHistory);
//      System.out.println(newCommand.getClass());
      //currentCommandReturnValue = newCommand.executeAndReturnValue();
      argumentRunningTotal--;
//      System.out.println("command " + currentCommandReturnValue);
//      System.out.println(argumentRunningTotal);
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


  public Command makeCommand(String commandName){
    try {
      Class commandClass = Class.forName(commandName);
//      System.out.println(commandClass.getConstructors()[0].getParameters()[1]);
//      for (int i = 0; i < commandClass.getConstructors()[0].getParameters().length; i++) {
      System.out.println(commandClass.getConstructors()[0].getParameters()[0]);
//      System.out.println("test " + command);
      return new PiCommand();
    }
    catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

}
