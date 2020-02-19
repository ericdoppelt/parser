
import java.util.*;

/**
 * This interface was developed to give a general idea of how the front end will look like to an outside class.
 * In general, the front end will simply take a in a string and deliver it to the controller to be processed.
 */
public interface FrontEnd External {

    /**
     * This method allows a given classs to retrive the command that has just been given the the view
     */
    public void getCommand();

    /**
     * This method allows the controller/model to trigger an error warning in case their is any error with user-given
     * string.
     */
    public void throwError();

    /**
     * This method allows a class to send the result of a command back to the view.
     */
    public void sendCommand();

}