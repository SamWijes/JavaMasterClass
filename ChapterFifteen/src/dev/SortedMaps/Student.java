package dev.SortedMaps;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

record Course(String courseID,String name,String subject){}
record Purchase(String courseID,int studentID,double price,int year,int dayOfYear){

    public LocalDate purchaseDate(){
        return LocalDate.ofYearDay(year,dayOfYear);
    }
}
public class Student {

    public static int lastID=1;
    private String name;
    private int id;
    private List<Course> courseList;

    public Student(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
        id=lastID++;

    }
    public Student(String name, Course course) {
        this(name,new ArrayList<>(List.of(course)));
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }


    public void addCourse(Course course){
        courseList.add(course);
    }

    @Override
    public String toString() {
        String[] courseNames=new String[courseList.size()];
        Arrays.setAll(courseNames,i->courseList.get(i).name());
        return "[%d] : %s".formatted(id,String.join(",",courseNames));
    }
}
