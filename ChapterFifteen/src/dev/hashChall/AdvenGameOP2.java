package dev.hashChall;

import java.util.*;

public class AdvenGameOP2 {
    public static final String GAME_LOCATIONS= """
            road,at the end of the road, W: hill, E:well house,S:valley,N:forest
            hill,on top of hill with a view in all directions,N:forest, E:road
            well house,inside a well house for a small spring,W:road,N:lake,S:stream
            valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:stream
            forest,at the edge of a thick dark forest,S:road,E:lake
            lake,by an alpine lake surrounded by wildflowers,W:forest,S:well house
            stream,near a stream with a rocky bed,W:valley, N:well house
            """;



    public enum dirCompass{
        N,S,E,W;

        private static final String[] dirText= {"North","South","East","West"};


        @Override
        public String toString() {
            return dirText[this.ordinal()];
        }
    }

    //private Location StartingLocation;

    public record Location(String curLoc,String description, Map<dirCompass,String> nextLoc){ }

    Map<String,Location> locMap=new HashMap<>();

    public void getLocations(String GAME_LOCATIONS){
        for(String loc:GAME_LOCATIONS.split("\\R")){
            String [] locSplit=loc.split(",");
            Arrays.asList(locSplit).replaceAll(String::trim);
            String curLon=locSplit[0];
            String description=locSplit[1];
            Map<dirCompass,String> nextLoc=new HashMap<>();
            for (int i = 2; i < locSplit.length; i++) {
                nextLoc.put(dirCompass.valueOf(String.valueOf(locSplit[i].charAt(0))),
                        locSplit[i].substring(locSplit[i].indexOf(":")+1).trim());
            }
            locMap.put(curLon,new Location(curLon,description,nextLoc));
        }
    }


    public void play(){
        //loadMap
        getLocations(GAME_LOCATIONS);
        //set Start Location
        String curLoc="road";
        Scanner scanner=new Scanner(System.in);
        while (true){
            String HomeMsg= """
                ***You are standing %s ***
                From Here You can see""".formatted(locMap.get(curLoc).description);
            System.out.println(HomeMsg);
            printSeenLoc(locMap.get(curLoc));

            String nextDir=scanner.nextLine().substring(0,1).toUpperCase();
            while (!"NSEWQ".contains(nextDir)) {
                System.out.println("Enter a Valid Direction");
                nextDir=scanner.nextLine().substring(0,1).toUpperCase();
            }
            if (nextDir.equalsIgnoreCase("q")) break;
            while (!locMap.get(curLoc).nextLoc.containsKey(dirCompass.valueOf(nextDir))){
                System.out.println("Enter a Valid Direction"+locMap.get(curLoc).nextLoc.keySet());
                nextDir=scanner.nextLine().substring(0,1).toUpperCase();
                while (!"NSEWQ".contains(nextDir)) {
                    System.out.println("Enter a Valid Direction");
                    nextDir=scanner.nextLine().substring(0,1).toUpperCase();
                }
                if (nextDir.equals("Q")) return;

            }

            curLoc=locMap.get(curLoc).nextLoc.get(dirCompass.valueOf(nextDir));


        }
    }

    public void printSeenLoc(Location location){
        location.nextLoc.forEach((k,v)-> System.out.printf("\u2022 A %s to the %s (%s)%n",
               v,k,k.name()));

        System.out.println("Select Your Compass Direction (Q to Quit)");
    }




}
