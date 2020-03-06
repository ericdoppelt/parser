package slogo.Model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import slogo.Model.CommandInfrastructure.CommandDatabase;
import slogo.Model.CommandInfrastructure.CommandProducer;
import slogo.Model.Commands.Command;

public class ModelDatabase {

  private SimpleObjectProperty<List<TurtleData>> turtleListProperty = new SimpleObjectProperty<>(
      FXCollections.observableArrayList());
  ;
  private SimpleStringProperty commandProperty;

  // regular expression representing any whitespace characters (space, tab, or newline)
  private String language;
  private ListProperty<String> HISTORY_LIST = new SimpleListProperty(FXCollections.observableList(new ArrayList<>()));
  private ListProperty<Command> COMMAND_LIST = new SimpleListProperty<>();

  private TurtleData turtle = new TurtleData("Happy", 0, 0, 0);

  private CommandDatabase originCommandDatabase;
  private ModelParser originParser;
  private CommandProducer originProducer;


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
    originProducer = new CommandProducer(originCommandDatabase, HISTORY_LIST, COMMAND_LIST);
    originParser = new ModelParser(language, originCommandDatabase, originProducer);
  }

  public TurtleData getMyTurtle() {
    return turtle;
  }

  public ListProperty<String> getHISTORY_LIST(){
    return HISTORY_LIST;
  }

  public ListProperty<Command> getCOMMAND_LIST(){
    return COMMAND_LIST;
  }


  public String getCommand() {
    return commandProperty.get();
  }

//  public void addToHistory(String command) {
//    HISTORY_LIST.getValue().add(command);
//  }
  public void bindHistory(ListProperty displayedHistory) {
    displayedHistory.bind(HISTORY_LIST);
  }

  public void bindCommands(MapProperty displayedCommands) {
    displayedCommands.bind(COMMAND_LIST);
  }

}
