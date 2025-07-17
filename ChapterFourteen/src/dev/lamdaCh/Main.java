package dev.lamdaCh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String[] names={"Anna","Bob","David","Bill","Carol","Fred","Fury"};


        Arrays.setAll(names,(i)->names[i].toUpperCase() +"-"+(char)(new Random().nextInt(65,91))+" "+
                new StringBuilder(names[i].toUpperCase()).reverse() );
        Arrays.asList(names).forEach(s -> System.out.println(s));
        //System.out.println(Arrays.toString(names));


        List<String> namesModified= new ArrayList<>(List.of(names));
        namesModified.removeIf(s ->s.endsWith(s.split("-")[0]));
        System.out.println("_".repeat(15));
        namesModified.forEach(s -> System.out.println(s));
        //Arrays.asList(names).removeIf(s ->s.endsWith(s.split("-")[0]));
    }
}
