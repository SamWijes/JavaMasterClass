package dev.lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    record Person (String firstName,String lastName){
        @Override
        public String toString() {
            return firstName +" "+ lastName;
        }
    }

    public static void main(String[] args) {
        List<Person> personList=new ArrayList<>(Arrays.asList(
                new Person("Timmy","Bean"),
                new Person("Colt","Bean"),
                new Person("Reji","Nold"),
                new Person("Marty","Nold"),
                new Person("Cory","Franklin")
        )) ;
        var comparator=new Comparator<Person>(){
            @Override
            public int compare(Person o1, Person o2) {
                return o1.lastName.compareTo(o2.lastName);
            }
        };

        interface EnhancedComparator<T> extends Comparator<T>{
            int secondLevel (T o1,T o2);
        }

        personList.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));

        System.out.println(personList);


        var compMixed=new EnhancedComparator<Person>(){

            @Override
            public int compare(Person o1, Person o2) {
                int result=o1.lastName.compareTo(o2.lastName);
                return (result==0)? secondLevel(o1,o2):result;
            }

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName.compareTo(o2.firstName);
            }
        };


        personList.sort(compMixed);
        System.out.println(personList);
    }
}
