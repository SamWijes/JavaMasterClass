package dev.hashing;

import javax.management.InvalidAttributeValueException;
import java.util.*;

public class TreeSetMain {
    public static void main(String[] args) {

        List<Contact> phones=ContactData.getData("phone");
        List<Contact> emails=ContactData.getData("email");

//        NavigableSet<Contact> sortTree=new TreeSet<>(phones);
        Comparator<Contact> mySort=Comparator.comparing(Contact::getName);
        NavigableSet<Contact> sortTree=new TreeSet<>(mySort);
        sortTree.addAll(phones);
        sortTree.forEach(System.out::println);
        NavigableSet<String> justNames=new TreeSet<>();
        sortTree.forEach(s->justNames.add(s.getName()));
        System.out.println(justNames);

        NavigableSet<Contact> fullSet=new TreeSet<>(mySort);
        fullSet.addAll(phones);
        fullSet.forEach(System.out::println);

        List<Contact> fullList=new ArrayList<>();
        fullList.addAll(emails);
        fullList.sort(sortTree.comparator());
        System.out.println("_______________");
        fullList.forEach(System.out::println);

        //max min

        Contact max=Collections.max(fullSet,fullSet.comparator());  //should be sorted
        Contact min=Collections.min(fullSet,fullSet.comparator());  //

        Contact first=fullSet.first();
        Contact last=fullSet.last();

        System.out.println("_".repeat(20));
        System.out.printf("first=%s last= %s%n".formatted(first.name,last.name));
        System.out.printf("max=%s min= %s%n".formatted(first.name,last.name));
        System.out.println("_".repeat(20));

        NavigableSet<Contact> cpSet=new TreeSet<>(fullSet);
        System.out.println("Poll First "+cpSet.pollFirst());
        System.out.println("Poll Last "+cpSet.pollLast());
        cpSet.forEach(System.out::println);

        Contact daffy=new Contact("Daffy Duck");
        Contact daisy=new Contact("Daisy Duck");
        Contact snoopy=new Contact("Snoopy");
        Contact archie=new Contact("Archie");

        System.out.println("-".repeat(30));

        fullSet.forEach(System.out::println);
        System.out.println("-".repeat(30));
        for (var c: List.of(daffy,daisy,last,snoopy) ) {
            System.out.printf("Ceiling(%s)=%s%n", c.getName(),fullSet.ceiling(c));
            System.out.printf("higher(%s)=%s%n", c.getName(),fullSet.higher(c));
        }
        System.out.println("-".repeat(30));

        fullSet.forEach(System.out::println);
        System.out.println("-".repeat(30));
        for (var c: List.of(daffy,daisy,first,archie) ) {
            System.out.printf("Floor(%s)=%s%n", c.getName(),fullSet.floor(c));
            System.out.printf("lower(%s)=%s%n", c.getName(),fullSet.lower(c));
        }
        System.out.println("-".repeat(30));

        //backing set will be afftected if derived set is changed ex:using poll
        NavigableSet<Contact> descendingSet=fullSet.descendingSet();
        descendingSet.forEach(System.out::println);
        System.out.println("-".repeat(30));

        System.out.println("Removed User "+descendingSet.pollLast());
        descendingSet.forEach(System.out::println);
        System.out.println("-".repeat(30));
        fullSet.forEach(System.out::println);

        Contact micky=new Contact("Mickey Mouse");
        System.out.println("-".repeat(30));
        fullSet.tailSet(micky).forEach(System.out::println);
        System.out.println("-".repeat(30));
        fullSet.headSet(micky).forEach(System.out::println);
        //Can Include->inclusive boolean its fault by default
//        System.out.println("-".repeat(30));
//        fullSet.tailSet(micky,true).forEach(System.out::println);
//        System.out.println("-".repeat(30));
//        fullSet.headSet(micky,true).forEach(System.out::println);

        Contact linus=new Contact("Lucy Van Pelt");
        var subSet1=fullSet.subSet(linus,false,micky,false);
        var subSet2=fullSet.subSet(linus,micky);
        subSet1.forEach(System.out::println);
        System.out.println("-".repeat(30));
        subSet2.forEach(System.out::println);

    }
}
