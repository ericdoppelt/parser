//package slogo.Model.Commands.ConcreteCommands;
//
//import slogo.Model.CommandInfrastructure.CommandDatabase;
//import slogo.Model.Commands.Command;
//
///**
// * Subclass to create a BackCommand
// *
// * @author Frank Tang
// */
//public class MakeVariable extends Command {
//
//  private double returnArgValue = 0;
//  private Number expression;
//  private String variable;
//  private CommandDatabase dataBase;
//
//  private static final int argumentsNeeded = 1;
//  private CommandDatabase database;
//
//
//
//  public MakeVariable(CommandDatabase data) {
//    super(data);
//    database = data;
//
////    System.out.println("test" + parameterOne);
//  }
//
//  /**
//   * Moves the turtle backwards by a pixel amount.
//   */
//  @Override
//  public Double executeAndReturnValue() {
//    variable = variableName;
//    expression = dataBase.getParameterOne();
////    System.out.println(variable);
////    System.out.println(expression.doubleValue());
////    dataBase.getVariables().putIfAbsent(variable, expression.doubleValue());
//    dataBase.addToVariableMap(variable, expression.doubleValue());
//    returnArgValue = expression.doubleValue();
//    return this.returnArgValue;
//  }
//  @Override
//  public int getArgumentsNeeded(){
//    return this.argumentsNeeded;
//  }
//
//
//}
//
//
