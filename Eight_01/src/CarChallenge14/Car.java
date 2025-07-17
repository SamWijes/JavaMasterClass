package CarChallenge14;



public class Car {


    private String description;
    private boolean idling;
    public void startEngine(){
        System.out.println("vehicle Started, idling....");
        idling=true;

    }

    public void setDescription(String description){
        this.description=description;

    }

    public String getDescription(){
        return description;
    }
    public static Car getCar(String type){
        return switch (type){
            case "Gas"->new GasPoweredCar();
            case "Hybrid"->new HybridCar();
            case "Electric"->new ElectricCar();
            default -> new Car("Car");


        };
    }

    public Car(String description){
        this.description=description;
        if (!description.equalsIgnoreCase("car")) {
            System.out.printf("This is a %s car %n", description);
        }else  System.out.printf("This is a %s  %n", description);
    }

    public void drive(){
        if (idling){
            System.out.println("Driving the Car");
        }else System.out.println("start the Vehicle First !!");



    }
    protected void runEngine(){
        if (idling){
            System.out.println("Engine Running");
        }else System.out.println("start the Vehicle First !!");

    }


}


class GasPoweredCar extends Car{
    private double avgKmPerLitre;
    private int cylinders;

    public GasPoweredCar(){
        super("Gas");
        this.cylinders=4;
        this.avgKmPerLitre=25;
    }
    public void viewSpecs(){
        System.out.printf("..".repeat(3)+"%s Car Specifications"+"..".repeat(3) +
                "%nKm per L : %f %n" +
                "No of cylinders : %d %n",getDescription(),avgKmPerLitre,cylinders);
    }

}

class ElectricCar extends Car{
    private double avgKmPerCharge;
    private int batterySize;
    public ElectricCar(){
        super("Electric");
        this.batterySize=300;
        this.avgKmPerCharge=50.5;
    }

    public void viewSpecs(){
        System.out.printf("..".repeat(3)+"%s Car Specifications"+"..".repeat(3) +
                "%nKm per Charge : %f %n" +
                "BatterySize : %d %n",getDescription(),avgKmPerCharge,batterySize);
    }
}

class HybridCar extends Car{
    private double avgKmPerLitre;
    private int batterySize;
    private int cylinders;

    public void viewSpecs(){
        System.out.printf("..".repeat(3)+"%s Car Specifications"+"..".repeat(3) +
                "%nKm per L : %f %n" +
                "No of cylinders : %d %n" +
                "BatterySize : %d %n",getDescription(),avgKmPerLitre,cylinders,batterySize);
    }
    public HybridCar(){
        super("Hybrid");
        this.batterySize=25;
        this.avgKmPerLitre=30;
        this.cylinders=4;

    }
}