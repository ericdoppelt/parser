package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.View.SlogoView;

public class Main extends Application {
  /**
     * Start of the program.
     */
  public static void main (String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    SlogoView initialSlogo = new SlogoView(primaryStage);
  }
}

