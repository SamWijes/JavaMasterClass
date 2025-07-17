package dev.dateTimeProject;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;

public class Main {

    public static void main(String[] args) {
        LocalDate today=LocalDate.now();
        System.out.println(today);

        LocalDate five5=LocalDate.of(2025,05,05);
        System.out.println(five5);
        LocalDate may5=LocalDate.of(2025, Month.MAY,05);
        System.out.println(may5);

        LocalDate day125=LocalDate.ofYearDay(2025,125);
        System.out.println(day125);

        LocalDate may5th=LocalDate.parse("2025-05-05");
        System.out.println(may5th);
        System.out.println(may5th.getYear());
        System.out.println(may5th.getMonth());
        System.out.println(may5th.getDayOfMonth());

        System.out.println(today.isLeapYear());
        System.out.println(may5th.minusYears(5).isLeapYear());

        may5th.datesUntil(may5th.plusDays(5)).forEach(System.out::println);
        System.out.println("-------------------");
        may5th.datesUntil(may5th.plusYears(1), Period.ofDays(7))
                .forEach(System.out::println);

        LocalTime time=LocalTime.now();
        System.out.println(time);
        LocalTime sevenAM = LocalTime.of(13,0);
        System.out.println(sevenAM);

        LocalTime sevenThirty = LocalTime.of(7, 30,15,1000);
        System.out.println(sevenThirty);

        LocalTime sevenPM = LocalTime.parse("07:00");
        System.out.println(sevenPM);
        LocalTime sevenThirtyPM = LocalTime.parse("19:30:15.1000");
        System.out.println(sevenPM.get(ChronoField.AMPM_OF_DAY));
        System.out.println(sevenThirtyPM.get(ChronoField.AMPM_OF_DAY));
        System.out.println(sevenThirtyPM.getHour());
        System.out.println(sevenThirtyPM.get(ChronoField.HOUR_OF_DAY));


        System.out.println(sevenPM.range(ChronoField.HOUR_OF_DAY));
        System.out.println(sevenPM.range(ChronoField.MINUTE_OF_HOUR));
        System.out.println(sevenPM.range(ChronoField.MINUTE_OF_DAY));
        System.out.println(sevenPM.range(ChronoField.SECOND_OF_MINUTE));
        System.out.println(sevenPM.range(ChronoField.SECOND_OF_DAY));

        LocalDateTime todayAndNow = LocalDateTime.now();
        System.out.println(todayAndNow);

        LocalDateTime May5noon=LocalDateTime.of(2025,5,05,12,00);
        System.out.printf("%1$tD %1$tr%n",May5noon);
        System.out.printf("%1$tF %1$tT%n",May5noon);

        System.out.println(todayAndNow.format(DateTimeFormatter.ISO_WEEK_DATE));

        DateTimeFormatter dtf=DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        System.out.println(May5noon.format(dtf));
        System.out.println(May5noon.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));


        LocalDateTime May6Noon = May5noon.plusHours(24);
        System.out.println(May6Noon.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));

    }
}
