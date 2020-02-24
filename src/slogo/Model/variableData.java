package slogo.Model;

import java.util.HashMap;
import java.util.Map;

public class variableData {
    private Map<String, Integer> variableMap;

    public variableData(){
        variableMap = new HashMap<>();
    }

    public Map<String, Integer> getVariableMap() {
        return variableMap;
    }
}
