package dev.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

record GroceryList (String name,String type,int count){
    public GroceryList(String name){
        this(name,"Produce",1);
    }
}
public class myLists {
    public static void main(String[] args) {

        ArrayList<String> item=new ArrayList<>(List.of("a,b,c,d".split(",")));

        ArrayList<String> arr = new ArrayList<>(Arrays.asList());

        System.out.println(arr);



    }

}
