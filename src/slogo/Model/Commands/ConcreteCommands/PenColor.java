package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

public class PenColor extends Command {

    private CommandDatabase database;
    private static final int argumentsNeeded = 0;
    private int index;

    /**
     * Superconstructor for a Turtle Command
     *
     * @param data
     */
    public PenColor(CommandDatabase data) {
        super(data);
        database = data;
    }

    @Override
    public Number executeAndReturnValue() {
        return null;
    }

    @Override
    public int getArgumentsNeeded() {
        return 0;
    }
}
