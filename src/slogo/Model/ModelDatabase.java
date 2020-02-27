package slogo.Model;

import java.util.List;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import slogo.Model.CommandInfrastructure.CommandDatabase;

public class ModelDatabase {

  private SimpleObjectProperty<List<TurtleData>> turtleListProperty = new SimpleObjectProperty<>(
      FXCollections.observableArrayList());
  ;
  private SimpleStringProperty commandProperty;

  // regular expression representing any whitespace characters (space, tab, or newline)
  private String language;
  private TurtleData turtle = new TurtleData("Happy", 0, 0, 0);

  private ModelParser originParser;
  private CommandDatabase originCommandDatabase;

  /**
   * Main Database for instantiating turtles and parsing data
   *
   * @author Frank Tang
   */

  public ModelDatabase() {
    TurtleData originTurtle = new TurtleData("1", 0, 0, 0);
    turtleListProperty.getValue().add(originTurtle);

    language = "English";

    originCommandDatabase = new CommandDatabase(turtle);
    originParser = new ModelParser(language, originCommandDatabase);
  }

  public List<TurtleData> getTurtleListProperty() {
    return turtleListProperty.get();
  }

  ;

  public TurtleData getTurtle(String id) {
    for (TurtleData turtle : turtleListProperty.get()) {
      if (turtle.getTurtleID().equals(id)) {
        return turtle;
      }
    }
    //eventually add error for no turtle existing
    String errorMessage = "ERROR: Invalid Turtle ID";
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(errorMessage);
    Platform.runLater(alert::showAndWait);
    return null;
  }

  public TurtleData getMyTurtle() {
    return turtle;
  }

  public ModelParser getModelParser() {
    return originParser;
  }


  public String getCommand() {
    return commandProperty.get();
  }
  // try against different kinds of inputs
//      model.parseText(model, examples);
//      String fileInput = model.readFileToString(
//          Main.class.getClassLoader().getResource("\\resources\\languages\\square.logo").toExternalForm());
  // instead it will "comment out" the remainder of the program!
//      model.parseText(model, Arrays.asList(fileInput.split(WHITESPACE)));


  public void makeNewTurtle(String ID, double initX, double initY, double initHeading) {
    TurtleData newTurtle = new TurtleData(ID, initX, initY, initHeading);
    turtleListProperty.getValue().add(newTurtle);
  }

}
