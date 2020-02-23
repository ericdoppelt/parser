package slogo.Model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Pattern;
import slogo.Model.CommandInfrastructure.CommandFactory;
import slogo.Model.CommandInfrastructure.CommandProducer;

public class ModelParser {

  private enum ParserEnum{
    Constant,
    Variable,
    Command,
    List,
    Comment
  }

  private ParserEnum symbolName;

  /**
   * Simple parser based on regular expressions that matches input strings to kinds of program elements.
   * Based off of ProgramParser by Robert Duvall
   * 2/20/2020
   * @author Frank Tang
   */

  private String commandFromController;
  private static final String RESOURCES_PACKAGE = "resources/languages/";
  private static final String REGEX_SYNTAX = "Syntax";
  private List<Entry<String, Pattern>> mySymbols;
  private TurtleData turtle = new TurtleData("yeet",0,0,45);
  private int argumentThreshold;

  public ModelParser(String language){
    mySymbols = new ArrayList<>();
    addPatterns(language);
    addPatterns(REGEX_SYNTAX);
//    commandFromController = inputString;
  }

  public TurtleData getMyTurtle(){
    return turtle;
  }


  /**
   * Adds the given resource file to this language's recognized types
   */
  public void addPatterns (String syntax) {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
    for (String key : Collections.list(resources.getKeys())) {
      String regex = resources.getString(key);
      mySymbols.add(new SimpleEntry<>(key,
          // THIS IS THE IMPORTANT LINE
          Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
    }
  }

  /**
   * Returns language's type associated with the given text if one exists
   */
  public String getSymbol (String text) {
    final String ERROR = "NO MATCH";
    for (Entry<String, Pattern> e : mySymbols) {
      if (match(text, e.getValue())) {
        return e.getKey();
      }
    }
    // FIXME: perhaps throw an exception instead
    return ERROR;
  }

  // utility function that reads given file and returns its entire contents as a single string
  public String readFileToString (String inputSource) {
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

  // Returns true if the given text matches the given regular expression pattern
  private boolean match (String text, Pattern regex) {
    // THIS IS THE IMPORTANT LINE
    return regex.matcher(text).matches();
  }

  // given some text, prints results of parsing it using the given language
  public void parseText (List<String> lines) {
    //System.out.println(lines);
    Stack<String> commandStack = new Stack<>();
    Stack<Integer> argumentStack = new Stack<>();
    for (String line : lines) {
      if (line.trim().length() > 0) {
        //enum stuff that will probably used for the final implementation
//        System.out.print(this.getSymbol(line));
//        symbolName = ParserEnum.valueOf(this.getSymbol(line));
//        switch (symbolName){
//          case Constant:
//            argumentStack.push(Integer.parseInt(line));
//          case Variable:
//          case Command:
//            commandStack.push(this.getSymbol(line));
//          case Comment:
//          case List:
//        }
        CommandProducer commandProducer = new CommandProducer(turtle);
        if(this.getSymbol(line).equals("Constant")){
          argumentStack.push(Integer.parseInt(line));
        }
        else {
          commandStack.push(this.getSymbol(line));
          CommandFactory parameterGetter = new CommandFactory(commandStack.peek(), turtle);
          argumentThreshold = argumentStack.size() + parameterGetter.getAmountOfParametersNeeded();

        }
        System.out.println("Before Parse: " + commandStack);
        System.out.println("Before Parse: " + argumentStack);
        commandProducer.parseStacks(commandStack, argumentStack, argumentThreshold);
        System.out.println("After Parse: " + commandStack);
        System.out.println("After Parse: " + argumentStack);
      }
    }

  }

}
