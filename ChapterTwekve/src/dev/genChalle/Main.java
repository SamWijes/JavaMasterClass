package dev.genChalle;

public class Main {

    public static void main(String[] args) {
        Park park  =new Park("Grand Canyon" ,"National Park", new double[]{40.1021, -70.4231});
        River river =new River("Mississippi River","River",new double[][]{{47.2160, -95.2348},
                                                                                   {35.1556, -90.0659},
                                                                                   {29.1566, -89.2495}});
//        park.render();

//        river.render();

        Layer<Mappable> layer=new Layer();
        layer.addElement(park);
        layer.addElement(river);

        layer.renderLayer();
    }
}
