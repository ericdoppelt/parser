package slogo.Model.Commands.Variables;

import slogo.Model.Commands.Command;
import slogo.Model.variableData;

import java.util.HashMap;
import java.util.Map;

public class makeCommand extends Command {

    private Map<String, Integer> variableMap;
    private String variableName;
    private Integer variableValue;

    public makeCommand(String name, Integer value, variableData oldMap){
        variableName = name;
        variableValue = value;
        variableMap = oldMap.getVariableMap();
    }

    @Override
    public void execute(){
        variableMap.put(variableName, variableValue);
    }

    @Override
    public Integer returnArgValue() {
        return variableValue;
    }
}
