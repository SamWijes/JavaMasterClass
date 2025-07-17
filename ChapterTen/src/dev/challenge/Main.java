package dev.challenge;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        String textBox= """
                Available Actions
                0 - to shutdown
                1 - to add item(s) to list
                2 - to remove items
                Enter number for action""";

        ArrayList<String> item=new ArrayList<>();
        while ( true) {

            System.out.println(textBox);
            String choice = s.nextLine();

            if (choice.equals("0")){
                break;
            } else if (choice.equals("1")) {

                String addlist=s.nextLine();
                System.out.println(addlist);
                    item.addAll(Collections.singleton("a"));
                System.out.println(item);



            }else if (choice.equals("2") ) {
                String removeList=s.nextLine();
                item.removeAll(Arrays.asList(removeList.split(",")));
                System.out.println(item);
            }



        }
    }
}

