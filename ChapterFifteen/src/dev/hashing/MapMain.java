package dev.hashing;

import java.security.cert.CertPath;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMain {
    public static void main(String[] args) {
        List<Contact> phone=ContactData.getData("phone");
        List<Contact> email=ContactData.getData("email");

        List<Contact> fullList=new ArrayList<>(phone);
        fullList.addAll(email);
        fullList.forEach(System.out::println);
        System.out.println("_".repeat(30));
        Map<String,Contact> contacts=new HashMap<>();
        for(var contact:fullList){
            contacts.put(contact.getName(),contact);
        }

        contacts.forEach((k,v)-> System.out.println("key:"+k+"  "+"value: "+v));
        System.out.println("_".repeat(30));
        contacts.clear();

        for(var contact:fullList){
            Contact duplicate=contacts.put(contact.getName(),contact);
            if (duplicate != null) {
//                System.out.println("duplicate: "+duplicate);
//                System.out.println("Current: "+contact);

                contacts.put(contact.getName(),contact.mergerContactData(duplicate));
            }

        }
        contacts.forEach((k,v)-> System.out.println("key:"+k+"  "+"value: "+v));
        System.out.println("_".repeat(30));
        contacts.clear();

        System.out.println("_".repeat(30));
        for(var contact:fullList){
            contacts.putIfAbsent(contact.getName(),contact);
        }

        contacts.forEach((k,v)-> System.out.println("key:"+k+"  "+"value: "+v));

        System.out.println("_".repeat(30));
        contacts.clear();
        fullList.forEach(contact -> contacts.merge(contact.getName(), contact,
                Contact::mergerContactData));

        System.out.println("_".repeat(30));

        contacts.forEach((k,v)-> System.out.println("key:"+k+"  "+"value: "+v));

//        System.out.println("_".repeat(30));
//        for(String contact:new String[]{"Daisy Duck","Daffy Duck","Scrooge McDuck"}){
//            contacts.compute(contact,(k,v)->new Contact(k));
//        }
//        contacts.forEach((k,v)-> System.out.println("key :"+k+"  "+"value: "+v));

        System.out.println("_".repeat(30));
        for(String contact:new String[]{"Daisy Duck","Daffy Duck","Scrooge McDuck"}){
            contacts.computeIfAbsent(contact,(k)->new Contact(k));
        }
        contacts.forEach((k,v)-> System.out.println("key:"+k+"  "+"value: "+v));

        System.out.println("_".repeat(30));
        for(String contact:new String[]{"Daisy Duck","Daffy Duck","Scrooge McDuck"}){
            contacts.computeIfPresent(contact,(k,v)-> {
                v.addEmail("FunPlace");
                return v;
            });
        }
        contacts.forEach((k,v)-> System.out.println("key:"+k+"  "+"value: "+v));
        System.out.println("_".repeat(30));
        contacts.replaceAll((k,v)-> {
            String newEmail=k.replaceAll(" ","") +"@funplace.com";
            v.replaceEmailIfExists("DDuck@funplace.com",newEmail);
            return v;
        });
        contacts.forEach((k,v)-> System.out.println("key:"+k+"  "+"value: "+v));
        System.out.println("_".repeat(30));
        Contact daisy=new Contact("Daisy Jane Duck","daisyj@duck.com");
        System.out.println("daisy="+daisy);
        Contact replacedContact = contacts.replace("Daisy Duck",daisy);

        System.out.println("replacedContact="+replacedContact);
        contacts.forEach((k,v)-> System.out.println("key:"+k+"  "+"value: "+v));
        System.out.println("_".repeat(30));
        Contact jack=new Contact("Jack Rach","daisyj@ranch.com");
        contacts.replace("Daisy Duck",daisy,jack);
        contacts.forEach((k,v)-> System.out.println("key:"+k+"  "+"value: "+v));
        Contact updatedDaisy=replacedContact.mergerContactData(daisy);
        boolean success=contacts.replace("Daisy Duck",jack,updatedDaisy);
        if (success) {
            System.out.printf("Contact values %s and key %s Matched%n","Daisy Duck",daisy);
        }else System.out.println("keys or values not matched");

        success=contacts.remove("Daisy Duck",updatedDaisy);
        System.out.println(success);


    }
}
