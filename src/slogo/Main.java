package slogo;


import java.util.Arrays;
import slogo.Model.ModelParser;

public class Main {

  /**
     * Start of the program.
     */
  public static final String WHITESPACE = "\\s+";
  public static void main (String[] args) {
//      TurtleData turtle1 = new TurtleData(50, 50, 0);
//      RightCommand command1 = new RightCommand(turtle1, 50);
//      command1.execute();
    //ModelDatabase m = new ModelDatabase();
    ModelParser modelParser = new ModelParser("English");
//    String userInput = "fd sum 90 sum 50 fd 90 sum 70 - 80 50";
    String userInput = "SETXY 50 50";
    modelParser.parseText(Arrays.asList(userInput.split(WHITESPACE)));




  }

}

