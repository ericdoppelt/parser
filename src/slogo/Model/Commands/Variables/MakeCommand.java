package slogo.Model.Commands.Variables;

import slogo.Model.Commands.Command;
import slogo.Model.VariableData;
import java.util.Map;

public class MakeCommand extends Command {

    private Map<String, Integer> variableMap;
    private String variableName;
    private Integer variableValue;

    public MakeCommand(String name, Integer value, VariableData oldMap){
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
