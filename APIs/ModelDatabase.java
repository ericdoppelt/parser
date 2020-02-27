
import slogo.Model.Commands.Command;

import java.util.*;

/**
 * ModelDatabase is the API that represents the internal backend of the program. This API will be responsible for making the
 * information packages that will eventually to the model, make and execute the commands of the program, and make History objects
 * that keep track of the history of commands of a turtle.
 */
public interface ModelDatabase {

    /**
     * Will execute the Command that is passed in the parameter. Used to specify which turtle the command is for.
     */
    public void executeCommand(Command nextCommand);

    /**
     * Will add the command information of a Turtle to a History List in the specific Turtle.
     */
    public void addHistory();

    /**
     * Gets the History of commands from a Turtle's History List when called.
     */
    public void getHistory();

}