public class Car {

    private String make="Tesla";
    private String model="X";
    private String colour="Grey";
    private int doors=2;
    private boolean convertible=true;


    public void describeCar(){

        System.out.println(doors+"-Door "+
                colour+" "+
                make +" "+
                model+" "+
                (convertible?"convertible":" "));
    }
}
