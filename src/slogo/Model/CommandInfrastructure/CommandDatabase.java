package slogo.Model.CommandInfrastructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import slogo.Model.ModelParser;
import slogo.Model.TurtleData;

public class CommandDatabase {

  private String targetVariable;
  private Number parameterOne;
  private Number parameterTwo;
  private MapProperty<String, Number> VARIABLE_MAP = new SimpleMapProperty(
      FXCollections.observableMap(new LinkedHashMap<String, Number>()));

  private Function<List<String>, Number> parseFunction;
  private Function<List<String>, Number> listFunction;
  private TurtleData targetTurtle;
  private List<TurtleData> active_Turtles = new ArrayList<>();
  private ModelParser originParser;
  private List<String> currentLineArray;

  public CommandDatabase(TurtleData turtle) {
    targetTurtle = turtle;
  }

  public TurtleData getTurtle() {
    return targetTurtle;
  }

  public Number getParameterOne() {
    return parameterOne;
  }
  public Number getParameterTwo() {
    return parameterTwo;
  }


  public void setListArray(List<String> array) {
    currentLineArray = array;
  }

  public Function<List<String>, Number> getParseFunction() {
    return parseFunction;
  }
  public Function<List<String>, Number> getListFunction() {
    return listFunction;
  }

  public List<String> getCurrentLineArray() {
    return currentLineArray;
  }



  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public void addParser(ModelParser parser) {
    originParser = parser;
    parseFunction = d -> originParser.parseText(d);
    listFunction = l -> originParser.findListEnd(l);
//    updateCommandMap();
  }



  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public void setParameterOne(Number newValue) {
    parameterOne = newValue;
  }

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public void setParameterTwo(Number newValue) {
    parameterTwo = newValue;
  }

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public void setVariableName(String targetCommand) {
    targetVariable = targetCommand;
  }


  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public MapProperty getVariableMap() {
    return this.VARIABLE_MAP;
  }

  public void bindVariables(MapProperty displayedVariables) {
    displayedVariables.bind(VARIABLE_MAP);
  }


  public void addToVariableMap(String command, Number expression) {
    this.VARIABLE_MAP.putIfAbsent(command, expression);
    this.VARIABLE_MAP.put(command, expression);
  }


}
