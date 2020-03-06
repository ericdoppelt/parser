package slogo.Model.Commands.ConcreteCommands;

import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.Commands.Command;

public class SetPalette extends Command {

    private CommandDatabase database;
    private static final int argumentsNeeded = 4;

    /**
     * Superconstructor for a Turtle Command
     *
     * @param database
     */
    public SetPalette(CommandDatabase database) {
        super(database);
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
