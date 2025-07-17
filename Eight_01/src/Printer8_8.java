public class Printer8_8 {
    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;


    public Printer8_8(boolean duplex){
        this(100,duplex);
    }
    public Printer8_8(int tonerLevel,boolean duplex){
        this.tonerLevel=(tonerLevel>=0 && tonerLevel<=100) ? tonerLevel:-1 ;

        if (this.tonerLevel==-1) {
            System.out.println("Enter a Valid Toner Level");
            return;
        }
        //System.out.println(tonerLevel);

        this.duplex=duplex;

    }

    public int getTonerLevel() {
        if(tonerLevel==-1){
            System.out.println("Invalid Toner Level");
            return -1;
        }
        System.out.println(tonerLevel);
        return tonerLevel;
    }

    public int addToner (int tonerAmount){
        int tempToner=(tonerAmount>=0 && tonerAmount+tonerLevel<=100) ? tonerAmount+tonerLevel:-1;
        tonerLevel=tempToner;
        return tonerLevel;
    }


    public int printPages(int pages){

        pagesPrinted=(duplex)? pages/2 +pages%2:pages;

        return pagesPrinted;



    }
}

