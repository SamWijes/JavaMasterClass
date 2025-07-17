public class BarkingDog {
    public static void main(String[] args) {
        System.out.println(shouldWakeUp(true,1));
        System.out.println(shouldWakeUp(false,2));
        System.out.println(shouldWakeUp(true,-1));
        System.out.println(shouldWakeUp(true,1));
    }

    public static boolean shouldWakeUp(boolean barking,int hourOfDay){
        if (hourOfDay>23 || hourOfDay <0){
            return  false;
        }
        else if(barking && (hourOfDay>23 || hourOfDay<8)){
            return true;
        }
        else return false;
    }
}
