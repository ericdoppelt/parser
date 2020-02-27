package slogo.View;

import java.io.FileNotFoundException;

public class TurtleException extends NullPointerException {
    public TurtleException(String message, Object... values) {
        super(String.format(message, values));
    }


    /**
     * Create an exception based on a caught exception, with no additional message.
     */
    public TurtleException (Throwable exception) {
        super(String.valueOf(exception));
    }

}
