public class SpeedConverterNew {
    public static void main(String[] args) {
        System.out.println(toMilesPerHour(1.5));
        System.out.println(toMilesPerHour(10.25));
        System.out.println(toMilesPerHour(-5.6));
        System.out.println(toMilesPerHour(75.114));
        printConversion(10.25);
        printConversion(75.114);
    }

    public  static long toMilesPerHour(double kilometersPerHour){
        double mph=(kilometersPerHour * 5 / 8);
        long mphRound=Math.round(mph);

        if (kilometersPerHour<0) {
            return -1;
        } else return mphRound;

    }

    public static void printConversion(double kilometersPerHour){
        System.out.println(kilometersPerHour+" Km/h = "+toMilesPerHour(kilometersPerHour)+" Mi/h");
    }
}
