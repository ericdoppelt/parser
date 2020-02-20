package slogo;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import slogo.Model.ModelParser;

public class Main {
  // regular expression representing any whitespace characters (space, tab, or newline)
  public static final String WHITESPACE = "\\s+";
  // some examples to test for matching
  private List<String> examples = List.of(
      "",
      "# foo",
      "foo #",
      "#",
      "fd",
      "FD",
      "forwardd",
      "equalp",
      "equal?",
      "equal??",
      "+",
      "SuM",
      "-",
      "*",
      "/",
      "%",
      "~",
      "+not",
      "not+",
      "++",
      "+*+",
      "or",
      "FOR",
      "allOrNothing",
      "all_or_nothing",
      "allOr_nothing?",
      "allOr?nothing_",
      ":allornothing",
      "PI",
      "90",
      "9.09",
      "9.0.0",
      "[",
      "]",
      "(",
      ")"
  );
  /**
     * Start of the program.
     */
  public static void main (String[] args) {
//      TurtleData turtle1 = new TurtleData(50, 50, 0);
//      RightCommand command1 = new RightCommand(turtle1, 50);
//      command1.execute();
    Main m = new Main();

    ModelParser model = new ModelParser();
    // set up the parser, which checks for matches in order given
    // these are more specific, so add them first to ensure they are checked first
    model.addPatterns("resources\\languages\\English");
    // general checks, added last
    model.addPatterns("resources\\languages\\Syntax");

    // try against different kinds of inputs
    m.parseText(model, m.examples);
    String userInput = "fd 50 rt 90 BACK :distance Left :angle";
    // note, this simple "algorithm" will not handle SLogo comments
    m.parseText(model, Arrays.asList(userInput.split(WHITESPACE)));
    String fileInput = m.readFileToString(Main.class.getClassLoader().getResource("square.logo").toExternalForm());
    // instead it will "comment out" the remainder of the program!
    m.parseText(model, Arrays.asList(fileInput.split(WHITESPACE)));
    }

  // utility function that reads given file and returns its entire contents as a single string
  private String readFileToString (String inputSource) {
    try {
      // this one line is dense, hard to read, and throws exceptions so better to wrap in method
      return new String(Files.readAllBytes(Paths.get(new URI(inputSource))));
    }
    catch (URISyntaxException | IOException e) {
      // NOT ideal way to handle exception, but this is just a simple test program
      System.out.println("ERROR: Unable to read input file " + e.getMessage());
      return "";
    }
  }

  // given some text, prints results of parsing it using the given language
  private void parseText (ModelParser lang, List<String> lines) {
    for (String line : lines) {
      if (line.trim().length() > 0) {
        System.out.println(String.format("%s : %s", line, lang.getSymbol(line)));
      }
    }
    System.out.println();
  }


}

