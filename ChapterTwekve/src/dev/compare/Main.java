package dev.compare;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Integer five=5;
        Integer[] others={0,5,-50,50};
        for(Integer i:others){
            int val =five.compareTo(i);
            System.out.printf("%d %s %d :comparedTo=%d%n",five,(val==0)?"==":(val>0)?">":"<",i,val  );
        }
        String banana="banana";
        String [] fruits={"apple","banana","Banana","pear","Mango"};

        for (String i:fruits) {
            int val =banana.compareTo(i);
            System.out.printf("%s %s %s :comparedTo=%d%n",banana,(val==0)?"==":(val>0)?">":"<",i,val  );
        }

        Student [] students={new Student("Alice",3.8),new Student("John",3.75),new Student("Charlie",3.85)};
        Arrays.sort(students);
        System.out.println(Arrays.toString(students));
        StudentGPASorter gpaSorter=new StudentGPASorter();

        Arrays.sort(students,gpaSorter);

        System.out.println(Arrays.toString(students));
        Arrays.sort(students,gpaSorter.reversed());

        System.out.println(Arrays.toString(students));
    }
}

class StudentGPASorter implements Comparator<Student>{

    @Override
    public int compare(Student o1, Student o2) {
        return (o1.gpa+o1.name).compareTo(o2.gpa+o2.name);
    }
}

class Student implements Comparable<Student>{
    String name;
    private static int BASE_INDEX=1000;
    private int index;
    protected double gpa;

    public Student(String name,double gpa) {
        this.name = name;
        this.gpa=gpa;
        this.index=++BASE_INDEX;
    }

    @Override
    public String toString() {
        return String.format("%d - %s -%.2f" ,index,name,gpa);
    }

    @Override
    public int compareTo(Student o) {
        return Integer.valueOf(index).compareTo(o.index);
    }
}
