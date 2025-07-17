package dev.hashing;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Contact contact=new Contact("sam","sam@gmail.com",1456981236);
        Contact contact2=new Contact("sam","sam2@gmail.com",1356974236);
        Contact contact3=contact.mergerContactData(contact2);
        System.out.println(contact);
        System.out.println(contact2);
        System.out.println(contact3);

        List<Contact> c1=ContactData.getData("email");
        List<Contact> c2=ContactData.getData("phone");
//        c1.forEach(System.out::println);
//        c2.forEach(System.out::println);
        printData("email",c1);
        printData("phone",c2);

        Set<Contact> phoneContacts=new HashSet<>(c2);
        Set<Contact> emailContacts=new HashSet<>(c1);

        printData("email",emailContacts);
        printData("phone",phoneContacts);

        int index=c1.indexOf(new Contact( "Daffy Duck"));
        Contact dd=c1.get(index);
        dd.addEmail("Dan Dex");

        System.out.println(dd);

        c1.get(0).addEmail("Rathan Group");
        System.out.println(c1.get(0));

        c1.get(0).replaceEmailIfExists("MMouse@rathangroup.com","MMouse@iamreplaced.com");
        System.out.println(c1.get(0));


        Set<Contact> unionAB= new HashSet<>();
        unionAB.addAll(phoneContacts);
        unionAB.addAll(emailContacts);
        printData("A \u222A B",unionAB);
        Set<Contact> intersectAB= new HashSet<>(phoneContacts);
        intersectAB.retainAll(emailContacts);
        printData("A \u2229 B",intersectAB);


        Set<Contact> minusAB= new HashSet<>(phoneContacts);
        minusAB.removeAll(emailContacts);
        printData("A-B",minusAB);
        Set<Contact> minusBA= new HashSet<>(emailContacts);
        minusBA.removeAll(phoneContacts);
        printData("B-A",minusBA);


    }
    public static void printData(String header, Collection<Contact> contacts){
        System.out.println("-".repeat(50));
        System.out.println(header);
        System.out.println("-".repeat(50));
        contacts.forEach(System.out::println);
    }
}
