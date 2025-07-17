package dev.interf;

public class Test {

    public static void main(String[] args) {
        Jet jet=new Jet();
        //inFlight(jet);


        //OrbitEarth.log("Engines Normal"+new Satellite());
        orbit(new Satellite());
        //jet.track();
    }


    public static void inFlight(FlightEnabled flier){
        flier.takeOff();
        flier.land();
        flier.transition(FlightStages.LAUNCH);
        if (flier instanceof  Trackable tracked){
            tracked.track();
        }
        else System.out.println("Cannot Track");
    }

    public static void orbit(OrbitEarth flier){
        flier.takeOff();
        flier.fly();
        flier.land();

    }
}
