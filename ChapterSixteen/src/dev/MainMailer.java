package dev;

import java.util.*;

public class MainMailer {
    public static void main(String[] args) {
        String[] names = {"Ann Jones","Ann Jones Ph.D.", "Bob Jones M.D.",  "Carol Jones",
                "Ed Green Ph.D.",  "Ed Green M.D.","Ed Black"};

        List<StringBuilder> population=getNames(names);

        Map<StringBuilder,Integer> counts=new TreeMap<>();

        population.forEach(s->counts.merge(s,1,(prev,curr)->prev+curr) );

        counts.forEach((k,v)-> System.out.println(k+" : "+v));
        StringBuilder annjhones=new StringBuilder("Ann Jones Ph.D.");
        System.out.println("There are " +counts.get(annjhones) +" records for"+annjhones );
        List<StringBuilder> stdNames= standardizedNames(population);
        System.out.println(stdNames);
        System.out.println("There are " +counts.get(annjhones) +" records for"+annjhones );
        System.out.println(counts);
//        Map<StringBuilder,Integer> stdcounts=new TreeMap<>();
//        stdNames.forEach(s->stdcounts.merge(s,1,(prev,curr)->prev+curr) );
//        stdcounts.forEach((k,v)-> System.out.println(k+" : "+v));

        StringBuilder annjhones2=new StringBuilder("Ann Jones");
        System.out.println("There are " +counts.get(annjhones2) +" records for"+annjhones2 );
        System.out.println("_".repeat(50));
        counts.forEach((k,v)-> System.out.println(k+":"+v));
        System.out.println("_".repeat(50));
        counts.keySet().forEach(k -> System.out.println(k +":"+counts.get(k)));


    }

    public static List<StringBuilder> getNames (String [] names) {
        List<StringBuilder> nameList=new ArrayList<>();
        int index=3;
        for (String name : names) {
            for (int i = 0; i < index; i++) {
                nameList.add(new StringBuilder(name));
            }
            index++;
        }
        return nameList;
    }

    public static List<StringBuilder> standardizedNames(List<StringBuilder> nameList){
        List<StringBuilder> list=new ArrayList<>();
        for (StringBuilder name : nameList) {
            for(String suffix:new String[]{"Ph.D.","M.D."}){
                int sufIndex=name.indexOf(suffix);
                if ( (sufIndex)>0) name.replace(sufIndex-1,name.length(),"");
            }
            list.add(name);
        }
        return list;

    }
}
