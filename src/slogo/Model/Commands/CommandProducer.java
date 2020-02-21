package slogo.Model.Commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import slogo.Model.TurtleData;

public class CommandProducer {

  /**
   * Simple parser based on regular expressions that matches input strings to kinds of program elements.
   * @author Frank Tang
   */

  private List<Command> newCommands = new ArrayList<>();
  private TurtleData turtle = new TurtleData("yeet",50,50,50);
  private CommandFactory commandFactory;



  public CommandProducer(Stack commStack, Stack argStack){
    parseStacks(commStack, argStack);
  }


  /**
   * Adds the given resource file to this language's recognized types
   */
  public void parseStacks (Stack commStack, Stack argStack) {
    for (int i = 0; i < commStack.size(); i++){
      commandFactory = new CommandFactory(commStack.pop().toString(), turtle, Integer.parseInt(argStack.pop().toString()));
      Command commandMade = commandFactory.makeCommand();
      newCommands.add(commandMade);
    }
    new CommandExecuter(newCommands);
  }

}
