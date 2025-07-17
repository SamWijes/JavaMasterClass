package dev.treesetCh;

import java.lang.reflect.Array;
import java.util.*;

public class Theatre {

    class Seat implements Comparable<Seat> {
        private String seatNum;
        private char rowLetter;
        private int columNum;
        private boolean isReversed;

        public Seat(String seatNum) {
            this.seatNum = seatNum;
        }

        public Seat(char rowLetter, int columNum, boolean isReversed) {
            this.seatNum =String.valueOf(rowLetter)+columNum;
            this.rowLetter=rowLetter;
            this.columNum=columNum;
            this.isReversed = isReversed;

        }

        @Override
        public int compareTo(Seat o) {
            return seatNum.compareTo(o.seatNum);
        }

        @Override
        public String toString() {
            return seatNum;
        }
    }



    private String theatreName;
    private int seatsInRow;
    private int numOfRows;
    private int totalSeats;

    private static NavigableSet<Seat> seats = new TreeSet<>();

    //no if rows <26 row letter A, seats->A1,A2,A3....


    public Theatre(String theatreName, int seatsInRow, int numOfRows) {
        this.theatreName = theatreName;
        this.seatsInRow = seatsInRow;
        if (numOfRows > 26) throw new IllegalArgumentException("Rows cannot exceed 26");
        else this.numOfRows = numOfRows;
        this.totalSeats = seatsInRow * numOfRows;

        int seatNum = 65;
        for (int i = 1; i < numOfRows + 1; i++) {
            char seat = (char) seatNum;
            for (int j = 1; j < seatsInRow + 1; j++) {
                seats.add(new Seat(seat,j,false));
            }
            seatNum += 1;
        }
    }

        //create seats and number in the constructor
    public void printSeatMap () {
       // int seatNum = 65;
       // System.out.println(seats);

//        for (int i = 0; i < numOfRows; i++) {
//            String seatRowFirst=String.valueOf(((char) (seatNum+i)));
//            String seatRowLast=String.valueOf(((char) (seatNum+i+1)));
//            var seatRow= seats.subSet(new String[]{seatRowLast}, new String[]{seatRowLast});
//            for(var seat:seatRow){
//                System.out.print(seat+" ");
//            }
//            System.out.println();
//        }
        //iterator Note
//        for (var seat:seats) {
//            Seat nextSeat=seats.iterator().next();
//            //System.out.print(nextSeat.seatNum +" ");
//            if (seat.rowLetter!=nextSeat.rowLetter) {
//                System.out.println("next");
//            }else System.out.print(seat.seatNum+" ");
//
//        }


        Iterator<Seat> iterator=seats.iterator();
        Seat currentSeat = iterator.next();
        while (iterator.hasNext()){
            Seat nextSeat = iterator.next();
            System.out.print(currentSeat.seatNum +((currentSeat.isReversed)?"(R)":"(Av)")+" ");
            if (currentSeat.rowLetter!=nextSeat.rowLetter){
                System.out.println();
            }
            currentSeat=nextSeat;
        }
        System.out.print(currentSeat.seatNum +((currentSeat.isReversed)?"(R)":"(Av)"));
        System.out.println();



    }
    //reverse a specific seat

    public void reserveSeat(String seatNum){
        Seat reservingSeat= seats.ceiling(new Seat(seatNum));
        if (reservingSeat != null ) {
            if (reservingSeat.isReversed) {
                System.out.println("Seat Already Reserved");
            }else reservingSeat.isReversed=true;
        }

    }

}