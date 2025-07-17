package dev.CardGameCh;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
//        var deck=Card.getStandardDeck();
//        GameMaster game=new GameMaster(3,1);
//        game.shuffleDeck(deck);
//
//        game.cutDeck(deck);
//        Card.printDeck(deck);
//        game.dealCards(deck);
//        game.showHand();
//        game.showChips();
//        System.out.println(game.getChipsInPot());

        startGame(4,1);

    }
    public static Scanner sc=new Scanner(System.in);
    public static void  startGame(int playerCount,int ante){
        boolean win=false;
        GameMaster newGame=new GameMaster(playerCount, ante);
        var deck=Card.getStandardDeck();
        newGame.shuffleDeck(deck);
        newGame.cutDeck(deck);
        newGame.drawAnte();
        System.out.println("Who will Deal?");
        newGame.setDealer(sc.nextInt());
        newGame.dealCards(deck);
        newGame.showHand();
        newGame.showChips();
        boolean isRoundOne=true;
        while(!win){
            if(isRoundOne){
                System.out.println("Call Bet in the round");
                newGame.setBet(sc.nextInt());
            }
            for(var player:newGame.players) {
                if(player.hasFolded || player.equals(newGame.dealer)&& isRoundOne ){continue;}
                System.out.printf("Player %d : choice 1.Call 2.Check 3.Fold %n", player.id);
                System.out.println("Hand: "+player.hand+ " Mychips :"+player.chips+" | PotChips: "+newGame.getChipsInPot());
                int choice;
                boolean input=false;
                while (!input) {
                    choice= sc.nextInt();
                    switch (choice) {
                        case 1 -> {
                            System.out.println("Raise Amount: ");
                            int raise=sc.nextInt();
                            if (raise<newGame.getBet()){
                                System.out.println("Raise Amount must be grater than bet ");
                                break;

                            }
                            newGame.call(player, raise);
                            input=true;
                        }
                        case 2 -> {
                            newGame.check();
                            input=true;
                        }
                        case 3 ->{
                            newGame.fold(player);
                            input=true;
                        }
                        default ->System.out.println("Invalid Input");

                    }
                }
            }
            isRoundOne=false;

            for(var player:newGame.players) {
                System.out.printf("To Discard cards for Player %d or enter \"n\" to keep current cards %n",player.id);
                String input=sc.next();
                if (input.equals("n")) {
                    continue;
                }
                String[] cards=input.split(",");
//                System.out.println(Arrays.toString(cards));
                int[] cardsInt=new int[cards.length];
                for (int i=0;i<cardsInt.length;i++) {
                    cardsInt[i]=Integer.parseInt(cards[i]);

                }
                newGame.discard(player,deck,cardsInt);
                System.out.println(player.hand);


            }
        }



    }


}


