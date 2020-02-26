package slogo.View;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import slogo.Model.ModelParser;


public class SlogoView extends Application {

    private BorderPane myBorderPane;
    private ModelParser myModelParser;
    private InputView myInputView;
    private Pane myBackgroundPane;
    private TurtleView myTurtleView;

    public SlogoView() {}

    public SlogoView(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initModel();
        initView();
        bindProperties();
        initStage(primaryStage);
    }

    private void initModel() {
        myModelParser = new ModelParser("English");
    }

    private void initView() {
        myBackgroundPane = new Pane();
        myTurtleView = new TurtleView(myModelParser.getMyTurtle(), myBackgroundPane);
        CommandBox myCommandLine = new CommandBox(myModelParser, myTurtleView);
        myInputView = new InputView();
        InfoView myInfo = new InfoView();

        VBox commandAndInput = new VBox();
        commandAndInput.getChildren().addAll(myInputView.getInputPanel(), myCommandLine.getCommandLine());

        myBorderPane = new BorderPane();
        myBorderPane.setBottom(commandAndInput);
        myBorderPane.setCenter(myBackgroundPane);
        myBorderPane.setRight(myInfo.getInfoPanel());
    }

    private void initStage(Stage primaryStage) {
        Scene myScene = new Scene(myBorderPane, 600,600);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

    private void bindProperties() {
        createBindableBackground();
        createBindablePen();
    }

    // Inspiration from https://stackoverflow.com/questions/33999728/binding-colorpicker-in-javafx-to-label-background-property
    // TODO: once we understand bindings better, refactor
    private void createBindableBackground() {
        ObjectProperty<Background> backgroundProperty = myBackgroundPane.backgroundProperty();
        backgroundProperty.bind(Bindings.createObjectBinding(() -> {
            BackgroundFill fill = new BackgroundFill(myInputView.getBackgroundColor().getValue(), CornerRadii.EMPTY, Insets.EMPTY);
            return new Background(fill);
        }, myInputView.getBackgroundColor().valueProperty()));
    }


    private void createBindablePen() {

        Bindings.bindBidirectional(myTurtleView.getColorProperty(), myInputView.getPenColor().valueProperty());
    }
}


