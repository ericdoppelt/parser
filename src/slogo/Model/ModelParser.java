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
  private int argumentThreshold;
  private List<String> linesArray;
  private ObjectProperty languageChosen;


  public ModelParser(String language, CommandDatabase commandData){
    createBindableLanguage(language);

    commandDatabase = commandData;
    commandData.addParser(this);
    commandProducer = new CommandProducer(commandData);
  }

  private void createBindableLanguage(String language) {
    languageChosen = new SimpleObjectProperty<>(language);
    languageChosen.addListener((observable, oldValue, newValue) -> {
      setUpModelParserLanguage((String)newValue);
    });
    setUpModelParserLanguage(languageChosen.getValue().toString());
  }

  public void setUpModelParserLanguage(String language){
    mySymbols = new ArrayList<>();
    addPatterns(language);
    addPatterns(REGEX_SYNTAX);

  }

  public Property getParserLanguageProperty() {return languageChosen;}

  /**
   * Adds the given resource file to this language's recognized types
   */
  public void addPatterns (String syntax) {
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

      //potential error pop-up code
//      String errorMessage = "ERROR: Unable to read input f ile " + e.getMessage();
//      Alert alert = new Alert(Alert.AlertType.ERROR);
//      alert.setTitle("Error");
//      alert.setHeaderText(errorMessage);
//      Platform.runLater(alert::showAndWait);
      return "";
    }
  }

  // Returns true if the given text matches the given regular expression pattern
  private boolean match (String text, Pattern regex) {
    // THIS IS THE IMPORTANT LINE
    return regex.matcher(text).matches();
  }

  public void initializeNewParserTextandParse (List<String> lines) {
    linesArray = lines;
    parseText(lines);
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
  public void parseText (List<String> inputCommandList) {
    Stack<String> commandStack = new Stack<>();
    Stack<Number> argumentStack = new Stack<>();
    for (int index = 0; index < inputCommandList.size(); index++) {
      if (inputCommandList.get(index).trim().length() > 0) {
        linesArray = inputCommandList.subList(index, inputCommandList.size());
        if(this.getSymbol(inputCommandList.get(index)).equals("Constant")){
          argumentStack.push(Double.parseDouble(inputCommandList.get(index)));
        }
        else if(commandDatabase.isInCommandMap(this.getSymbol(inputCommandList.get(index)))) {
          commandStack.push(this.getSymbol(inputCommandList.get(index)));
          argumentThreshold = argumentStack.size() + commandDatabase.getAmountOfParametersNeeded(commandStack.peek());
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
        commandProducer.parseStacks(commandStack, argumentStack, argumentThreshold);
      }
    }

  }

  public List<String> getLinesArray(){
    return linesArray;
  }

}
