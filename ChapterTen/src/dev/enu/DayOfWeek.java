package dev.enu;

public enum DayOfWeek {
        SON,MON,TUES,WED,THURS,FRI,SAT;

        public void getFullDate(){
                switch (this){
                        case SON, MON, TUES, WED, THURS,FRI -> System.out.println(this.name().
                                charAt(0)+this.name().substring(1).toLowerCase()+"day");
                        default -> System.out.println("some other day");
                }
        }
}
