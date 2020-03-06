package slogo.Model;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Pattern;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import slogo.Model.CommandInfrastructure.CommandDatabase;
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

  private static final String REGEX_SYNTAX = "Syntax";
  private List<Entry<String, Pattern>> mySymbols;
  private CommandDatabase commandDatabase;
  private CommandProducer commandProducer;
  private List<String> linesArray;
  private ObjectProperty languageChosen;
  private int currentIndex;
  private int argumentThreshold;
  private Number finalCommandValue;
  private Command1 argumentChecker;



  public ModelParser(String language, CommandDatabase commandData, CommandProducer producer){
    createBindableLanguage(language);

    commandDatabase = commandData;
    commandProducer = producer;
    commandDatabase.addParser(this);

  }

  private void createBindableLanguage(String language) {
    languageChosen = new SimpleObjectProperty<>(language);
    languageChosen.addListener((observable, oldValue, newValue) -> {
      setUpModelParserLanguage((String)newValue);
    });
    setUpModelParserLanguage(languageChosen.getValue().toString());
  }

  private void setUpModelParserLanguage(String language){
    mySymbols = new ArrayList<>();
    addPatterns(language);
    addPatterns(REGEX_SYNTAX);

  }

  public Property getParserLanguageProperty() {return languageChosen;}

  /**
   * Adds the given resource file to this language's recognized types
   */
  private void addPatterns (String syntax) {
    ResourceBundle resources = ResourceBundle.getBundle(syntax);
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
  private String getSymbol (String text) {
    final String ERROR = "NO MATCH";
    for (Entry<String, Pattern> e : mySymbols) {
      if (match(text, e.getValue())) {
        return e.getKey();
      }
    }
    // FIXME: perhaps throw an exception instead
    return ERROR;
  }

//  // utility function that reads given file and returns its entire contents as a single string
//  public String readFileToString (String inputSource) {
//    try {
//      // this one line is dense, hard to read, and throws exceptions so better to wrap in method
//      return new String(Files.readAllBytes(Paths.get(new URI(inputSource))));
//    }
//    catch (URISyntaxException | IOException e) {
//      // NOT ideal way to handle exception, but this is just a simple test program
//      System.out.println("ERROR: Unable to read input file " + e.getMessage());
//
//      //potential error pop-up code
////      String errorMessage = "ERROR: Unable to read input f ile " + e.getMessage();
////      Alert alert = new Alert(Alert.AlertType.ERROR);
////      alert.setTitle("Error");
////      alert.setHeaderText(errorMessage);
////      Platform.runLater(alert::showAndWait);
//      return "";
//    }
//  }

  // Returns true if the given text matches the given regular expression pattern
  private boolean match (String text, Pattern regex) {
    // THIS IS THE IMPORTANT LINE
    return regex.matcher(text).matches();
  }

  public int findListEnd(List<String> listToCheck){
    int listStartCounter = 0;
    int listEndCounter = 0;
    for(int i = 0; i < listToCheck.size(); i++){
      if(listToCheck.get(i).equals("]")){
        listEndCounter++;
      }
      else if(listToCheck.get(i).equals("[")){
        listStartCounter++;
      }
      if(listEndCounter == listStartCounter){
        return i;
      }
    }
    return 0;
  }

  // given some text, prints results of parsing it using the given language
  public Number parseText (List<String> inputCommandList) {
    Stack<String> commandStack = new Stack<>();
    Stack<Number> argumentStack = new Stack<>();
//    int argumentThreshold = 0;
    for (int index = 0; index < inputCommandList.size(); index++) {
      if (inputCommandList.get(index).trim().length() > 0) {
        currentIndex = index;
        linesArray = inputCommandList.subList(index, inputCommandList.size());
        commandDatabase.setListArray(linesArray);
        if(this.getSymbol(inputCommandList.get(index)).equals("Constant")){
          argumentStack.push(Double.parseDouble(inputCommandList.get(index)));
        }

        else if(this.getSymbol(inputCommandList.get(index)).equals("Variable")){
          if(commandStack.peek().equals("MakeVariable")){
            commandDatabase.setVariableName(inputCommandList.get(index));
          }
          else{
            argumentStack.push((Number) commandDatabase.getVariableMap().get(inputCommandList.get(index)));
          }
        }
        else if(this.getSymbol(inputCommandList.get(index)).equals("ListStart")){
          int listEnd = findListEnd(linesArray);
          index = listEnd + index;
          continue;
        }
        else if(checkCommand(this.getSymbol(inputCommandList.get(index)))){
          commandStack.push(this.getSymbol(inputCommandList.get(index)));
          argumentThreshold = argumentStack.size() + argumentChecker.getArgumentsNeeded();
        }
        finalCommandValue = commandProducer.parseStacks(commandStack, argumentStack, argumentThreshold);
      }
    }
    return finalCommandValue;

  }

  public boolean checkCommand(String commandName){
    try {
      Class commandClass = Class.forName("slogo.Model.Commands.TurtleCommands." + commandName + "Command");
      Object o = commandClass.getDeclaredConstructors()[0].newInstance(commandDatabase);
      argumentChecker = (Command1) o;
      return true;
    }
    catch (Exception e){
      e.printStackTrace();
      // TODO: FIX THIS SO WE DON'T FAIL
    }
    return false;
  }


  public List<String> getLinesArray(){
    return linesArray;
  }

  public Integer getCurrentLinesIndex(){
    return currentIndex;
  }


}
