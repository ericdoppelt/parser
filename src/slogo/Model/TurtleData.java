package slogo.Model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TurtleData {

  private SimpleDoubleProperty xCoord = new SimpleDoubleProperty();
  private SimpleDoubleProperty yCoord = new SimpleDoubleProperty();
  private SimpleDoubleProperty headingDirection = new SimpleDoubleProperty();
  private SimpleBooleanProperty penDown = new SimpleBooleanProperty();
  private SimpleBooleanProperty turtleVisibility = new SimpleBooleanProperty(true);
  private SimpleBooleanProperty turtleShowTrails = new SimpleBooleanProperty(true);
  private String turtleID;

  private static final int fullRevolution = 360;
  private static final int zeroAngle = 0;
  private List<Collection> historyList;
  private ObservableList<List<Double>> coordList = FXCollections.observableArrayList();
  private List<Double> coord = new ArrayList<>();
  private SimpleObjectProperty<ObservableList<List<Double>>> coordHistory;

  public TurtleData(String ID, double initX, double initY, double initHeading){
    xCoord.set(initX);
    yCoord.set(initY);
    headingDirection.set(initHeading);
    turtleID = ID;

    coord.add(initX);
    coord.add(initY);
    coordList.add(coord);
  }

  public double getTurtleX(){
    return this.xCoord.get();
  }

  public double getTurtleY(){
    return this.yCoord.get();
  }

  public SimpleDoubleProperty getTurtleXProperty(){
    return this.xCoord;
  }

  public SimpleDoubleProperty getTurtleYProperty(){
    return this.yCoord;
  }

  public SimpleBooleanProperty getPenDownProperty(){
    return this.penDown;
  }

  public SimpleDoubleProperty directionProperty() {
    return headingDirection;
  }

  public SimpleBooleanProperty turtleVisibility() {
    return turtleVisibility;
  }

  public double getTurtleHeading(){
    return this.headingDirection.get();
  }

  public String getTurtleID(){
    return this.turtleID;
  }

  public List<Collection> getHistory(){
    return this.historyList;
  }

  public void addHistory(Collection historyObject){
    historyList.add(historyObject);
    System.out.println(historyList);
  }

  public void addCoord(double x, double y){
    coord = new ArrayList<>();
    coord.add(x);
    coord.add(y);
    coordList.add(coord);
  }

  public ObservableList<List<Double>> getCoordHistory(){
    return coordList;
  }

  public void moveXCoord(double distance){
    //System.out.println(this.xCoord);
    this.xCoord.set(xCoord.get() + distance);
  }

  public void moveYCoord(double distance){
//    System.out.println(this.yCoord);
    this.yCoord.set(yCoord.get() + distance);
  }

  public void setXCoord(double newX){
    //System.out.println(this.xCoord);
    this.xCoord.set(newX);
  }

  public void setYCoord(double newY){
//    System.out.println(this.yCoord);
    this.yCoord.set(newY);
  }

  public void setPenStatus(boolean isPenDown){
//    System.out.println(this.yCoord);
    this.penDown.set(isPenDown);
  }

  public void setTurtleVisibility(boolean isTurtleVisible){
//    System.out.println(this.yCoord);
    this.turtleVisibility.set(isTurtleVisible);
  }

  public void setTurtleTrails(boolean areTrailsVisible){
//    System.out.println(this.yCoord);
    this.turtleVisibility.set(areTrailsVisible);
  }

  public void rotateTurtleHeading(double angleAmount){
//    System.out.println(this.yCoord);
    double test = this.headingDirection.get() + angleAmount;
    if(test >= fullRevolution){
      test -= fullRevolution;
    }
    else if (test < zeroAngle){
      test += fullRevolution;
    }
    this.setTurtleDirection(test);
  }

  public void setTurtleDirection(double angle){
//    System.out.println(this.yCoord);
    this.headingDirection.set(angle);
  }

  public int getTurtleVisibility(){
//    System.out.println(this.yCoord);
    if(this.turtleVisibility.get()){
      int turtleIsVisible = 1;
      return turtleIsVisible;
    }
    else{
      int turtleIsNotVisible = 0;
      return turtleIsNotVisible;
    }
  }

  public int getPenStatus(){
//    System.out.println(this.yCoord);
    if(this.penDown.get()){
      int penDown = 1;
      return penDown;
    }
    else{
      int penUp = 0;
      return penUp;
    }
  }

}
