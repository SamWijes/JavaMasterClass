package dev.interf;

public class Jet implements FlightEnabled ,Trackable {
    private FlightStages currentStage=FlightStages.GROUNDED;
    public void printDistance(double distance){
        System.out.printf("%s has traveled a distance of %.2fKM or %.2fMil",getClass().getSimpleName(),distance,distance*MILES_TO_KM);
    }
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

    @Override
    public void track() {
        currentStage.track();
    }

    @Override
    public FlightStages transition(FlightStages stage){
        System.out.println(getClass().getSimpleName()+ " Is Transitioning");
        return FlightEnabled.super.transition(stage);
    }
}
