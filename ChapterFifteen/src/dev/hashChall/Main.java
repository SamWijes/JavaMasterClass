package dev.hashChall;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        AdvenGameOP2 game=new AdvenGameOP2();
        game.play();
//        System.out.println(game.locList.getFirst().nextLoc().values());


    }
}
