package studentEngagement;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCollect {
    public static void main(String[] args) {
        Course pymc=new Course("PYMC","Pyth MC");
        Course jmc=new Course("JMC","Java MC");

        List<Student> studentList=Stream.generate(()->Student.getRandomStudent(pymc,jmc))
                .limit(1000)
                .toList();

        Set<Student> ausStudent=studentList.stream()
                .filter(student->student.getCountryCode()=="AU")
                .collect(Collectors.toSet());
        System.out.println("#Aus student count "+ausStudent.size());


        Set<Student> underThirty=studentList.stream()
                .filter(student->student.getAgeEnrolled()<30)
                .collect(Collectors.toSet());
        System.out.println("#underThirty student count "+underThirty.size());

        //use set operation for aus studenet s under 30
        Set<Student> youngAussie1=new TreeSet<>(Comparator.comparing(Student::getStudentId));
        youngAussie1.addAll(ausStudent);
        youngAussie1.retainAll(underThirty);
        youngAussie1.forEach(s-> System.out.print(s.getStudentId()+" "));
        System.out.println();

        Set<Student> youngAussie2=studentList.stream()
                .filter(student->student.getCountryCode().equals("AU"))
                .filter(student->student.getAgeEnrolled()<30)
//                .collect(Collectors.toSet());
                .collect(()->new TreeSet<>(Comparator.comparing(Student::getStudentId)),TreeSet::add,TreeSet::addAll);

        youngAussie2.forEach(s-> System.out.print(s.getStudentId()+" "));
        System.out.println();
        var countryCode=studentList.stream()
                .map(Student::getCountryCode)
                .distinct()
                .reduce((r,v)->r+" "+v).orElse("");



        System.out.println("Country List="+countryCode);
    }
}
