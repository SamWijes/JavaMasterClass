package dev.LinkLstChallen;


import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


record PlaceVisit(String place,int distance ){

}

class Direction{
    private boolean dir=true;

    public boolean getDir(){
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }
}

public class SecMethod {

    public static void main(String[] args) {
       // PlaceVisit place=new PlaceVisit("Adelaide",1374);
        LinkedList<PlaceVisit> linkPlace=new LinkedList<>();
        listPlace(linkPlace,"Adelaide",1374);
        listPlace(linkPlace,"adelaide",1374);
        listPlace(linkPlace,"Alice Springs",2771);
        listPlace(linkPlace,"Alice SpRings",2771);
        listPlace(linkPlace,"Brisbane",917);
        listPlace(linkPlace,"Darwin",3972);
        listPlace(linkPlace,"Melbourne",877);
        listPlace(linkPlace,"Perth",3923);

        Scanner sc =new Scanner(System.in);
        var itrListPlace =linkPlace.listIterator();
        Direction direction=new Direction();

        printMenu();

        while(true){

            String input= sc.nextLine().toUpperCase();

            switch (input){
                case "F": {
                    listTraverse(itrListPlace,"F",direction);
                    break;
                }
                case "B":{
                    listTraverse(itrListPlace,"B",direction);
                    break;
                }
                case "L" : {
                    for (PlaceVisit town: linkPlace){
                        System.out.println(town.place()+ "<---> " +town.distance());
                    }
                    break;
                }
                case "M" :
                    printMenu();
                    break;
                case "Q" : {
                    return;
                }
                default :
                    System.out.println("Enter Valid Input");
                    break;
            }
        }
    }
    public static void listPlace(LinkedList<PlaceVisit> linkPlace,String place,int distance){
        PlaceVisit town=new PlaceVisit(place,distance);
        for(PlaceVisit town2:linkPlace){
            if (town2.place().equalsIgnoreCase(place)){
                System.out.printf("-".repeat(20)+"%n%s Already Existing%n"+"-".repeat(20),town2.place());
                return;
            }
        }

        int indexCounter = 0;
        for (PlaceVisit newtown : linkPlace) {
            //System.out.println(linkPlace +" "+indexCounter);
            if (newtown.distance() > town.distance()) {
              break;//to stop incrementing counter
            }
            indexCounter++;//this acts like the cursor inside linklist.Add
        }
        linkPlace.add(indexCounter, town);

    }
    public static void listTraverse(ListIterator<PlaceVisit> itLinkPlace,String dir,Direction direction ){

        if(dir.equals("F") ){
            if (itLinkPlace.hasNext()) {
                if(!direction.getDir()){itLinkPlace.next();}
                System.out.println(itLinkPlace.next().place());
                direction.setDir(true);
            }else System.out.println("At Last Element");

        }
        else if(dir.equals("B") ) {

            if(itLinkPlace.hasPrevious()) {
                if(direction.getDir()){itLinkPlace.previous();}
                System.out.println(itLinkPlace.previous().place());
                direction.setDir(false);
            }else System.out.println("At First Element");

        }

    }

    public static void printMenu(){
        String textBox= """
                    
                Available action
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit""";
        System.out.println(textBox);
    }


}
