package array;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        int[] newArray;
        newArray= new int[]{1, 2, 4, 5};

        int [] newArray2={15,67,4,6,7,};
        int [] newArray3=new int[5] ;
        for (int i=0;i<5;i++) {
            newArray3[i]=i+1;
        }
        System.out.println(newArray3);
        System.out.println( Arrays.toString(newArray3));

        }

}
