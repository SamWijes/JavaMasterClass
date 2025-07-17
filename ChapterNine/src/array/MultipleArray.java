package array;

import java.util.Arrays;

public class MultipleArray {
    public static void main(String[] args) {

        int[][] array=new int[4][4];
        System.out.println(Arrays.toString(array));

        for(int[] el:array){
            System.out.println(Arrays.toString(el));

        }


        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j]=10*i+j+1;
            }
        }

        for (int[] out :array) {
            for (int el: out) {
                System.out.print(el+" ");
            }
            System.out.println();
        }

        System.out.println(Arrays.deepToString(array));


    }
}
