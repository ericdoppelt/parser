package slogo.Model.Commands;

import java.util.Stack;
import slogo.Model.TurtleData;

public class CommandProducer {

  /**
   * Simple parser based on regular expressions that matches input strings to kinds of program elements.
   * @author Frank Tang
   */

  private CommandFactory commandFactory;
  private TurtleData turtle;



  public CommandProducer(Stack commStack, Stack argStack, TurtleData turtleObject){
    turtle = turtleObject;
    parseStacks(commStack, argStack);
  }


  /**
   * Adds the given resource file to this language's recognized types
   */
  public void parseStacks (Stack commStack, Stack argStack) {
    while (commStack.size() > 0 && argStack.size() > 0){
      commandFactory = new CommandFactory(commStack.pop().toString(), turtle, Integer.parseInt(argStack.pop().toString()));
      Command newCommand = commandFactory.makeCommand();
      if(commStack.size() > argStack.size()){
//        System.out.println("BeforeA" + argStack);
//        System.out.println("BeforeC" + commStack);
        argStack.push(newCommand.returnArgValue());
//        System.out.println("AfterA" + argStack);
//        System.out.println("AfterC" + commStack);
     }
      new CommandExecuter(newCommand);
    }

  }

}
