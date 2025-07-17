public class MegabyteConvertion {

    public  static  void  printMegaBytesAndKiloBytes(int kiloBytes){
        int megaBytes=kiloBytes / 1024;
        int remKiloBytes=kiloBytes % 1024;
        //"2500 KB = 2 MB and 452 KB"
        if (kiloBytes<0){
            System.out.println("Invalid Value");
        }
        else
            System.out.println(kiloBytes+" KB = "+megaBytes+" MB and " +remKiloBytes+" KB ");
    }

    public static void main(String[] args) {
        printMegaBytesAndKiloBytes(-2500);
    }
}
