package dev.enumCollect;

import java.util.*;

public class Main {

    enum Weekday{MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY;}

    public static void main(String[] args) {

        List<Weekday> annsWorkDays=List.of(Weekday.MONDAY,Weekday.TUESDAY,Weekday.THURSDAY,Weekday.FRIDAY);


        var annsdDaysSet= EnumSet.copyOf(annsWorkDays);
        System.out.println(annsdDaysSet.getClass().getSimpleName());
        annsdDaysSet.forEach(System.out::println);

        var alldayset=EnumSet.allOf(Weekday.class);
        System.out.println("_".repeat(25));
        alldayset.forEach(System.out::println);


        Set<Weekday> newPersonDays=EnumSet.complementOf(annsdDaysSet);
        System.out.println("_".repeat(25));
        newPersonDays.forEach(System.out::println);

        Set<Weekday> businessDays=EnumSet.range(Weekday.MONDAY,Weekday.FRIDAY);
        System.out.println("_".repeat(25));
        businessDays.forEach(System.out::println);

        Map<Weekday,String[]> roster=new EnumMap<>(Weekday.class);

        roster.put(Weekday.MONDAY,new String[]{"Sam","Jack","Gavez","Mike"});
        roster.put(Weekday.TUESDAY ,new String[]{"Ronalsd","Jhon","Williams"});
        roster.forEach((k,v)-> System.out.println(k+":"+Arrays.toString(v)));
    }

}
