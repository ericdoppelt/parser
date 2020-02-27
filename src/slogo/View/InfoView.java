package slogo.View;

import javafx.beans.property.ListProperty;
import javafx.beans.property.MapProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
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

    private MapProperty<String, Integer> myVariables;
    private ListProperty<String> myHistory;
    private MapProperty<String, String> myCommands;


    private VBox myInfoPanel;

    private ToggleGroup myToggleGroup = new ToggleGroup();
    private HBox myButtonPanel;
    private ToggleButton myHistoryToggle;
    private ToggleButton myCommandToggle;
    private ToggleButton myVariableToggle;

    private final String HELP_IMAGE_PATH = "infoGraphic.png";
    private Button myHelpButton;

    private ScrollPane myScrollPane;

    private final String RESOURCE_LANGUAGE = "English";
    private final ResourceBundle myBundle = ResourceBundle.getBundle(RESOURCE_LANGUAGE);


    public InfoView() {
        initInfoPanel();
        initProperties();
        initButtons();

        setToggledInfo();
        setButtonActions();
    }

    public MapProperty getVariableProperty() { return myVariables; }
    public ListProperty getHistoryProperty() { return myHistory; }
    public MapProperty getCommandProperty() { return myCommands; }

    private void initInfoPanel() {
        myButtonPanel = new HBox();
        myScrollPane = new ScrollPane();
        myScrollPane.setBackground(new Background(new BackgroundFill(Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));

        myInfoPanel = new VBox();
        myInfoPanel.getChildren().addAll(myButtonPanel, myScrollPane);
        myInfoPanel.setVgrow(myScrollPane, Priority.ALWAYS);
    }

    private void initProperties() {
        myVariables = new SimpleMapProperty<>();
        myVariables.addListener(((observable, oldValue, newValue) -> {
            ((VBox)myVariableToggle.getUserData()).getChildren().clear();
            for (String s : newValue.keySet()) {
                ((VBox)myVariableToggle.getUserData()).getChildren().add(new Label(s.substring(1) + ": " + newValue.get(s)));
            }
        }));

        myCommands = new SimpleMapProperty<>();
        myCommands.addListener(((observable, oldValue, newvalue) -> {

        }));

        myHistory = new SimpleListProperty<>();
        myHistory.addListener(((observable, oldValue, newValue) -> {
            if (newValue.size() > 0) {
                ((VBox) myHistoryToggle.getUserData()).getChildren().add(new Label(newValue.get(newValue.size() - 1)));
            }
        }));
    }

    private void initButtons() {
        myHistoryToggle = createToggleButton(myBundle.getString("historyHeader"));
        myCommandToggle = createToggleButton(myBundle.getString("commandHeader"));
        myVariableToggle = createToggleButton(myBundle.getString("variableHeader"));
        myHelpButton = createHelpButton();
    }

    private ToggleButton createToggleButton(String s) {
        ToggleButton tempToggle = new ToggleButton(s);
        tempToggle.setToggleGroup(myToggleGroup);
        myButtonPanel.getChildren().add(tempToggle);
        return tempToggle;
    }

    private Button createHelpButton() {
        Button tempButton = new Button();
        try {
            Image helpImage = new Image(this.getClass().getClassLoader().getResourceAsStream(HELP_IMAGE_PATH), 0, 25, true, false);
            tempButton.setGraphic(new ImageView(helpImage));
            //TODO: fill this in
        } catch (Exception e) {
        }
        tempButton.setPadding(new Insets(0));
        myButtonPanel.getChildren().add(tempButton);
        return tempButton;
    }

    private void setToggledInfo() {
        setHistoryInfo();
        setCommandInfo();
        setVariableInfo();
    }

    private void setHistoryInfo() {
        VBox historyToggleInfo = new VBox();
        myHistoryToggle.setUserData(historyToggleInfo);
    }

    private void setVariableInfo() {
        VBox variableToggleInfo = new VBox();
        myVariableToggle.setUserData(variableToggleInfo);
    }

    private void setCommandInfo() {
        VBox commandToggleInfo = new VBox();
        myCommandToggle.setUserData(commandToggleInfo);
    }

    private void setButtonActions() {
        for (Toggle tempToggle : myToggleGroup.getToggles()) {
            ToggleButton tempToggleButton = (ToggleButton) tempToggle;
            tempToggleButton.setOnAction(event -> myScrollPane.setContent((Node)tempToggleButton.getUserData()));
        }
        //TODO: helpButton exceptions
        myHelpButton.setOnAction(event -> {
            try {
                Desktop.getDesktop().browse(new URL("https://www2.cs.duke.edu/courses/compsci308/current/assign/03_parser/commands.php").toURI());
            } catch (IOException | URISyntaxException e) {
                System.out.println("Bad Error!");
            }
        });
    }

    public VBox getCompletePanel() {
        return myInfoPanel;
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
}
