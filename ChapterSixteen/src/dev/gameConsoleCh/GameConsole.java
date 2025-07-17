package dev.gameConsoleCh;

import java.util.*;
import java.util.function.Predicate;

record GameAction(char key, String prompt, Predicate<Integer> action){}

public class GameConsole {
    private static Scanner scanner=new Scanner(System.in);
    private List<String> players;

    public GameConsole() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(){
        System.out.println("Enter Player name: ");
        String name=scanner.nextLine();
        players.add(name);

    }

    public void playGame(){
        System.out.println("Enter Game Option: ");
        String opt=scanner.nextLine();
//        while (true){
//            sd
//        }
    }

}
