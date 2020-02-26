package slogo.View;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class InfoView {

    private Map<String, Integer> myVariables = getTempVars();
    private VBox myVariableLabels;

    private List<String> myHistory = getTempHistory();
    private VBox myHistoryLabels;

    private Map<String, String> myCommands;
    private VBox myCommandsLabels;

    private VBox infoPanel;
    private HBox buttons;
    private ScrollPane information;

    private final ToggleGroup myGroup = new ToggleGroup();

    private final String RESOURCE_LANGUAGE = "English";
    private final ResourceBundle myBundle = ResourceBundle.getBundle(RESOURCE_LANGUAGE);

    private ToggleButton historyToggle;
    private ToggleButton commandToggle;
    private ToggleButton variableToggle;

    private Button myHelpButton;
    private final String HELP_IMAGE_PATH = "infoGraphic.png";

    public InfoView() {
        infoPanel = new VBox();
        buttons = new HBox();
        information = new ScrollPane();
        information.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        infoPanel.getChildren().addAll(buttons, information);
        infoPanel.setVgrow(information, Priority.ALWAYS);

        initToggleButton();
        setHistoryInfo();
        setCommandInfo();
        setVariableInfo();
        initHelpButton();
        setActions();
        buttons.getChildren().addAll(historyToggle, commandToggle, variableToggle, myHelpButton);
    }

    private void setActions() {
        historyToggle.setOnAction(event -> {
            information.setContent(myHistoryLabels);
        });
        commandToggle.setOnAction(event -> {
        });
        variableToggle.setOnAction(event -> {
            information.setContent(myVariableLabels);
        });
    }

    private void initHelpButton() {
        myHelpButton = new Button();
        //TODO: exception for is the image path is wrong (similar to the turtle image in inputview)
        try {
            Image helpImage = new Image(this.getClass().getClassLoader().getResourceAsStream(HELP_IMAGE_PATH), 0, 25, true, false);
            myHelpButton.setGraphic(new ImageView(helpImage));
        } catch (Exception e) {
        }
        myHelpButton.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URL("https://www2.cs.duke.edu/courses/compsci308/current/assign/03_parser/commands.php").toURI());
            //TODO: Not sure if this needs to be a custom error TBH since Java can handle it, but basically its just if the help link is bad
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


    public VBox getInfoPanel() {
        return infoPanel;
    }

    private Map getTempVars() {
        Map temp = new HashMap();
        for (int i = 0; i < 100; i++) {
            temp.put("same" + i, i);
        }
        return temp;
    }

    private List getTempHistory() {
        List tempHist = new ArrayList();
        for (int i = 0; i < 100; i++) {
            tempHist.add("fd 50");
        }
        return tempHist;
    }

    private void setHistoryInfo() {
        myHistoryLabels = new VBox();
        for (String s : myHistory) {
            HBox labelWrapper = new HBox();
            Label tempInfo = new Label();
            tempInfo.setText(s);
            labelWrapper.getChildren().add(tempInfo);
            labelWrapper.setHgrow(tempInfo, Priority.ALWAYS);
            myHistoryLabels.getChildren().add(labelWrapper);
        }
        historyToggle.setUserData(myHistoryLabels);
    }

    private void setVariableInfo() {
        myVariableLabels = new VBox();
        for (String s : myVariables.keySet()) {
            HBox labelWrapper = new HBox();
            Label tempInfo = new Label();
            tempInfo.setText(s + ": " + myVariables.get(s));
            labelWrapper.getChildren().add(tempInfo);
            labelWrapper.setHgrow(tempInfo, Priority.ALWAYS);
            myVariableLabels.getChildren().add(labelWrapper);
        }
    }

    private void setCommandInfo() {

    }
}
