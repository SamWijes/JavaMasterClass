package dev.lamda3;

import java.util.ArrayList;
import java.util.List;

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
        //System.out.println(cal);
        int cal2=calculator((a,b)->{
                      int result =a+b;
                      return result;
                    },5,6);
        System.out.println(cal2);
    }


    public static <T> T calculator(Operation<T> func, T value1, T value2){
        T result = func.operate(value1, value2);
        return result;
    }
}
