package slogo.Model.Commands;

import java.util.Stack;
import slogo.Model.TurtleData;

public class CommandProducer {

  /**
   * Simple parser based on regular expressions that matches input strings to kinds of program elements.
   * @author Frank Tang
   */

  private TurtleData turtle = new TurtleData("yeet",50,50,0);
  private CommandFactory commandFactory;



  public CommandProducer(Stack commStack, Stack argStack){
    parseStacks(commStack, argStack);
  }


  /**
   * Adds the given resource file to this language's recognized types
   */
  public void parseStacks (Stack commStack, Stack argStack) {
    while (commStack.size() > 0){
      commandFactory = new CommandFactory(commStack.pop().toString(), turtle, Integer.parseInt(argStack.pop().toString()));
      Command newCommand = commandFactory.makeCommand();
      if(commStack.size() > argStack.size()){
        argStack.push(newCommand.returnArgValue());
      }
      new CommandExecuter(newCommand);
    }

  }

}
