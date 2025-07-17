package dev.mathRandomProject;

public class Main {
    public static void main(String[] args) {

//        int maxMinusFive = Integer.MAX_VALUE - 5;
//        for (int j = 0, id = maxMinusFive;j<10;id=Math.incrementExact(id),j++){
//            System.out.printf("Assigning id %,d%n",id);
//
//        }

        System.out.println(Math.abs(-50));
//        System.out.println(Math.absExact(Integer.MIN_VALUE));
        System.out.println(Math.abs((long) Integer.MIN_VALUE));

        System.out.println("Round Down = " + Math.round(10.2));
        System.out.println("Round Up = " + Math.round(10.8));
        System.out.println("Round ? = " + Math.round(10.5));
        for (int i = 0; i < 10; i++) {
            System.out.println((int) (Math.random() * 26)+65);
        }
    }
}
