
import java.util.*;

/**
 * This interface was developed to give a general idea of how the front end will look like to an outside class.
 * In general, the front end will simply take a in a string and deliver it to the controller to be processed.
 */
public interface UserInterface {

    /**
     * This method allows a given class to retrieve the command that has just been given the the view
     */
    public void getCommand();

    /**
     * This method allows the controller/model to accept an exception warning from the Controller in case there is any error with user-given
     * strings.
     * @Param Exception that will be displayed in Internal Frontend
     */
    public void throwException(Exception E);

    /**
     * This method allows a class to send the result of a command back to the view to be udpated in the Grid and Turtle.
     */
    public void sendCommand();
}