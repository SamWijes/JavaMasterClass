package dev.genChalle;

import java.util.ArrayList;
import java.util.Arrays;

interface Mappable{
//define a method to convert string of coordinates intodouble array of length 2
    void render();
}
public class Point implements Mappable {
    private String name;
    private String type;
    private  double[] coordinates;
    @Override
    public void render() {
        System.out.printf("Render %s %s as POINT (%s)%n", name,type,Arrays.toString(coordinates));
    }

    public Point(String name,String type,double[] coordinates){
        this.name=name;
        this.type=type;
        this.coordinates=coordinates;
    }
}
//make line and point abstract  , make constructor input var args for locations, get name parameter from implementing class
class Line implements Mappable{
    private String name;
    private String type;
    protected double[][] coordinates;
    @Override
    public void render() {
        System.out.printf("Render %s %s as LINE %s%n", name,type, Arrays.deepToString(coordinates));
    }

    public Line(String name,String type, double[][] coordinates) {
        this.name = name;
        this.type=type;
        this.coordinates = coordinates;
    }
}

class Layer<T extends Mappable >{
    private ArrayList<T> elements;

    Layer() {
        this.elements = new ArrayList<>();
    }

    public void renderLayer(){
        for(T el:elements){
            el.render();
        }
    }

    public void addElement(T element){
        elements.add(element);
    }
}