package array;

import java.util.Arrays;
import java.util.Random;

public class Chal06 {
    public static void main(String[] args) {
        Random random=new Random();
        random.nextInt(51);
        int[] array=new int[5];

        for (int i = 0; i < array.length; i++) {
            array[i]=random.nextInt(51);
        }

        System.out.println(Arrays.toString(array));
        Arrays.sort(array);

        System.out.println(Arrays.toString(array));
        int[] arrayD=new int[5];
        for (int i = array.length-1; i>=0; i--) {

            arrayD[i]=array[array.length-1-i];
        }
        System.out.println(Arrays.toString(arrayD));
    }
}
