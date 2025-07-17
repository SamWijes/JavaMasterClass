package dev.LinkLstChallen;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        String [] towns="Adelaide,Alice Springs,Brisbane,Darwin,Melbourne,Perth".split(",");
        int[] distance={1374,2771,917,3972,877,3923};

        LinkedList<TownInfo> itinerary=new LinkedList<>();
        for (int i = 0; i < towns.length; i++) {
            TownInfo town=new TownInfo(towns[i],distance[i] );
            itinerary.add(town);
        }

        var iter=itinerary.listIterator();

        itinerary.sort(Comparator.comparing(TownInfo::getDistance));


        Scanner s=new Scanner(System.in);
        while(true){
            String textBox= """
                    Available action
                    (F)orward
                    (B)ackward
                    (L)ist Places
                    (M)enu
                    (Q)uit
                    """;
            System.out.println(textBox);
            String input=s.nextLine().toUpperCase();
            switch (input){
                case "F"->iterTraverse(itinerary,"F");
                case "B"->iterTraverse(itinerary,"B");
                case "L" -> {
                    for (TownInfo town: itinerary){
                        System.out.println(town.name+ "<---> " +town.distance);
                    }
                }
                case "M" -> {
                }
                case "Q" -> {
                    return;
                }
                default -> System.out.println("enter valid input");
            }

        }



    }
    public static void iterTraverse(LinkedList<TownInfo> list,String direction){
        int index=0;
        if (direction=="F") {
            index=0;
        }else{
            index=list.size();
        }
        var iter = list.listIterator(index);
        while (iter.hasNext() && direction=="F"){
            System.out.println(iter.next().name);
        }
        while (iter.hasPrevious() && direction.equals("B")){
            System.out.println(iter.previous().name);
        }
    }

}

class TownInfo {
    protected String name;
    protected int distance;

    public TownInfo(String name,int distance){
        this.name=name;
        this.distance=distance;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "TownInfo{" +
                "name='" + name + '\'' +
                ", distance=" + distance +
                '}';
    }
}