package dev.treesetCh;

public class Main {
    public static void main(String[] args) {
        Theatre savoy=new Theatre("Savoy",7,8);


        savoy.reserveSeat("D3");
        savoy.reserveSeat("C3");
        savoy.reserveSeat("H3");
        savoy.printSeatMap();

        String fruit = "Apple";
        int count = 5;
        double price = 1.25;

        // positional formatting
        System.out.printf("I bought %2$d %1$s(s) for $%3$.2f each.%n", fruit, count, price);
    }

}
