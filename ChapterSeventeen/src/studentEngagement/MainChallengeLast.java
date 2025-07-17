package studentEngagement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainChallengeLast {
    public static void main(String[] args) {

        Course pymc=new Course("PYMC","Pyth MC",50);
        Course jmc=new Course("JMC","Java MC",60);
        Course js=new Course("JS","Java Script",45);
        Course php=new Course("PHP","PHP",45);

        var students= Stream.generate(()->Student.getRandomStudent(pymc,jmc,js,php))
                .limit(10_000)
                .filter(s->s.getYearsSinceEnrolled()<=4)
                .toList();


        var stat = students.stream()
                .map(s -> s.getEngagementMap().values()) // Stream<Collection<CourseEngagement>>
                        .flatMap(l->l.stream())
                        .collect(Collectors.groupingBy(CourseEngagement::getCourseCode));

        stat.forEach((k,v)-> System.out.println(k+"-"+v.size()));

        var classCounts = students.stream()
                .collect(Collectors.groupingBy(s->s.getEngagementMap().size(),
                        Collectors.counting()));

        classCounts.forEach((k,v)-> System.out.println(k+"-"+v));

        var averagePercentCourse = students.stream()
                .map(s -> s.getEngagementMap().values()) // Stream<Collection<CourseEngagement>>
                .flatMap(l->l.stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.summarizingDouble(CourseEngagement::getPercentComplete)));

       averagePercentCourse.forEach((k,v)-> System.out.println(k+"-"+v));

        var courseActivity = students.stream()
                .map(s -> s.getEngagementMap().values()) // Stream<Collection<CourseEngagement>>
                .flatMap(l->l.stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.groupingBy(CourseEngagement::getLastActivityYear,
                                Collectors.counting())));

        courseActivity.forEach((k,v)-> {
            System.out.println(k+"-"+v);
        });

       students.stream()
                .flatMap(s->s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getEnrollmentYear,
                        Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.counting())))
               .forEach((k,v)-> System.out.println(k+"-"+v));



    }
}
