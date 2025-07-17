package dev.defCopyShallowDeep;

import java.util.Arrays;

record Person (String name,String dob,Person [] kids){

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", kids=" + Arrays.toString(kids) +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        Person jim=new Person("Jim","08/05/1995",null);
        Person joe=new Person("Joe","08/05/1995",null);
        Person jack=new Person("Jack","08/05/1995",new Person[]{jim,joe});
        Person jane=new Person("Jane","08/05/1995",null);
        Person jill=new Person("Jill","08/05/1995",new Person[]{jim,joe});

        Person[] persons={jim,joe,jack,jane,jill};
//        Person[] personsCopy=Arrays.copyOf(persons,persons.length);

        Person[] personsCopy=new Person[persons.length];
        for (int i = 0; i < persons.length; i++) {
            personsCopy[i]=new Person(persons[i].name(),persons[i].dob(),persons[i].kids());
        }

        var jackKid=jack.kids();
        jackKid[1]=jane;

        for (int i = 0; i < persons.length; i++) {
            if (persons[i]==personsCopy[i]){
                System.out.println("Referecence match = "+persons[i]);
            }
        }

        for (int i = 0; i < persons.length; i++) {
            System.out.println("Persons= "+System.identityHashCode(persons[i]) +"   pcopy   " +System.identityHashCode(personsCopy[i]));
            //System.out.printf("Persons:%13d   PersonCopy:%13d%n",persons[i].hashCode(),personsCopy[i].hashCode());
        }

    }
}
