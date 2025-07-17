package array;

import java.util.Arrays;
import java.util.Scanner;

public class Challenge09 {

    public static void main(String[] args) {
//        int[] myArray=readIntegers();
//        System.out.println("Array= "+Arrays.toString(myArray));
//
//        System.out.println(findMin(myArray));
        int[] myArray= {1, 2, 3, 4,5};
        System.out.println(Arrays.toString(myArray));
        int[] revArray=reverseArray(myArray);
        System.out.println(Arrays.toString(revArray));
        int[] revArray2=reverseArray2(myArray);
        System.out.println(Arrays.toString(revArray2));
    }


    public static int[] readIntegers(){
        Scanner s=new Scanner(System.in);
        System.out.println("Enter Array");
        String strIn=s.nextLine();
        String[] array=strIn.split(",");
        int[] arrayInt=new int[array.length];
        for (int i = 0; i < arrayInt.length; i++) {
            arrayInt[i]=Integer.parseInt(array[i]);
        }
        return arrayInt;
    }

    public static int findMin(int[] array){
        int minNum = array[0];
        for (int i = 0; i < array.length; i++) {

            minNum=Integer.min(minNum,array[i]);
        }
        return minNum;
    }


    public static int[] reverseArray(int[] array) {
        int[] arrayRev=new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayRev[i]=array[array.length-1-i];
        }
        return arrayRev;
    }

    public static int[] reverseArray2(int[] array) {
        int arrLen= array.length-1;
        int arraymid=arrLen/2;
        for (int i = 0; i<= arraymid; i++) {
            int temp=array[i];
            array[i]=array[arrLen-i];
            array[arrLen-i]=temp;
        }
        return array;


    }
}
