package dev.hashing;

import java.util.*;

public class MapViewsMain {
    public static void main(String[] args) {
        Map<String,Contact> contacts=new HashMap<>();
        ContactData.getData("phone").forEach(c->contacts.put(c.getName(),c));
        ContactData.getData("email").forEach(c->contacts.put(c.getName(),c));

        Set<String> keysView=contacts.keySet();
//        System.out.println(keysView);
//
//        Set<String> copyKeySet=new TreeSet<>(contacts.keySet());
//        System.out.println(copyKeySet);
//
//        if(contacts.containsKey("Lucy Van Pelt")) System.out.println("lucy is there");
//
//        keysView.remove("Daffy Duck");
//        System.out.println(keysView);
//        contacts.forEach((k,v)-> System.out.println(v));
//
//        System.out.println("_".repeat(30));
//        copyKeySet.remove("Linus Van Pelt");
//        System.out.println(keysView);
//        contacts.forEach((k,v)-> System.out.println(v));
//
//        System.out.println("_".repeat(30));
//        List<String> lst=new ArrayList<>(List.of("Minnie Mouse, Maid Marion, Charlie Brown, Robin Hood, Mickey Mouse".split(", ")));
//        lst.replaceAll(String::trim);
//        keysView.retainAll(lst);
//        System.out.println(keysView);
//        contacts.forEach((k,v)-> System.out.println(v));
//
        System.out.println("_".repeat(30));

        keysView.clear();
        ContactData.getData("email").forEach(c->contacts.put(c.getName(),c));
        ContactData.getData("phone").forEach(c->contacts.put(c.getName(),c));
        //System.out.println(keysView);
        System.out.println(contacts);
        var values=contacts.values();
        values.forEach(System.out::println);

        values.retainAll(ContactData.getData("email"));
        System.out.println(keysView);

        contacts.forEach((k,v)-> System.out.println(v));

        System.out.println("_".repeat(30));
        List<Contact> list=new ArrayList<>(values);

        list.sort(Comparator.comparing(Contact::getNameLastFirst));

        list.forEach(c-> System.out.println(c.getNameLastFirst() +":"+ c));

        System.out.println("_".repeat(30));
        System.out.println(values);
        Contact first=list.get(0);
        System.out.println("first: "+first);

        contacts.put(first.getNameLastFirst(), first);
        values.forEach(System.out::println);
        System.out.println("_".repeat(30));
        keysView.forEach(System.out::println);

        HashSet<Contact> set=new HashSet<>(values);
        set.forEach(System.out::println);
        System.out.println("_".repeat(30));
        if (set.size()<contacts.keySet().size()) {
            System.out.println("duplicates values in values collection");
        }

        Set<Map.Entry<String,Contact>> nodeSet=contacts.entrySet();

        for(var node:nodeSet){
            System.out.println(node.getClass().getName());
            if(!node.getKey().equals(node.getValue().getName())){
                System.out.println(node.getClass().getName());
                System.out.println("Key Doesnt Match Name "+node.getKey() +":"+node.getValue());
            }
        }

    }
}
