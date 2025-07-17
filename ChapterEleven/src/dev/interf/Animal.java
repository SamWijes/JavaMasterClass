package dev.interf;

enum FlightStages implements Trackable{
    GROUNDED,LAUNCH,CRUISE,DATA_COLLECTION;

    @Override
    public void track() {
        if (this!=GROUNDED){
            System.out.println("Monitoring "+this);
        }
    }

    public FlightStages getNextStage(){
        FlightStages[] stages=values();
        return stages[(ordinal()+1) % stages.length];
    }
}

record DragonFly(String name,String type) implements FlightEnabled{

    @Override
    public void takeOff() {
        System.out.println(getClass().getSimpleName()+ " is taking Off");
    }

    @Override
    public void fly() {
        System.out.println(getClass().getSimpleName()+ "is Flying");
    }

    @Override
    public void land() {
        System.out.println(getClass().getSimpleName()+ " is landing");

    }

}

interface  OrbitEarth extends FlightEnabled{

    void achieveOrbit();

    static void log(String description){
        var date=new java.util.Date();
        System.out.println(date +":"+description);
    }

    private void logState(FlightStages stage ,String description){
        description=stage+":"+description;
        log(description);
    }

    @Override
    default FlightStages transition(FlightStages stage) {
        FlightStages nextStage=FlightEnabled.super.transition(stage);
        logState(stage,"Begining Transition to "+nextStage);
        return nextStage;
    }
}
interface FlightEnabled{

    double MILES_TO_KM=1.603;
    void takeOff();

    void fly();
    void land();


    default FlightStages transition(FlightStages stage){
//        System.out.println("transition Not Implemented for "+getClass().getName());
//        return stage.getNextStage();

        FlightStages nextStage=stage.getNextStage();
        System.out.printf("Transitioning From %s to %s%n",stage,nextStage);
        return nextStage;
    }
}

interface Trackable{
    void track();


}

public abstract class Animal {

    public abstract void move();

}

class Satellite implements OrbitEarth,Trackable{
    FlightStages stage=FlightStages.GROUNDED;

    public void satLog(String description){
        OrbitEarth.log(description);
    }

    @Override
    public void achieveOrbit() {
        transition("orbit achived ");
    }
    @Override
    public void takeOff() {
        transition("taking off");
    }

    @Override
    public void fly() {
        achieveOrbit();
        transition("flyinh");
    }

    @Override
    public void land() {
        transition("landing");

    }
    public void transition(String description){
        System.out.println(description);
        stage=transition(stage);
        stage.track();
    }

    @Override
    public void track() {
        System.out.println(getClass().getSimpleName()+ "'s is GPS tracking");
    }
}