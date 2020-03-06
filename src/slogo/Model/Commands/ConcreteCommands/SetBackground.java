package slogo.Model.Commands.ConcreteCommands;

import javafx.beans.property.ObjectProperty;
import slogo.Model.ColorData;
import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SetBackground extends Command {
    private CommandDatabase database;
    private static final int argumentsNeeded = 1;
    private int index;



    /**
     * Superconstructor for a Turtle Command
     *
     * @param data
     */
    public SetBackground(CommandDatabase data) {
        super(data);
        database = data;
    }

    @Override
    public Number executeAndReturnValue() {
        index = database.getParameterOne().intValue();
        ArrayList<Integer> color = (ArrayList<Integer>) database.getColorMap().get(index);
        database.setBackgroundColor(color);
        return index;
    }

    @Override
    public int getArgumentsNeeded() {
        return this.argumentsNeeded;
    }
}
