package slogo.Model;

import java.util.ArrayList;
import java.util.List;

public class ColorData {

    private List<List<Integer>> colorList;

    public ColorData(){
        colorList = new ArrayList<>();
    }

    public void setColor(int index, int r, int g, int b){
        ArrayList color = new ArrayList<Integer>();
        color.add(r);
        color.add(b);
        color.add(g);
        colorList.set(index, color);
    }

    public List<List<Integer>> getColorList(){
        return colorList;
    }

}
