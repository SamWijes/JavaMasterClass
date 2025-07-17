package dev.employMeeting;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.*;

record Employee(String name, ZoneId zone){}
public class Main {
    public static void main(String[] args) {
        Employee Sam=new Employee("Sam",ZoneId.of("America/New_York"));
        Employee Joe=new Employee("Joe",ZoneId.of("Australia/Sydney"));

        //set sydney to 8 am
        ZonedDateTime samTime= ZonedDateTime.of(LocalDate.now(),LocalTime.of(8,00),Sam.zone());
        ZonedDateTime joeTime= samTime.withZoneSameInstant(Joe.zone());

//        System.out.println(samTime);
//        System.out.println(joeTime);
        Instant start = Instant.now();
        for (int i = 0; i < 11; i++) {
            Instant current = start;
            Instant next = current;
            long daySpan=0;
            while (daySpan < 1) {

                var samH = samTime.getHour();
                var joeH = joeTime.getHour();
                if ((samH >= 7 && samH <= 20) && (joeH >= 7 && joeH <= 20)) {
//                System.out.println(samTime);
//                System.out.println(joeTime);

                    System.out.printf("%s [%s] : %s  <---> %s [%s] : %s%n", Sam.name(), Sam.zone(),
                            samTime.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy, H:mm a")),
                            Joe.name(), Joe.zone(), joeTime.format(DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy, H:mm a")));

                }
                samTime=samTime.plusHours(1);
                joeTime=joeTime.plusHours(1);
                next=next.plus(1,ChronoUnit.HOURS);
                daySpan=Duration.between(current,next).toDays();

            }

            start=next;
        }


    }
}
