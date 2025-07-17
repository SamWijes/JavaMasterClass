package dev.enu;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        DayOfWeek weekDay=DayOfWeek.FRI;
        System.out.println(weekDay);
        System.out.printf("%s  %d%n",weekDay.name(),weekDay.ordinal());

        for (int i = 0; i < 7; i++) {

            randomDate(weekDay).getFullDate();
        }





    }

    public static DayOfWeek randomDate(DayOfWeek weekDay){
        int randomNum=new Random().nextInt(7);
        var date=DayOfWeek.values();
        return date[randomNum];
    }
}
