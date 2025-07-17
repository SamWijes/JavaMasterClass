public class LeapYear {
    public static void main(String[] args) {
        System.out.println(isLeapYear(2000));
        System.out.println(isLeapYear(1900));
        System.out.println(isLeapYear(1600));
    }

    public static boolean isLeapYear (int year){
        if(year >0 && year <9999 && year % 4==0){
            if (year % 100==0){
                return year % 400 == 0;
            }
        }
        return false;
    }
}
