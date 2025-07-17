package dev.interf;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Satellite satellite=new Satellite();

        Bird bird=new Bird();
        Animal animal=bird ;
        Trackable tracked=bird;
        FlightEnabled flier=bird;

        Jet mig=new Jet();

        inFlight(mig);
        inFlight(satellite);
        double distanceTraveled=100;
        System.out.println();
        mig.printDistance(distanceTraveled);

        ArrayList<FlightEnabled> fliers=new ArrayList<>();
        fliers.add(bird);

        List<FlightEnabled> betterFliers=new ArrayList<>();
        betterFliers.add(bird);

        mig.transition(FlightStages.LAUNCH);
        triggerFlier(fliers);

        triggerFlier(betterFliers);
    }

    public static void inFlight(FlightEnabled flier){
        flier.takeOff();
        flier.land();
        if (flier instanceof  Trackable tracked){
            tracked.track();
        }
        else System.out.println("Cannot Track");
    }

    public static void triggerFlier(List<FlightEnabled> fliers){
        for(var flier:fliers){
            flier.takeOff();
        }
    }
}
