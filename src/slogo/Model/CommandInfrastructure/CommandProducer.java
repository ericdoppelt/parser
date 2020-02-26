package slogo.Model.CommandInfrastructure;

import java.util.Stack;
import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

public class CommandProducer {

  /**
   * Simple parser based on regular expressions that matches input strings to kinds of program elements.
   * @author Frank Tang
   */

  private CommandFactory commandFactory;
  private TurtleData turtle;
  private static int argumentRunningTotal;
  private static final int zeroParametersNeeded = 0;
  private static final int oneParametersNeeded = 1;
  private static final int twoParametersNeeded = 2;

  public CommandProducer(TurtleData turtleObject){
    turtle = turtleObject;
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
      commandFactory = new CommandFactory(commStack.pop().toString(), turtle);
      double parametersNeeded = commandFactory.getAmountOfParametersNeeded();
      Command newCommand = null;
      if(parametersNeeded == zeroParametersNeeded){
        newCommand = commandFactory.makeZeroParameterCommand();
      }
      else if(parametersNeeded == oneParametersNeeded) {
        Integer firstParameter = Integer.parseInt(argStack.pop().toString());
        newCommand = commandFactory.makeOneParameterCommand(firstParameter);
      }
      else if (parametersNeeded == twoParametersNeeded){
        Integer secondParameter = Integer.parseInt(argStack.pop().toString()); //must be in this order because the second parameter is popped off first
        Integer firstParameter = Integer.parseInt(argStack.pop().toString());
        newCommand = commandFactory.makeTwoParameterCommand(firstParameter, secondParameter);
      }
      new CommandExecuter(newCommand);
      argumentRunningTotal--;

      if(commStack.size() == 0){
        break;
      }
      //CommandFactory nextCommandFactory = new CommandFactory(commStack.peek().toString(), turtle);
      if(argStack.size() <= argumentRunningTotal){
        //System.out.println("return" + newCommand.returnArgValue());
        argStack.push(newCommand.returnArgValue());
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
