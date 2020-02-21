package slogo.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelDatabase {

  private List<TurtleData> turtleList = new ArrayList<>();

  // regular expression representing any whitespace characters (space, tab, or newline)
  private String language;

  // some examples to test for matching
//  private List<String> examples = List.of(
//      "",
//      "# foo",
//      "foo #",
//      "#",
//      "fd",
//      "FD",
//      "forwardd",
//      "equalp",
//      "equal?",
//      "equal??",
//      "+",
//      "SuM",
//      "-",
//      "*",
//      "/",
//      "%",
//      "~",
//      "+not",
//      "not+",
//      "++",
//      "+*+",
//      "or",
//      "FOR",
//      "allOrNothing",
//      "all_or_nothing",
//      "allOr_nothing?",
//      "allOr?nothing_",
//      ":allornothing",
//      "PI",
//      "90",
//      "9.09",
//      "9.0.0",
//      "[",
//      "]",
//      "(",
//      ")"
//  );

  /**
   * Main Database for instantiating turtles and parsing data
   *
   * @author Frank Tang
   */

    public ModelDatabase(){
      TurtleData originTurtle = new TurtleData("1",0,0,0);
      turtleList.add(originTurtle);
    }
  // try against different kinds of inputs
//      model.parseText(model, examples);
//      String fileInput = model.readFileToString(
//          Main.class.getClassLoader().getResource("\\resources\\languages\\square.logo").toExternalForm());
  // instead it will "comment out" the remainder of the program!
//      model.parseText(model, Arrays.asList(fileInput.split(WHITESPACE)));



    public void makeNewTurtle(String ID, double initX, double initY, double initHeading){
      TurtleData newTurtle = new TurtleData(ID, initX, initY, initHeading);
      turtleList.add(newTurtle);
    }

}
