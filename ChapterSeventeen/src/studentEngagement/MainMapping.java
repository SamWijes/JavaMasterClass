package studentEngagement;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class MainMapping {
    public static void main(String[] args) {
        Course pymc=new Course("PYMC","Pyth MC",50);
        Course jmc=new Course("JMC","Java MC",100);

        List<Student> students=IntStream
                .rangeClosed(1,200)
                .mapToObj(i->Student.getRandomStudent(pymc,jmc))
                .toList();

        var mappedStudents=students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode));



        mappedStudents.forEach((k,v)-> System.out.println(k+"-"+v.size()));

        int minAge=25;
        var youngerStudents=students.stream()
                .collect(groupingBy(Student::getCountryCode,
                        filtering(s->s.getAge()<=minAge,toList())));


        youngerStudents.forEach((k,v)-> System.out.println(k+"-"+v.size()));

        var experienced=students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience));
        System.out.println("Exp Students= "+experienced.get(true).size());

        var experiencedCount=students.stream()
                .collect(partitioningBy(Student::hasProgrammingExperience,counting()));
        System.out.println("Exp Students= "+experiencedCount.get(true));

        var multilevel=students.stream()
                .collect(groupingBy(Student::getCountryCode,
                        groupingBy(Student::getGender)));


        multilevel.forEach((k,v)-> {
           System.out.println(k);
           v.forEach((a,b)-> System.out.println("\t"+a+"-"+b.size()));
        });

        long stdCount=0;
        for (List<Student> value : experienced.values()) {
            stdCount+=value.size();
        }
        System.out.println(stdCount);

        var studentCount=experienced.values().stream()
                .mapToInt(l->l.size())
                .sum();

        studentCount= (int) experienced.values().stream()
                .map(l->l.stream()
                        .filter(s->s.getMonthsSinceActive()<=3)
                        .count()
                )
                .mapToLong(i->i)
                .sum();
        System.out.println("StudentBodyCount="+studentCount);

        long count=experienced.values().stream()
                .flatMap(l->l.stream())
                .filter(s->s.getMonthsSinceActive()<=3)
                .count();

        System.out.println("StudentBodyCount="+count);

        count=multilevel.values().stream()
                .flatMap(map->map.values().stream()
                        .flatMap(l->l.stream())
                )

                .filter(s->s.getMonthsSinceActive()<=3)
                .count();

        System.out.println("StudentBodyCount="+count);


        count= multilevel.values().stream()
                .flatMap(map-> map.values().stream())
                .flatMap(l->l.stream())
                .filter(s->s.getMonthsSinceActive()<=3)
                .count();

        System.out.println("StudentBodyCount="+count);

    }
}
