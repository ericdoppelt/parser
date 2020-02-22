package slogo.Model.Commands;

public class CommandExecuter {

  /**
   * Simple parser based on regular expressions that matches input strings to kinds of program elements.
   * @author Frank Tang
   */

  public CommandExecuter(Command commandToExecute){
    commandToExecute.execute();
  }
}
