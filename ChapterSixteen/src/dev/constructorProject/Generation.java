package dev.constructorProject;

import java.time.LocalDate;

public enum Generation {
    GEN_X{
        {
            System.out.println("-- SPECIAL FOR "+this);
        }
    },
    MILLENNIAL(1990,2000),
    BABY_BOOMER(1980,1990),
    GREATER_GENERATION(1970,1980);

    private int startYear;
    private int endYear;

    Generation() {
        this(2001, LocalDate.now().getYear());
    }

    Generation(int startYear, int endYear) {
        this.startYear=startYear;
        this.endYear=endYear;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.name()+"-"+startYear+"-"+endYear;
    }
}
