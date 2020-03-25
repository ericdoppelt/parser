package slogo.Model.Commands.ConcreteCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;
import slogo.Model.TurtleData;

/**
 * Sets turtles that will follow commands when invoked
 */

public class Tell extends Command {

    private int returnArgValue;
    private static final int argumentsNeeded = 0;
    private List<String> linesSubArray;
    private CommandDatabase database;
    private Function<List<String>, Number> listEndFunction;
    private List<TurtleData> turtleList;
    private List<String> turtleIdList;

    /**
     * Superconstructor for a Turtle Command
     *
     * @param data
     */
    public Tell(CommandDatabase data) {
        super(data);
        turtleIdList = new ArrayList<>();
        database = data;
    }

    /**
     * Creates new turtles with id's as noted on the inputted commands
     * Activate each new turtle as true or existing turtle from false to true
     * @return value of last turtle in list
     */
    @Override
    public Number executeAndReturnValue() {
        setTurtleIds();
        listEndFunction = database.getListFunction();
        linesSubArray = database.getCurrentLineArray();
        List<String> tempList = linesSubArray.subList(linesSubArray.indexOf("["), linesSubArray.size());
        int listEnd = listEndFunction.apply(tempList).intValue();
        linesSubArray = tempList.subList(1, listEnd);

        returnArgValue = Integer.parseInt(linesSubArray.get(linesSubArray.size()-1));

        for(String id : linesSubArray){
            if(turtleIdList.contains(id)){
                int index = turtleIdList.indexOf(id);
                turtleList.get(index).setTurtleActive(true);
            } else {
                TurtleData turtle = new TurtleData(id, 0, 0, 0);
                turtle.setTurtleActive(true);
                turtleList.add(turtle);
            }
        }

        database.setTurtleList(turtleList);

        setTurtleIds();
        for(TurtleData turtle: turtleList){
            System.out.println(turtle.getTurtleID());
            System.out.println(turtle.getTurtleActive());

        }
        System.out.println(returnArgValue);
      System.out.println(turtleList);

        return returnArgValue;
    }

    private void setTurtleIds(){
        turtleList = database.getTurtleList();
        for(TurtleData turtle : turtleList){
            turtleIdList.add(turtle.getTurtleID());
        }
    }

    @Override
    public int getArgumentsNeeded() {
        return this.argumentsNeeded;
    }
}
