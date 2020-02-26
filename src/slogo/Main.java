package slogo;


import java.util.Arrays;
import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.ModelParser;
import slogo.Model.TurtleData;

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
    TurtleData turtle = new TurtleData("help", 0,0,0);
    CommandDatabase database = new CommandDatabase(turtle);
    ModelParser modelParser = new ModelParser("English", database);
//    String userInput = "fd sum 90 sum 50 fd 90 sum 70 - 80 50";
    String userInput = "fd 50 repeat 5 [ fd 50 fd 50 ] repeat 5 [ fd 50 fd 50 ]";
    modelParser.initializeNewParserTextandParse(Arrays.asList(userInput.split(WHITESPACE)));




  }

}

