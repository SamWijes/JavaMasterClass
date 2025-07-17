package dev.MethRefCh;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        String[] names={"Anna","Bob","David","Bill","Carol","Fred","Fury"};


        Arrays.setAll(names,(i)->names[i].toUpperCase()+" " +(char)(new Random().nextInt(65,91))+". "+
                new StringBuilder(names[i].toUpperCase()).reverse() );
        Arrays.asList(names).forEach(System.out::println);
        //System.out.println(Arrays.toString(names));


        List<String> namesModified= new ArrayList<>(List.of(names));
       // namesModified.removeIf(s ->s.substring(0,s.indexOf(" ")).equalsIgnoreCase(s.substring(s.lastIndexOf(" ")+1)));
        namesModified.removeIf(s->s.endsWith(s.substring(0,s.indexOf(" "))));
        System.out.println("_".repeat(15));
        namesModified.forEach(System.out::println);
        //Arrays.asList(names).removeIf(s ->s.endsWith(s.split("-")[0]));
        System.out.println("*".repeat(15));


        String[] newNames={"Anna","Bob","David","Bill","Carol","Fred","Fury"};
        List<UnaryOperator<String>> ref=List.of(
                String::toUpperCase,
                s->s+" "+(char)new Random().nextInt(65,90)+". ",
                s->s+new StringBuilder(s.substring(0,s.indexOf(" "))).reverse()
        );
        transformList(ref,newNames);
        System.out.println(Arrays.toString(newNames));

        String[] newNames2={"Anna","Bob","David","Bill","Carol","Fred","Fury"};
        listFuncTransform(ref,newNames2);

        //System.out.println(Arrays.toString(newNames));
//        List<String> nameMethod=new ArrayList<>(arrayProcess(String::toUpperCase,
//                (s)-> String.valueOf(new StringBuilder(s).reverse()).toUpperCase(),newNames));

       // arrayProcess(String::toUpperCase,(s)-> String.valueOf(new StringBuilder(s).reverse()).toUpperCase(),newNames);
        //Arrays.asList(newNames).forEach(System.out::println);
    }

    public static  String[] arrayProcess(UnaryOperator<String> cap,UnaryOperator<String> rev,String[] array){

        Arrays.setAll(array,i->array[i].transform(cap) +" "+ (char)new Random().nextInt(65,90)+". "+array[i].transform(rev));
        List<String> list=Arrays.asList(array);
        return array;
    }

    public static void transformList(List<UnaryOperator<String>> s,String[] array){
       var list= Arrays.asList(array);
        for(var ref:s){
           list.replaceAll(ref);
        }
    }
    public static void listFuncTransform(List<UnaryOperator<String>> strFunc,String[] array){
        var list= Arrays.asList(array);
        for(var function:strFunc){
            list.replaceAll(s ->s.transform(function));
            System.out.println(Arrays.toString(array));
        }
    }
}
