package dev.hashChall;

import java.util.*;

public class AdventureGame {

    public static void main(String[] args) {
        Map<String,String> currentLocation=new HashMap<>();
        Map<String,Map<String,String>> nextLocation=new HashMap<>();

        currentLocation.put("road","at the end of the road");
        currentLocation.put("hill","on top of hill with a view in all directions");
        currentLocation.put("well house","inside a well house for a small spring");
        currentLocation.put("valley","in a forest valley beside a tumbling stream");
        currentLocation.put("forest","at the edge of a thick dark forest");
        currentLocation.put("lake","by an alpine lake surrounded by wildflowers");
        currentLocation.put("stream","near a stream with a rocky bed");
        Map<String,String> roadDir=new HashMap<>();
        roadDir.put("W", "hill");
        roadDir.put("E", "well house");
        roadDir.put("S", "valley");
        roadDir.put("N", "forest");

        Map<String, String> hillDir = new HashMap<>();
        hillDir.put("N", "forest");
        hillDir.put("E", "road");

        Map<String, String> wellHouseDir = new HashMap<>();
        wellHouseDir.put("W", "road");
        wellHouseDir.put("N", "lake");
        wellHouseDir.put("S", "stream");

        Map<String, String> valleyDir = new HashMap<>();
        valleyDir.put("N", "road");
        valleyDir.put("W", "hill");
        valleyDir.put("E", "stream");

        Map<String, String> forestDir = new HashMap<>();
        forestDir.put("S", "road");
        forestDir.put("E", "lake");

        Map<String, String> lakeDir = new HashMap<>();
        lakeDir.put("W", "forest");
        lakeDir.put("S", "well house");

        Map<String, String> streamDir = new HashMap<>();
        streamDir.put("W", "valley");
        streamDir.put("N", "well house");


        nextLocation.put("road", roadDir);
        nextLocation.put("hill", hillDir);
        nextLocation.put("well house", wellHouseDir);
        nextLocation.put("valley", valleyDir);
        nextLocation.put("forest",forestDir);
        nextLocation.put("lake", lakeDir);
        nextLocation.put("stream", streamDir);

        String curLoc="road";
        Scanner scanner=new Scanner(System.in);
        while (!curLoc.equalsIgnoreCase("q")) {
            printStatus(curLoc, currentLocation, nextLocation);
            String nextDir = scanner.nextLine();
            if (nextDir.equalsIgnoreCase("q")) break; ;
           // System.out.println(nextDir);
            while (!nextLocation.get(curLoc).containsKey(nextDir.trim().
                    toUpperCase())&& !nextDir.equalsIgnoreCase("q")) {
                System.out.println("Invalid Direction,Enter a Valid Direction");
                nextDir = scanner.nextLine();

            }
           // System.out.println(nextDir);
            if (nextDir.equalsIgnoreCase("q")) curLoc=nextDir;
            else curLoc = nextLocation.get(curLoc).get(nextDir.toUpperCase());

        }



        

//        while(!curLoc.equalsIgnoreCase("q")) {
//
//            printStatus(curLoc, currentLocation, nextLocation);
//           // String nextDir = scanner.nextLine();
//            //System.out.println(Arrays.asList(nextLocation.get(curLoc)).contains(nextDir+":"));
//
//        }

    }


    public static void printStatus(String currentLoc,Map<String,String> currentLocation,
                                   Map<String,Map<String,String>> nextLocation){
        String HomeMsg= """
                
                ***You are standing %s ***
                From Here You can see""".formatted(currentLocation.get(currentLoc));
        System.out.println(HomeMsg);

        for (var loc:nextLocation.get(currentLoc).entrySet()) {

            System.out.printf("A %s to the %s (%s)%n",loc.getValue(),
                 switch (loc.getKey().toLowerCase()){
                        case "n"->"North";
                        case "s"->"South";
                        case "e"->"East";
                        case "w"->"West";
                        default -> throw new IllegalStateException("Unexpected value: ");
                    }, loc.getKey());
        }
        System.out.println("Select Your Compass Direction (Q to Quit)");

    }
}
