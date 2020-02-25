package slogo.Model;

import java.util.HashMap;
import java.util.Map;

public class VariableData {
    private Map<String, Integer> variableMap;

    public VariableData(){
        variableMap = new HashMap<>();
    }

    public Map<String, Integer> getVariableMap() {
        return variableMap;
    }
}
