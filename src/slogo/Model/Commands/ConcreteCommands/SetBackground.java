package slogo.Model.Commands.ConcreteCommands;

import java.util.ArrayList;
import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

/**
 * SetPenColor is the class for the command to set the background to a specific color given an index
 */

public class SetBackground extends Command {
    private CommandDatabase database;
    private static final int argumentsNeeded = 1;
    private Number index;

    /**
     * Superconstructor for a Turtle Command
     *
     * @param data
     */
    public SetBackground(CommandDatabase data) {
        super(data);
        database = data;
    }

    /**
     * Gets the color index from the command input
     * Sets the background color to the color retrieved from colormap
     * @return the index of the color within color map
     */
    @Override
    public Number executeAndReturnValue() {
        index = database.getParameterStack().pop();
        ArrayList<Integer> color = (ArrayList<Integer>) database.getColorMap().get(index.intValue());
        database.setBackgroundColor(color);
        return index;
    }

    @Override
    public int getArgumentsNeeded() {
        return this.argumentsNeeded;
    }
}
