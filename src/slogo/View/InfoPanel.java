package slogo.View;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoPanel {

    private VBox infoPanel;
    private HBox buttons;
    private Pane information;

    private final ToggleGroup myGroup = new ToggleGroup();

    private final String RESOURCE_LANGUAGE = "English";
    private final ResourceBundle myBundle = ResourceBundle.getBundle(RESOURCE_LANGUAGE);

    private ToggleButton historyToggle;
    private ToggleButton commandToggle;
    private ToggleButton variableToggle;

    private Button myHelpButton;
    private final String HELP_IMAGE_PATH = "helpImages/infoGraphic.png";

    public InfoPanel() {
        infoPanel = new VBox();
        buttons = new HBox();
        information = new Pane();
        information.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        infoPanel.setVgrow(information, Priority.ALWAYS);

        initToggleButton();
        setHistoryInfo();
        setCommandInfo();
        setVariableInfo();
        initHelpButton();
        setActions();
        buttons.getChildren().addAll(historyToggle, commandToggle, variableToggle, myHelpButton);
        infoPanel.getChildren().add(buttons);
        infoPanel.getChildren().add(information);
    }

    private void setActions() {
        historyToggle.setOnAction(event -> {
            information.getChildren().clear();
            information.getChildren().add(new Label("History"));
        });
        commandToggle.setOnAction(event -> {
            information.getChildren().clear();
            information.getChildren().add(new Label("Command"));
        });
        variableToggle.setOnAction(event -> {
            information.getChildren().clear();
            information.getChildren().add(new Label("Variable"));
        });
    }

    private void initHelpButton() {
        myHelpButton = new Button();
        Image helpImage = new Image(this.getClass().getClassLoader().getResourceAsStream(HELP_IMAGE_PATH), 0, 25, true, false);
        myHelpButton.setGraphic(new ImageView(helpImage));

        myHelpButton.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URL("https://www2.cs.duke.edu/courses/compsci308/current/assign/03_parser/commands.php").toURI());
            //TODO: Handle error
            } catch (IOException | URISyntaxException e) {
                System.out.println("Bad Error!");
            }
        });

        myHelpButton.setPadding(new Insets(0));
    }

    private void initToggleButton() {
        historyToggle = createToggleButton(myBundle.getString("historyHeader"), false);
        commandToggle = createToggleButton(myBundle.getString("commandHeader"), true);
        variableToggle = createToggleButton(myBundle.getString("variableHeader"), false);
    }

    private ToggleButton createToggleButton(String s, boolean selected) {
        ToggleButton tempToggle = new ToggleButton(s);
        tempToggle.setToggleGroup(myGroup);
        tempToggle.setSelected(selected);
        return tempToggle;
    }

    private void setHistoryInfo() {
        Label history = new Label("History");
        historyToggle.setUserData(history);
    }

    private void setCommandInfo() {
        Label command = new Label("Commands");
        commandToggle.setUserData(command);
    }

    private void setVariableInfo() {
        Label variable = new Label("Variables");
        variableToggle.setUserData(variable);
    }

    public VBox getInfoPanel() {
        return infoPanel;
    }
}
