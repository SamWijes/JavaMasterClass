public class FirstClass {
    public static void main(String[] args) {
        /*
        System.out.println("sda");
        boolean isCar=false ;
        if (!isCar){
           System.out.println("This car is ok");
        }

        String makeOfCar="HONDA";
        boolean isDomestic=makeOfCar=="HONDA" ? false :true;

        if (isDomestic){
            System.out.println("Not a Honda");
        }else
            System.out.println("Hondaaa");
        */
        double myVal1=20.00;
        double myVal2=80.00;
        double newVal=(myVal1+myVal2) * 100;
        System.out.println(newVal);
        double rem=newVal % 40;
        boolean isZero=(rem==0)? true:false;
        System.out.println("boolean variable isZero is " + isZero);
        if (!isZero) {
            System.out.println("got some remainder");
        }

    }

}
