package slogo.Model.CommandInfrastructure;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.collections.FXCollections;
import javafx.util.Pair;
import slogo.Model.Commands.Command1;
import slogo.Model.ModelParser;
import slogo.Model.TurtleData;

public class CommandDatabase {

  private String targetVariable;
  private static final Integer zeroParameterNeeded = 0;
  private static final Integer oneParameterNeeded = 1;
  private static final Integer twoParametersNeeded = 2;
  private Number parameterOne;
  private Number parameterTwo;
  private Map<String, Pair<Command1, Integer>> POSSIBLE_COMMANDS_MAP;
  private MapProperty<String, Number> VARIABLE_MAP = new SimpleMapProperty(
      FXCollections.observableMap(new LinkedHashMap<String, Number>()));


  private List<TurtleData> active_Turtles = new ArrayList<>();
  private TurtleData targetTurtle;
  private ModelParser originParser;

  public CommandDatabase(TurtleData turtle) {
    targetTurtle = turtle;
  }

  public TurtleData getTurtle() {
    return targetTurtle;
  }

  public Number getParameterOne() {
    return parameterOne;
  }


  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public void addParser(ModelParser parser) {
    originParser = parser;
//    updateCommandMap();
  }

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public Command1 makeZeroParameterCommand(String targetCommand) {
//    updateCommandMap();
    return POSSIBLE_COMMANDS_MAP.get(targetCommand).getKey();
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


//  /**
//   * Prompt the user to make a bet from a menu of choices.
//   */
//  public Command makeOneParameterCommand(String targetCommand, Number value) {
//    parameterOne = value;
//    updateCommandMap();
//    return POSSIBLE_COMMANDS_MAP.get(targetCommand).getKey();
//  }

//  /**
//   * Prompt the user to make a bet from a menu of choices.
//   */
//  public Command makeTwoParameterCommand(String targetCommand, Number value1, Number value2) {
//    parameterOne = value1;
//    parameterTwo = value2;
//    updateCommandMap();
//    return POSSIBLE_COMMANDS_MAP.get(targetCommand).getKey();
//  }

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public Integer getAmountOfParametersNeeded(String targetCommand) {
    return POSSIBLE_COMMANDS_MAP.get(targetCommand).getValue();
  }

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
  public boolean isInCommandMap(String targetCommand) {
    return POSSIBLE_COMMANDS_MAP.containsKey(targetCommand);
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

  /**
   * Prompt the user to make a bet from a menu of choices.
   */
//  private void updateCommandMap() {
//    POSSIBLE_COMMANDS_MAP = Map.ofEntries(
//        //Zero Parameter Commands
//        entry("PenUp", new Pair<>(new PenUpCommand(targetTurtle), zeroParameterNeeded)),
//        entry("PenDown", new Pair<>(new PenDownCommand(targetTurtle), zeroParameterNeeded)),
//        entry("ShowTurtle", new Pair<>(new ShowTurtleCommand(targetTurtle), zeroParameterNeeded)),
//        entry("HideTurtle", new Pair<>(new HideTurtleCommand(targetTurtle), zeroParameterNeeded)),
//        entry("Home", new Pair<>(new HomeCommand(targetTurtle), zeroParameterNeeded)),
//        entry("ClearScreen", new Pair<>(new ClearScreenCommand(targetTurtle), zeroParameterNeeded)),
//        entry("Pi", new Pair<>(new PiCommand(), zeroParameterNeeded)),
//        entry("XCoordinate", new Pair<>(new XCoordinateCommand(targetTurtle), zeroParameterNeeded)),
//        entry("YCoordinate", new Pair<>(new YCoordinateCommand(targetTurtle), zeroParameterNeeded)),
//        entry("Heading", new Pair<>(new HeadingCommand(targetTurtle), zeroParameterNeeded)),
//        entry("IsShowing", new Pair<>(new IsShowingCommand(targetTurtle), zeroParameterNeeded)),
//        entry("IsPenDown", new Pair<>(new IsPenDownCommand(targetTurtle), zeroParameterNeeded)),
//
//        //One Parameter Commands
//        entry("Forward", new Pair<>(new ForwardCommand(targetTurtle, parameterOne), oneParameterNeeded)),
//        entry("Backward", new Pair<>(new BackCommand(targetTurtle, parameterOne), oneParameterNeeded)),
//        entry("Left", new Pair<>(new LeftCommand(targetTurtle, parameterOne), oneParameterNeeded)),
//        entry("Right", new Pair<>(new RightCommand(targetTurtle, parameterOne), oneParameterNeeded)),
//        entry("SetHeading", new Pair<>(new SetHeadingCommand(targetTurtle, parameterOne), oneParameterNeeded)),
//        entry("Random", new Pair<>(new RandomCommand(parameterOne), oneParameterNeeded)),
//        entry("Sine", new Pair<>(new SineCommand(parameterOne), oneParameterNeeded)),
//        entry("Cosine", new Pair<>(new CosineCommand(parameterOne), oneParameterNeeded)),
//        entry("Tangent", new Pair<>(new TangentCommand(parameterOne), oneParameterNeeded)),
//        entry("ArcTangent", new Pair<>(new ArcTangentCommand(parameterOne), oneParameterNeeded)),
//        entry("NaturalLog", new Pair<>(new NaturalLogCommand(parameterOne), oneParameterNeeded)),
//        entry("Minus", new Pair<>(new MinusCommand(parameterOne), oneParameterNeeded)),
//        entry("MakeVariable", new Pair<>(new MakeVariableCommand(targetVariable, parameterOne, this), oneParameterNeeded)),
//        //Two Parameter Commands
//        entry("Sum", new Pair<>(new SumCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("Difference", new Pair<>(new DifferenceCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("Product", new Pair<>(new ProductCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("Quotient", new Pair<>(new QuotientCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("Remainder", new Pair<>(new RemainderCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("SetTowards", new Pair<>(new TowardsCommand(targetTurtle, parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("SetPosition", new Pair<>(new SetPositionCommand(targetTurtle, parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("Power", new Pair<>(new PowerCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("And", new Pair<>(new AndCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("Or", new Pair<>(new OrCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("NotEqual", new Pair<>(new NotEqualCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("Equal", new Pair<>(new EqualCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("Not", new Pair<>(new NotCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("LessThan", new Pair<>(new LessThanCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        entry("GreaterThan", new Pair<>(new GreaterThanCommand(parameterOne, parameterTwo), twoParametersNeeded)),
//        //
//        // TODO: make into reflection
//
//
//
//        //Control Parameter Commands
//        entry("Repeat", new Pair<>(new RepeatCommand(parameterOne, originParser.getLinesArray(),
//            d -> originParser.parseText(d), l -> originParser.findListEnd(l)), oneParameterNeeded)),
//        entry("DoTimes", new Pair<>(new DoTimesCommand(originParser, this), zeroParameterNeeded)),
//        entry("If", new Pair<>(new IfCommand(parameterOne, originParser), oneParameterNeeded)),
//        entry("IfElse", new Pair<>(new IfElseCommand(parameterOne, originParser), oneParameterNeeded)),
//        entry("For", new Pair<>(new ForCommand(originParser, this), zeroParameterNeeded))
//    );
//
//  }
//}
//
}
