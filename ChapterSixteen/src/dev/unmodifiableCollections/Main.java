package dev.unmodifiableCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StringBuilder bobsNotes=new StringBuilder();
        StringBuilder billsNotes=new StringBuilder("Bill Struggles with generics");

        Student bob=new Student("Bob",bobsNotes);
        Student bill=new Student("Bob",billsNotes);

        List<Student> students= new ArrayList<>(List.of(bob,bill));
        bobsNotes.append("I have appended this later regarding notes");

        List<Student> studentsFirstCopy=new ArrayList<>(students);
        List<Student> studentsSecondCopy=List.copyOf(students);
        List<Student> studentsThirdCopy= Collections.unmodifiableList(students);

        studentsFirstCopy.add(new Student("Bonnie",new StringBuilder()));
//        studentsSecondCopy.add(new Student("Bonnie",new StringBuilder()));
        studentsFirstCopy.get(2).getStudentNotes().append("Appending to empty bonnies notes");

        students.forEach(System.out::println);
        System.out.println("_".repeat(30));
        studentsFirstCopy.forEach(System.out::println);
        System.out.println("_".repeat(30));
        studentsSecondCopy.forEach(System.out::println);
        System.out.println("_".repeat(30));

    }
}
