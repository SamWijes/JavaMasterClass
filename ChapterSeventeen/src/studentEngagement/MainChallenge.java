package studentEngagement;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainChallenge {

    public static void main(String[] args) {
        Course pymc=new Course("PYMC","Pyth MC",50);
        Course jmc=new Course("JMC","Java MC",100);
        Course cgj=new Course("CGJ","Creating Games In Java");

        var students= Stream.generate(()->Student.getRandomStudent(pymc,jmc,cgj))
                .limit(5000)
                .toList();


        var totalMarks= students.stream()
                .mapToDouble(s->s.getPercentComplete("PYMC"))
                .sum();
        double average=totalMarks/students.size();
        System.out.println("Average = "+average);


        var aboveAverage= students.stream()
                .filter(i -> i.getPercentComplete("JMC") >= average * 1.25)
                .toList();

        System.out.println(aboveAverage.size());

    }
}
