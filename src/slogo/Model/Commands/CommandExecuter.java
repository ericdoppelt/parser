package slogo.Model.Commands;

import java.util.List;

public class CommandExecuter {

  /**
   * Simple parser based on regular expressions that matches input strings to kinds of program elements.
   * @author Frank Tang
   */

  public CommandExecuter(List<Command> commandList){
    executeCommands(commandList);
  }

  /**
   * Adds the given resource file to this language's recognized types
   */
  public void executeCommands (List<Command> commandList) {
    for (int i = 0; i < commandList.size(); i++){
      commandList.get(i).execute();
    }
  }

}
