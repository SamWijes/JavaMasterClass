package dev.SortedMaps;

import javax.print.DocFlavor;
import java.util.*;

public class Merj {
    public static void main(String[] args) {
        Map<String,String> userLogin=new HashMap<>();
        //userLogin.put("Alice","mmmsetsseee");

        userLogin.compute("Alice",(key,val)->(val==null)?"dsds":val);

        userLogin.forEach((k,v)-> System.out.println(v));

        Map<String, List<String>> courseStudents = new HashMap<>();
        courseStudents.put("Math",new ArrayList<>(List.of("Mark","Ron")));
        courseStudents.put("Science",new ArrayList<>(List.of("Mark","ron")));

        List<String> mathStudents=new ArrayList<>(List.of("Mark","Newt","Carly","Daffy","Ron","Mark","mark"));
        List<String> mathStudents2=new ArrayList<>(List.of("Nevil","Truman","Henry"));
        System.out.println(courseStudents);
        mergeCourse(courseStudents,"Math", mathStudents);
        System.out.println(courseStudents);
        mergeCourse(courseStudents,"Math", mathStudents2);
        System.out.println(courseStudents);

        courseStudents.forEach((k,v)-> System.out.println(v));

        Map<String,Integer> sameStudent=new HashMap<>();
        mathStudents.forEach(s->sameStudent.merge(s,1,(prev,curr)->prev+curr));
        sameStudent.forEach((k,v)-> System.out.println(k+"="+v));
    }

    private static void mergeCourse(Map<String, List<String>> courseStudents,String subject, List<String> mathStudents) {
        courseStudents.merge(subject, mathStudents,(oldList, newList)->{
            Set<String> students=new HashSet<>(oldList);
            newList.forEach(s-> {
                if (students.add(s)) {
                    oldList.add(s);
                }
            });
            return oldList;
        });
    }


}
