package slogo.Model;

import java.util.Collection;
import java.util.List;

public class TurtleData {

  private double xCoord;
  private double yCoord;
  private double headingDirection;
  private List<Collection> historyList;



  public TurtleData(double initX, double initY, double initHeading){
    xCoord = initX;
    yCoord = initY;
    headingDirection = initHeading;
  }

  public double getTurtleX(){
    return this.xCoord;
  }

  public double getTurtleY(){
    return this.yCoord;
  }

  public double getTurtleHeading(){
    return this.headingDirection;
  }

  public List<Collection> getHistory(){
    return this.historyList;
  }

  public void addHistory(Collection historyObject){
    historyList.add(historyObject);
    System.out.println(historyList);
  }

  public void moveXCoord(double distance){
    //System.out.println(this.xCoord);
    this.xCoord += distance;
  }

  public void moveYCoord(double distance){
//    System.out.println(this.yCoord);
    this.yCoord += distance;
  }

  public void rotateTurtleHeading(double angleAmount){
//    System.out.println(this.yCoord);
    this.headingDirection += angleAmount;
  }

}
