package dev.interf;

public class Bird extends Animal implements FlightEnabled ,Trackable{


    @Override
    public void takeOff() {
        System.out.println(getClass().getSimpleName()+ "is taking Off");
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName()+ "is Flying");
    }

    @Override
    public void land() {
        System.out.println(getClass().getSimpleName()+ "is landing");

    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName()+ "'s is GPS tracking");
    }

    @Override
    public void move(){
        System.out.println("Wings Flapping");
    }
}
