package dev.CardGameCh;

import java.util.*;

class Player {
    int id;
    List<Card> hand;
    int chips;
    boolean hasFolded;

    public Player(int id, List<Card> hand, int chips) {
        this.id = id;
        this.hand = hand;
        this.chips = chips;
        this.hasFolded=false;
    }
}

public class GameMaster {

    public List<Player> players;

    public Player dealer;
    private int bet;
    private int chipsInPot=0;
    private int ante;
    private int playerCount;
    private static Random random=new Random();

    public int getChipsInPot() {
        return chipsInPot;
    }

    public void setDealer(int player){
        dealer= players.get(player);
    }
    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public GameMaster(int playerCount, int ante) {
        this.ante=ante;
        this.playerCount=playerCount;
        this.players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            players.add(i,new Player(i,new ArrayList<>(5),10));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
    public void drawAnte(){
        for (Player player : players) {
            player.chips -= ante;
            chipsInPot += 1;
        }
    }

    public void shuffleDeck(List<Card> deck) {
        Collections.shuffle(deck);

    }
    public void cutDeck(List<Card> deck) {
        Collections.rotate(deck,random.nextInt(0,deck.size()));

    }

    public void dealCards(List<Card> deck){
        for(int i=0;i<6;i++) {
            for (var player:players) {
                player.hand.add(deck.get(0));
                deck.remove(0);

            }
        }

    }

    public void call(Player player,int raise){

        player.chips-=raise;
        chipsInPot+=raise;
    }

    public void check(){

    }

    public void fold(Player player){
        player.hasFolded=true;
    }
    public void showChips(){
        for(var player:getPlayers()){
            System.out.printf("PlayerID %d : %s %n",player.id,player.chips);
        }
    }
    public void discard(Player player,List<Card> deck, int[] card){
        for (var index:card) {
            player.hand.remove(index);
            player.hand.add(deck.get(0));
            deck.remove(0);
        }


    }
    public void showHand(){
        for(var player:getPlayers()){
            System.out.printf("PlayerID %d : %s %n",player.id,player.hand);
        }
    }

    public void showHand(int id){
        System.out.println(players.get(id).hand);
    }


//
//        High card
//        One pair
//        Two pair
//        Three of a kind
//        Straight Flush
//        Full house
//        Four of a Kind
//        Straight Flush
//        Royal Flush
//        Five of a kind

}
