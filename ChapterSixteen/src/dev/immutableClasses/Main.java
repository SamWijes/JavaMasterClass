package dev.immutableClasses;

public class Main {
    public static void main(String[] args) {
//
//        Person jane=new Person();
//        jane.setName("Jane");
//        Person jim=new Person();
//        jim.setName("Jim");
//        Person joe=new Person();
//        joe.setName("Joe");
//        Person john=new Person();
//        john.setName("John");
//        john.setDob("05/05/1990");
//        john.setKids(new Person[]{jane,jim,joe});

//        System.out.println(john);

        Person jane=new Person("Jane","01/01/1930");
        Person jim=new Person("Jim","02/05/1945");
        Person joe=new Person("Joe","02/05/1925");
        Person john=new Person("John","02/05/1900");
        john.setKids(new Person[]{jane, jim, joe}); //this array is mutable if accessed
        Person[] jkids=john.getKids();
        //accessing kids array on jhon
        System.out.println(jkids.hashCode());
        //the object cannot be changed oor new object not created but within the object container values are changed
        Person[] jkids2=john.getKids();
        jkids2[0]=new Person("Ann","25/05/1935");
        //jkids=null;
        System.out.println(jkids2.hashCode());
    }
}
