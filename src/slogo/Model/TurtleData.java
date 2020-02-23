package slogo.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;

import java.util.Collection;
import java.util.List;

public class TurtleData {

  private SimpleDoubleProperty xCoord = new SimpleDoubleProperty();
  private SimpleDoubleProperty yCoord = new SimpleDoubleProperty();
  private SimpleDoubleProperty headingDirection;
  private String turtleID;

  private static final int fullRevolution = 360;
  private static final int zeroAngle = 0;
  private List<Collection> historyList;
  private ObservableList<List<Double>> coordList;
  private List<Double> coord;
  private SimpleObjectProperty<ObservableList<List<Double>>> coordHistory;

  public TurtleData(String ID, double initX, double initY, double initHeading){
    xCoord.set(initX);
    yCoord.set(initY);
    headingDirection.set(initHeading);
    turtleID = ID;

    coord.add(initX);
    coord.add(initY);
    coordList.add(coord);
    coordHistory.set(coordList);
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
    coord.add(x);
    coord.add(y);
    coordList.add(coord);
    coordHistory.set(coordList);
  }

  public SimpleObjectProperty<ObservableList<List<Double>>> getCoordHistory(){
    return coordHistory;
  }

  public void moveXCoord(double distance){
    //System.out.println(this.xCoord);
    this.xCoord.set(xCoord.get() + distance);
  }

  public void moveYCoord(double distance){
//    System.out.println(this.yCoord);
    this.yCoord.set(yCoord.get() + distance);
  }

  public void rotateTurtleHeading(double angleAmount){
//    System.out.println(this.yCoord);
    this.headingDirection += angleAmount;
    if(this.headingDirection >= fullRevolution){
      this.headingDirection -= fullRevolution;
    }
    else if (this.headingDirection < zeroAngle){
      this.headingDirection += fullRevolution;
    }
  }

  public void setTurtleDirection(double angle){
//    System.out.println(this.yCoord);
    this.headingDirection.set(angle);
  }

}
