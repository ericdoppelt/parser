package slogo.Model.CommandInfrastructure;

import java.util.Stack;
import slogo.Model.Commands.Command;

import slogo.Model.TurtleData;

public class CommandProducer {

  /**
   * Simple parser based on regular expressions that matches input strings to kinds of program elements.
   * @author Frank Tang
   */

  private CommandDatabase commandDatabase;
  private TurtleData turtle;
  private static int argumentRunningTotal;
  private static final int zeroParametersNeeded = 0;
  private static final int oneParametersNeeded = 1;
  private static final int twoParametersNeeded = 2;
  private static final int controlParametersNeeded = 2;
  private String commandHistory;



  public CommandProducer(CommandDatabase database){
    commandDatabase = database;
  }

  /**
   * Adds the given resource file to this language's recognized types
   */
  public void parseStacks (Stack commStack, Stack argStack, int argumentThreshold) {
    argumentRunningTotal = argumentThreshold;
//    System.out.println(argumentRunningTotal);
    while (commStack.size() > 0 && argStack.size() >= argumentRunningTotal){
//      System.out.println("BeforeA" + argStack);
//      System.out.println("BeforeC" + commStack);
      int parametersNeeded = commandDatabase.getAmountOfParametersNeeded(commStack.peek().toString());
      Command newCommand = null;
      if(parametersNeeded == zeroParametersNeeded){
        commandHistory = commStack.peek().toString();
        newCommand = commandDatabase.makeZeroParameterCommand(commStack.pop().toString());
      }
      else if(parametersNeeded == oneParametersNeeded) {
        Number firstParameter = (Number) argStack.pop();
        //System.out.println("Number " + commStack.peek().toString());
        commandHistory = commStack.peek().toString() + " " + firstParameter.toString();
        newCommand = commandDatabase.makeOneParameterCommand(commStack.pop().toString(), firstParameter);
      }
      else if (parametersNeeded == twoParametersNeeded){
        Number secondParameter = (Number) argStack.pop(); //must be in this order because the second parameter is popped off first
        Number firstParameter = (Number) argStack.pop();
        commandHistory = commStack.peek().toString() + " " + firstParameter.toString() + " " + secondParameter.toString();
        newCommand = commandDatabase.makeTwoParameterCommand(commStack.pop().toString(), firstParameter, secondParameter);
      }
//      System.out.println("ssas " + commandHistory);
      commandDatabase.addToHistory(commandHistory);
//      Number returnValue =
      newCommand.execute(); //change values to return a value
      argumentRunningTotal--;

      if(commStack.size() == 0){
        break;
      }
      //CommandFactory nextCommandFactory = new CommandFactory(commStack.peek().toString(), turtle);
      else if(argStack.size() <= argumentRunningTotal){
        //System.out.println("return" + newCommand.returnArgValue());
        argStack.push(newCommand.returnArgValue());
//        argStack.push(returnValue);
      }
//      System.out.println("AfterA" + argStack);
//      System.out.println("AfterC" + commStack);
    }
  }

//  public void setRunningTotal(int value){
//    argumentRunningTotal = value;
//    System.out.println(argumentRunningTotal);
//  }

}
