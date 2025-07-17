package dev.generic08;

import dev.generic08.Util.QueryItem;

import java.util.Random;

public class Student implements QueryItem ,Comparable<Student> {
    private String name;
    private String course;

    private static int lastId=100;
    private int studentId;
    private int yearStarted;
    static Random random=new Random();

    private static String[] courses={"JavaScript","Java","Python"};
    private static String [] names={"Forest","Parker","Potter","Clint"};

    public Student() {
        this.name = names[ random.nextInt(0,4)]+"."+ ((char) random.nextInt(65, 91));
        this.course = courses[ random.nextInt(0,3)];
        this.yearStarted = random.nextInt(2020,2024);
        this.studentId=++lastId;
    }

    @Override
    public String toString(){
        return "%-5d %-10s %-12s %d".formatted(studentId,name,course,yearStarted);
    }

    public int getYearStarted() {
        return yearStarted;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        String fname=fieldName.toUpperCase();
        return switch (fname){
            case "NAME"->name.equalsIgnoreCase(value);
            case "COURSE"->course.equalsIgnoreCase(value);
            case "YEARSTARTED"->yearStarted==Integer.parseInt(value);
            default -> false;
        };
    }

    @Override
    public int compareTo(Student o){
        return ((Integer) studentId).compareTo(o.studentId);
    }


}
