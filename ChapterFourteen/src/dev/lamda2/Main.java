package dev.lamda2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        List<String> list=new ArrayList<>(List.of(
           "alpha","bravo","charlie","delta"
        ));

        for (var el:list) {
            System.out.println(el);
        }
        System.out.println("_".repeat(30));
        list.forEach((s)-> System.out.println(s));
        System.out.println("_".repeat(30));
        list.forEach((var mystring)-> {
            char first=mystring.charAt(0);
            System.out.println(first +" means " +mystring);
        });

        System.out.println("_".repeat(30));
        String prefix="nato ";  //This should be final cannot be changed later
        list.forEach((var mystring)-> {
            char first=mystring.charAt(0);
            System.out.println(prefix+first +" means " +mystring);
            //if changed above won't compile
        });


        int cal=calculator((a,b) -> a+b,4,5);
        System.out.println(cal);
        int cal2=calculator((a,b)->{
                      int result =a+b;
                      return result;
                    },5,6);
        System.out.println(cal2);
        var result=calculator((a,b)->a+" "+b,"Sammy","Wije");
        System.out.println(result);

        var coords= Arrays.asList(
                   new double[]{45.586,95.4583},
                   new double[]{43.486,94.4895},
                   new double[]{41.452,94.4475},
                   new double[]{48.892,94.4412}
        );

        coords.forEach(s-> System.out.println(Arrays.toString(s)));
        BiConsumer<Double,Double> p1=(lat,lon)-> System.out.printf("[lat:%.4f lon:%.4f]%n",lat,lon);
        var firstPoint=coords.getFirst();

        System.out.println("_".repeat(30));
        processPoint(firstPoint[0],firstPoint[1],p1);
        System.out.println("_".repeat(30));
        coords.forEach((s)->processPoint(s[0],s[1],p1));

        //removeif on list
        list.removeIf(s -> s.equalsIgnoreCase("bravo"));
        list.forEach(s -> System.out.println(s));
        System.out.println("_".repeat(30));
        list.addAll(List.of("echo","erwin","ernest","engelica"));
        list.removeIf(s -> s.startsWith("er"));
        list.forEach(s -> System.out.println(s));

        list.replaceAll(s -> s.charAt(0)+"-"+s.toUpperCase());
        System.out.println("_".repeat(30));
        list.forEach(s -> System.out.println(s));

        String[] emptyStrings=new String[10];
        Arrays.setAll(emptyStrings,i-> i + 1 +"."+switch (i){
            case 0->"One";
            case 1->"Two";
            default -> "";
        });
        System.out.println(Arrays.toString(emptyStrings));

        String[] names={"Ann","Bob","David","Bill","Carol","Fred","Fury"};
        Supplier<Integer> sup=new Supplier<Integer>() {
            @Override
            public Integer get() {
                int rand=new Random().nextInt(0, names.length);
                return rand;
            }
        };
        String[] randomList=randomlySelectedValues(10,names,sup);
        System.out.println(Arrays.toString(randomList));
        String[] newList=randomlySelectedValues(15,names,
                ()->new Random().nextInt(0, names.length));
        System.out.println(Arrays.toString(newList));

    }


    public static <T> T calculator(BinaryOperator<T> func, T value1, T value2){
        T result = func.apply(value1, value2);
        return result;
    }

    public static<T> void processPoint(T t1, T t2, BiConsumer<T,T> consumer){
        consumer.accept(t1,t2);
    }

    private static String[] randomlySelectedValues(int count, String[] values, Supplier<Integer> s) {
        String[] selectedValues=new String[count];
        for (int i = 0; i < count; i++) {
            selectedValues[i]=values[s.get()];
        }
        return selectedValues;

    }
}
