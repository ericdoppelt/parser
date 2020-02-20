package slogo;

import slogo.Commands.TurtleCommands.ForwardCommand;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
      TurtleData hello = new TurtleData(50, 50, 50);
      ForwardCommand hs = new ForwardCommand(hello, 50);
      hs.execute();

    }
}
