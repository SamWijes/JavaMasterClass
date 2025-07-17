package studentEngagement;

import java.util.Comparator;
import java.util.stream.Stream;

public class MainTerminalOption {
    public static void main(String[] args) {
        Course pymc=new Course("PYMC","Pyth MC",50);
        Course jmc=new Course("JMC","Java MC",100);

        var students= Stream.generate(()->Student.getRandomStudent(pymc,jmc))
                .limit(1000)
                .toList();

        int minAge=30;
        students.stream()
                .filter(s->s.getAge()<=minAge)
                .findAny()
                .ifPresentOrElse(s-> System.out.printf("Student %d from %s is %d%n",s.getStudentId(),s.getCountryCode()
                ,s.getAge()),()-> System.out.println("Didnt find anyone under "+minAge));

        students.stream()
                .filter(s->s.getAge()<=minAge)
                .findFirst()
                .ifPresentOrElse(s-> System.out.printf("Student %d from %s is %d%n",s.getStudentId(),s.getCountryCode()
                        ,s.getAge()),()-> System.out.println("Didnt find anyone under "+minAge));
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .max(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s-> System.out.printf("Student %d from %s is %d%n",s.getStudentId(),s.getCountryCode()
                        ,s.getAge()),()-> System.out.println("Didnt find anyone under "+minAge));


        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .mapToInt(Student::getAge)
                .average()
                .ifPresentOrElse(s-> System.out.println("Student averahge under 31 =" +s),
                        ()-> System.out.println("no one under 31" ));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .map(Student::getCountryCode)
                .distinct()
                .reduce((r,v)->r+","+v)
                .ifPresentOrElse(System.out::println,()-> System.out.println("None Found"));


        students.stream()
//                .peek(System.out::println)
                .map(Student::getCountryCode)
                .distinct()
                //.map(l->String.join(",",l))
                .peek(System.out::println)
                .filter(s->s.equals("FR"))

                .findAny()
                .ifPresentOrElse(s-> System.out.println("student from france"),()-> System.out.println("None Found"));
    }
}
