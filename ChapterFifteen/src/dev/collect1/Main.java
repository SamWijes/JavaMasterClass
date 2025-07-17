package dev.collect1;


import java.io.PrintStream;

import java.nio.charset.StandardCharsets;
import java.util.*;

enum Suit{
    CLUB,DIAMOND,HEART,SPADE;
    public char getImage(){
        return (new char[]{9827,9830,9829,9824})[this.ordinal()];
    }

    @Override
    public String toString() {
        return this.getImage()+"";
    }
}

record Card(Suit suit, String face, int rank){
    @Override
    public String toString() {
        return "%s%s(%d)".formatted(face,suit,rank);
    }
}
public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));


        List<Card> mydeck=getStandardDeck();
        printDeck(mydeck);
        Card[] cardArray=new Card[13];
        Card acehearts=getFaceCard(Suit.HEART,'A');
        Arrays.fill(cardArray,acehearts);
        printDeck(Arrays.asList(cardArray),"Aces",1);

    }

    public static Card getNumericCard(Suit suit, int number){

        return new Card(suit,String.valueOf(number),number-2);
    }

    public static Card getFaceCard(Suit suit, char abbrev){
        int rank=switch (abbrev){
            case 'A'->12;
            case 'K'->11;
            case 'Q'->10;
            case 'J'->9;
            default ->0;
        };
        if (rank==0){
            System.out.println("invalid Card");
            return null;
        }

        return new Card(suit,String.valueOf(abbrev),rank);
    }

    public static List<Card> getStandardDeck(){

        List<Card> deck= new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (int i = 2; i <11 ; i++) {
                deck.add(getNumericCard(suit,i));
            }
            for (var face:new char[]{'J','Q','K','A'}) {
                deck.add(getFaceCard(suit,face));
            }

        }
        return deck;

    }
    public static void printDeck(List<Card> deck){
        printDeck(deck,"Current Deck",4);
    }


    public static void printDeck(List<Card> deck, String description, int rows){

        if ((!description.isEmpty())) {
            System.out.println(description);
        }
        int cardPerRow=deck.size()/rows;

        for (int i = 0; i < rows; i++) {
            int startingIndex=i*cardPerRow;
            int endingIndex=startingIndex+cardPerRow;
            deck.subList(startingIndex,endingIndex).forEach(s-> System.out.print(s+" "));
            System.out.println();
        }

    }


//
//
//    public static void printDeck(List<Card> deck,String description,int rows){
//        int initialRow=1;
//        if ((!description.isEmpty())) {
//            System.out.println(description);
//        }
//        int tempCount=1;
//        for(var card:deck) {
//            System.out.print(card+" ");
//            tempCount++;
//            if (tempCount==14){
//                tempCount=1;
//                initialRow++;
//                System.out.println();
//            }
//            if (initialRow>rows){
//                break;
//            }
//        }
////        for(var card:deck){
//            System.out.print(card+" ");
//            if (deck.indexOf(card) % 12==0 && deck.indexOf(card)!=0) {
//                initialRow+=1;
//                System.out.println();
//            }
//            if (initialRow>rows){
//                break;
//            }

     //   }

}
