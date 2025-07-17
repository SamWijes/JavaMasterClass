package dev.generic08;

import dev.generic08.Util.QueryItem;
import dev.generic08.Util.QueryList;

import java.util.ArrayList;
import java.util.List;
record Employee(String name) implements QueryItem{
    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        return false;
    }
}
public class Main {

    public static void main(String[] args) {
        int studentCount=10;
        List<Student> students=new ArrayList<>();
        for(int i=0;i<studentCount;i++){
            students.add(new Student());
        }

        printList(students);
        List<SamStudent> samstudents=new ArrayList<>();
        for(int i=0;i<studentCount;i++){
            samstudents.add(new SamStudent());
        }

        printList(samstudents);

        var studentsforrest= QueryList.getMatMaches(samstudents,"course","javascript");
        var students2020= QueryList.<Student>getMatMaches(new ArrayList<>(),"yearstarted","2022");
        printList(studentsforrest);
        printList(students2020);

    }


    public static void printList(List<? extends Student> students) {
        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

//    public static <T extends Student> void printList(List<T> students){
//        for(var student:students){
//            //System.out.print(student.getYearStarted());
//            System.out.println(student.getYearStarted() +" : "+student);
//
//        }
//    }

//    public static <T> void printList(List<T> students){
//        for(var student:students){
//            System.out.println(student);
//        }
//    }


//
//    public static void printList(List<? super SamStudent > students){
//        for(var student:students){
//            System.out.println(student);
//        }
//    }


}
