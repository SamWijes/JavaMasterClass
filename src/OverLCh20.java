public class OverLCh20 {

    public static void main(String[] args) {
        System.out.println(convertToCentimeters(0,6));
    }

    public static double convertToCentimeters(int heightIn){
        return heightIn * 2.54d;
    }

    public static double convertToCentimeters(int heightIn,int heightFt){
        double heightCM =convertToCentimeters(heightFt*12) +convertToCentimeters(heightIn);
        return heightCM;
    }
}
