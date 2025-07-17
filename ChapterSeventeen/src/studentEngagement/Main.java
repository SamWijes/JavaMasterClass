package studentEngagement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Course pymc= new Course("PYMC", "Python Masterclass");
        Course jmc= new Course("JMC", "Java Masterclass");

//        var couseStream= Stream.generate(()->Student.getRandomStudent(pymc,jmc))
//                .limit(100);


        //How many male and female students are in the group.


//        var maleStudets=couseStream.filter(s->s.getGender()=="M");
        //var femaleStudets=couseStream.filter(s->s.getGender()=="U");
//        System.out.println(maleStudets.count()+ " is the no of male students");
        //System.out.println(femaleStudets.count()+ "is the no of female students");

        Student[] students=new Student[100];
        Arrays.setAll(students,i->Student.getRandomStudent(pymc,jmc));
        for (String gender : List.of("M", "F", "U")) {
            var mySt=Arrays.stream(students)
                    .filter(student -> student.getGender().equals(gender))
                    .count();

            System.out.println("#Number of " + ((gender=="M")?"Male":gender=="F"?"Female":"Unknown")+"="+mySt);

        }

        var ageStream=Arrays.stream(students)
                .mapToInt(Student::getAgeEnrolled);


        System.out.println("Stats for age"+ageStream.summaryStatistics());


        var currentAgeStream=Arrays.stream(students)
                .mapToInt(Student::getAge);


        System.out.println("Stats for Current age"+currentAgeStream.summaryStatistics());

        Arrays.stream(students)
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .forEach(s-> System.out.print(s+" "));
        System.out.println();
        var longTerm=Arrays.stream(students)
                .anyMatch(s->s.getAge()-s.getAgeEnrolled()>7 && s.getMonthsSinceActive()>12);

        var longTermCount=Arrays.stream(students)
                .filter(s->s.getAge()-s.getAgeEnrolled()>7 && s.getMonthsSinceActive()>12)
                .limit(5)
                .count();

        System.out.println(longTermCount);

//        Arrays.stream(students)
//                .filter(s->s.getAge()-s.getAgeEnrolled()>7 && s.getMonthsSinceActive()>12)
//                .limit(5)
//                .toList()
//                .forEach(System.out::println);

        var longTermLearners= Arrays.stream(students)
                .filter(s->s.getAge()-s.getAgeEnrolled()>7 && s.getMonthsSinceActive()>12)
                .limit(5)
                .toArray(Student[]::new);


        var learners= Arrays.stream(students)
                .filter(s->s.getAge()-s.getAgeEnrolled()>7 && s.getMonthsSinceActive()>12)
                .limit(5)
                .collect(Collectors.toList());
        //longTermLearners.forEach(System.out::println);

    }


}
