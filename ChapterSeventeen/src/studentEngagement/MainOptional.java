package studentEngagement;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainOptional {
    public static void main(String[] args) {
        Course pymc=new Course("PYMC","Pyth MC",50);
        Course jmc=new Course("JMC","Java MC",100);
        Course cgj=new Course("CGJ","Creating Games In Java");

        List<Student> students= Stream.generate(()->Student.getRandomStudent(pymc,jmc))
                .limit(1000)
                .collect(Collectors.toList());

        Optional<Student> o1=getStudent(null,"first");
        System.out.println("Empty ="+o1.isEmpty()+"Present= "+o1.isPresent());
//        System.out.println(o1.get());
//        o1.ifPresentOrElse(System.out::println,()-> System.out.println("--->Empty"));
        Optional<Student> o2=getStudent(students,"first");
        System.out.println("Empty ="+o2.isEmpty()+"Present= "+o2.isPresent());
//        System.out.println(o1);
        o2.ifPresent(System.out::println);
//        Student firstStudent=o2.orElse(getDummyStudent(jmc));
        Student firstStudent= o2.orElseGet(()->getDummyStudent(jmc));

        long id=firstStudent==null?-1:firstStudent.getStudentId();
        System.out.println("First student id is "+id);
    }
    private static Optional<Student> getStudent(List<Student> list,String type){
        if (list==null||list.size()==0){
            return Optional.empty();
        } else if (type.equals("first")) {
            return Optional.ofNullable(list.get(0));
        }else if(type.equals("last")){
            return Optional.ofNullable(list.get(list.size()-1));
        }else return Optional.of(list.get(new Random().nextInt(list.size())));
    }

    private static Student getDummyStudent(Course... courses) {

        System.out.println("Getting the dummy student");
        return new Student("NO", 1, 1, "U",
                false, courses);
    }
}
