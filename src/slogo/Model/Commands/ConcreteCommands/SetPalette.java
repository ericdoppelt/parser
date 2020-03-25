package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * SetPalette is the class for the command to add a color to the color map
 */

public class SetPalette extends Command {

    private CommandDatabase database;
    private static final int argumentsNeeded = 4;
    private int index;
    private List<Integer> rgb;
    /**
     * Superconstructor for a Turtle Command
     * Create a new arraylist to represent the rgb color value inputted by the command
     * @param data
     */
    public SetPalette(CommandDatabase data) {
        super(data);
        database = data;
        rgb = new ArrayList<>();
    }

    /**
     * Get the index of the color from the command
     * Creates a "color" from the rgb values from the command
     * Add the color to the color map with the index as its key
     * @return
     */
    @Override
    public Number executeAndReturnValue() {
        index = database.getParameterStack().pop().intValue();
        rgb.add(database.getParameterStack().pop().intValue());
        rgb.add(database.getParameterStack().pop().intValue());
        rgb.add(database.getParameterStack().pop().intValue());

        System.out.println(rgb.get(0) + " " + rgb.get(1) + " " + rgb.get(2));

        database.addToColorMap(index, rgb);

        return index;
    }

    @Override
    public int getArgumentsNeeded() {
        return this.argumentsNeeded;
    }
}
