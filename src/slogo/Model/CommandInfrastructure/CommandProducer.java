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
      System.out.println("BeforeA" + argStack);
      System.out.println("BeforeC" + commStack);
      double parametersNeeded = commandDatabase.getAmountOfParametersNeeded(commStack.peek().toString());
      Command newCommand = null;
      if(parametersNeeded == zeroParametersNeeded){
        newCommand = commandDatabase.makeZeroParameterCommand(commStack.pop().toString());
      }
      else if(parametersNeeded == oneParametersNeeded) {
        Integer firstParameter = Integer.parseInt(argStack.pop().toString());
        newCommand = commandDatabase.makeOneParameterCommand(commStack.pop().toString(), firstParameter);
      }
      else if (parametersNeeded == twoParametersNeeded){
        Integer secondParameter = Integer.parseInt(argStack.pop().toString()); //must be in this order because the second parameter is popped off first
        Integer firstParameter = Integer.parseInt(argStack.pop().toString());
        newCommand = commandDatabase.makeTwoParameterCommand(commStack.pop().toString(), firstParameter, secondParameter);
      }
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
      System.out.println("AfterA" + argStack);
      System.out.println("AfterC" + commStack);
    }
  }

//  public void setRunningTotal(int value){
//    argumentRunningTotal = value;
//    System.out.println(argumentRunningTotal);
//  }

}
