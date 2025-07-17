package dev.constructorProject;

import dev.constructorExternal.Child;

public class Main {
    public static void main(String[] args) {

        Parent parent=new Parent();
        Child child=new Child();

        System.out.println("Parent: "+parent);
        System.out.println("Child: "+child);

        Person joe=new Person("Joe","05/04/1996");
        Person joeCopy=new Person(joe);
        System.out.println(joeCopy);
        System.out.println(joe);

        Generation g=Generation.GEN_X;

    }
}
