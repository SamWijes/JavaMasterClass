package dev.constructorProject;

public record Person(String name ,String dob) {

//    public Person(String name, String dob) {
//        this.name = name;
//        this.dob = dob.replaceAll("/","-");
//
//    }

    public Person(Person p) {
        this(p.name,p.dob);
    }

    public Person {
        dob=dob.replace("/","-");
    }
}
