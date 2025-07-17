package dev.MethodReferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

class PlainOld{
    private static int LAST_ID=1;
    private int id;
    public PlainOld() {
        id=PlainOld.LAST_ID++;
        System.out.println("Creating an PlainOld object : id= "+id);

    }
    @Override
    public String toString(){
        return "Creating an PlainOld object : id=" +id;
    }
}

public class Main {

    public static void main(String[] args) {
        List<String > list=new ArrayList<>(List.of(
                "Anna","Bob","Tim","Robert","Dave"
        ));
        list.forEach(System.out::println);
        calculator((a,b)->a+b,4,5);
        calculator(Integer::sum,4,5);

        Supplier<PlainOld> reference1=()->new PlainOld();
        Supplier<PlainOld> reference2= PlainOld::new;

        reference2.get();
        System.out.println("________");
        PlainOld[] newArray=seedArray(PlainOld::new,10);

       // Arrays.asList(newArray).forEach(System.out::println);
        System.out.println("________");

        calculator((a,b)->a.concat(b),"hello ","World");//concat called on an instance a
        calculator(String::concat,"hello ","World");//using reference method

        BinaryOperator<String> b1=String::concat;
        BiFunction<String,String,String> b2=(a,b)->a.concat(b);
        UnaryOperator<String> u1=String::toUpperCase;
        System.out.println(b1.apply("sam"," Wije"));
        System.out.println(b2.apply("sam"," Wije"));
        System.out.println(u1.apply("sam"));
        System.out.println("________");
        String result="Hello".transform(u1);
        System.out.println(result);
        System.out.println(result.transform(String::toLowerCase).toString());

        Function<String ,Boolean> f0=(a)->a.equalsIgnoreCase("hello");
        boolean resEmpty=result.transform(f0);
        System.out.println(resEmpty);//true
        Function<String ,Boolean> f1=String::isEmpty;
        boolean resEmpty2=result.transform(f1);
        System.out.println(resEmpty2);//false
    }

    public static <T> void calculator(BinaryOperator<T> func,T t1,T t2 ){
        T result=func.apply(t1,t2);
        System.out.println(result);
    }
    public static <T> PlainOld[] seedArray(Supplier<T> func,int count ){
        PlainOld[] array=new PlainOld[count];
        Arrays.setAll(array,(i)->func.get());
        return array;
    }
}
