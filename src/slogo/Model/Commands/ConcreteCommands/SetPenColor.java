package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

import java.util.ArrayList;

/**
 * SetPenColor is the class for the command to set the pen to a specific color given an index
 */

public class SetPenColor extends Command {

    private CommandDatabase database;
    private static final int argumentsNeeded = 1;
    private Number index;

    /**
     * Superconstructor for a Turtle Command
     *
     * @param data
     */
    public SetPenColor(CommandDatabase data) {
        super(data);
        database = data;
    }

    /**
     * Gets the index inputted in the command
     * Finds the corresponding color based on the index in the colormap
     * Sets the pen to the found color
     * @return the index inputted in the command
     */
    @Override
    public Number executeAndReturnValue() {
        index = database.getParameterStack().pop().intValue();
        ArrayList<Integer> color = (ArrayList<Integer>) database.getColorMap().get(index.intValue());
        database.setPenColor(color);
        return index;
    }

    public void defaultMap() {
        ArrayList<Integer> dummyColor1 = new ArrayList<>();
        dummyColor1.add(255);
        dummyColor1.add(0);
        dummyColor1.add(0);
        ArrayList<Integer> dummyColor2 = new ArrayList<>();
        dummyColor1.add(0);
        dummyColor1.add(255);
        dummyColor1.add(0);
        ArrayList<Integer> dummyColor3 = new ArrayList<>();
        dummyColor1.add(0);
        dummyColor1.add(0);
        dummyColor1.add(255);
        database.addToColorMap(0,dummyColor1);
    }

    @Override
    public int getArgumentsNeeded() {
        return this.argumentsNeeded;
    }
}
