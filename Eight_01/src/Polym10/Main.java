package Polym10;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);

        while (true){
            System.out.println("enter q quit and  A C S etc u ...");
            String type= s.nextLine();
            if("Qq".contains(type)){
                break;
            }
            System.out.println("Enter Movie Name");
            String title=s.nextLine();
            Movie movie=Movie.getMovie(type,title);

            movie.watchMovie();


         }






    }



}
