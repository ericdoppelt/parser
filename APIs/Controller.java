
import java.util.*;

/**
 * Backend External is reponsible for methods that connect the model and controller to the view
 */
public interface Controller {

    /**
     * getInfoPackage will reside within the model package
     * The controller class will call upon this method to receive the infopackage
     * that is to be sent to the view for visualization
     */
    public void getInfoPackage();

    /**
     * Send the information package of the executed command to the view package to be visualized
     */
    public void sendInfoPackage();

    /**
     * Send the inputted command from the controller to the model to be parsed and executed
     */
    public void sendInstruction();

}