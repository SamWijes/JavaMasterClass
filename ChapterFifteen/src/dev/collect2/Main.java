package dev.collect2;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        List<Card> deck=Card.getStandardDeck();
        Card.printDeck(deck);

        Card[] cardArray=new Card[13];
        System.out.println(Arrays.toString(cardArray));
        Card aceHearts=Card.getFaceCard(Card.Suit.HEART,'A');
        Arrays.fill(cardArray,aceHearts);
        //Card.printDeck(Arrays.asList(cardArray),"Ace Of Hearts",1);
        List<Card> cards=new ArrayList<>(52);

        List<Card> acesOfHearts=Collections.nCopies(13,aceHearts);
        Card.printDeck(acesOfHearts,"Hearts",1);

        Card kingsOfClubs=Card.getFaceCard(Card.Suit.CLUB,'K');
        List<Card> kingsOfClub=Collections.nCopies(13,kingsOfClubs);
        Card.printDeck(kingsOfClub,"KClubs",1);

//        Collections.addAll(cards,cardArray );
//        Collections.copy(cards,kingsOfClub);

//        cards=List.copyOf(kingsOfClub);
//        Card.printDeck(cards,"",1);
//
//        Collections.copy(cards,kingsOfClub);
//        Card.printDeck(cards,"",1);

        cards=Card.getStandardDeck();
        Card.printDeck(cards);

//        Collections.shuffle(cards);
//        Card.printDeck(cards);
//
//        Collections.reverse(cards);
//        Card.printDeck(cards);

//        Comparator<Card> sortingAlgorithm = Comparator.comparing((card) -> card.rank())
//                .thenComparing((card) -> card.suit());

        Comparator<Card> sortingAlgorithm = Comparator.comparing((Card card) -> card.rank());

        var sortingAlgorithm2= Comparator.comparing(Card::rank)
                .thenComparing(Card::suit);


        Collections.sort(cards,sortingAlgorithm2);
        Card.printDeck(cards,"sorted deck",13);

        Collections.reverse(cards);
        Card.printDeck(cards,"Rev deck",13);

        List<Card> kings=cards.subList(4,8);
        Card.printDeck(kings,"Kings",1);

        List<Card> tens=cards.subList(16,20);
        Card.printDeck(tens,"Tens",1);

        int indextens=Collections.indexOfSubList(cards,tens);
        System.out.println("indexoftens= "+indextens);

        System.out.println("Contains "+ cards.containsAll(tens));
        //checks for all element avaialability in a list regardless of order

        boolean disjoint1= Collections.disjoint(cards,tens);
        //checks whether there are no elements in commom
        //if at least 1 element->false
        boolean disjoint2= Collections.disjoint(tens,kings);
        //no elements in common ->true
        System.out.println(disjoint1+ " " + disjoint2);

//        cards.sort(sortingAlgorithm2);
        Card tenofHearts=Card.getNumericCard(Card.Suit.HEART,10);
        Card tenofDiamond=Card.getNumericCard(Card.Suit.DIAMOND,10);
//        int foundIndex=Collections.binarySearch(cards,tenofHearts,sortingAlgorithm);
//        System.out.println("Index is "+foundIndex);
//        System.out.println("unsorted Index "+cards.indexOf(tenofHearts));
//        System.out.println(cards.get(foundIndex));


        Card.printDeck(cards,"cards",13);

        System.out.println("Col.replaceall return val :"+ Collections.replaceAll(cards,tenofHearts,tenofDiamond));
        Card.printDeck(cards.subList(16,20),"Replaced 10Hto10D",1);

        System.out.println("Frquency of 10D cards-:"+ Collections.frequency(cards,tenofDiamond));

        System.out.println("Best Card "+Collections.max(cards,sortingAlgorithm2));
        System.out.println("Worst Card "+Collections.min(cards,sortingAlgorithm2));

        var sortBySuit=Comparator.comparing(Card::suit).thenComparing(Card::rank);

        cards.sort(sortBySuit);

        Card.printDeck(cards,"by suit,By Rank",4);

        var copied=new ArrayList<>(cards.subList(0,13));
        List<Card> cop2=new ArrayList<>(copied);

        Card.printDeck(cop2,"",1);
        Collections.rotate(copied,-6);
        Card.printDeck(copied,"rotated",1);

        copied=new ArrayList<>(cards.subList(0,13));
//        Collections.swap(copied,0,12);
        Card.printDeck(copied,"",1);
        for (int i = 0; i < copied.size()/2; i++) {
            Collections.swap(copied,i,copied.size()-(i+1));
        }
        Card.printDeck(copied,"Swapped",1);

        Collections.reverse(copied);

        Card.printDeck(copied,"Revered",1);


    }
}
