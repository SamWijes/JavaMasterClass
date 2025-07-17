package dev.immutableClasses;

import java.util.Arrays;

public class MainRecord {
    public static void main(String[] args) {
        PersonRecord jane=new PersonRecord("Jane","01/01/1930");
        PersonRecord jim=new PersonRecord("Jim","02/05/1945");
        PersonRecord joe=new PersonRecord("Joe","02/05/1925");


        PersonRecord[] kids= {jane, jim, joe};
        PersonRecord john=new PersonRecord("John","02/05/1900",kids);
        PersonRecord johnCopy=new PersonRecord("John","02/05/1900");

        System.out.println(john);
        System.out.println(johnCopy);
    }
}
