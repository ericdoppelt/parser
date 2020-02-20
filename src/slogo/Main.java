package slogo;


import javafx.scene.layout.Background;
import slogo.Commands.*;
import slogo.Commands.TurtleCommands.BackCommand;
import slogo.Commands.TurtleCommands.LeftCommand;
import slogo.Commands.TurtleCommands.RightCommand;

public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
      TurtleData turtle1 = new TurtleData(50, 50, 0);
      RightCommand command1 = new RightCommand(turtle1, 50);
      command1.execute();
    }
}
