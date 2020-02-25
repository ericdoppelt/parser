package slogo.Model;

import javafx.application.Platform;
import javafx.beans.binding.ListBinding;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelDatabase {

  private SimpleObjectProperty<List<TurtleData>> turtleListProperty = new SimpleObjectProperty<>(FXCollections.observableArrayList());;
  private SimpleStringProperty commandProperty;

  // regular expression representing any whitespace characters (space, tab, or newline)
  private String language;

  // some examples to test for matching
//  private List<String> examples = List.of(
//      "",
//      "# foo",
//      "foo #",
//      "#",
//      "fd",
//      "FD",
//      "forwardd",
//      "equalp",
//      "equal?",
//      "equal??",
//      "+",
//      "SuM",
//      "-",
//      "*",
//      "/",
//      "%",
//      "~",
//      "+not",
//      "not+",
//      "++",
//      "+*+",
//      "or",
//      "FOR",
//      "allOrNothing",
//      "all_or_nothing",
//      "allOr_nothing?",
//      "allOr?nothing_",
//      ":allornothing",
//      "PI",
//      "90",
//      "9.09",
//      "9.0.0",
//      "[",
//      "]",
//      "(",
//      ")"
//  );

  /**
   * Main Database for instantiating turtles and parsing data
   *
   * @author Frank Tang
   */

    public ModelDatabase(){
      TurtleData originTurtle = new TurtleData("1",0,0,0);
      turtleListProperty.getValue().add(originTurtle);
    }

    public List<TurtleData> getTurtleListProperty(){return turtleListProperty.get();};

    public TurtleData getTurtle(String id){
      for(TurtleData turtle: turtleListProperty.get()){
        if (turtle.getTurtleID().equals(id)){
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


    public String getCommand(){return commandProperty.get();}
  // try against different kinds of inputs
//      model.parseText(model, examples);
//      String fileInput = model.readFileToString(
//          Main.class.getClassLoader().getResource("\\resources\\languages\\square.logo").toExternalForm());
  // instead it will "comment out" the remainder of the program!
//      model.parseText(model, Arrays.asList(fileInput.split(WHITESPACE)));



    public void makeNewTurtle(String ID, double initX, double initY, double initHeading){
      TurtleData newTurtle = new TurtleData(ID, initX, initY, initHeading);
      turtleListProperty.getValue().add(newTurtle);
    }

}
