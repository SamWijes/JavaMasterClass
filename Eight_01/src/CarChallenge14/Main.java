package CarChallenge14;

public class Main {

    public static void main(String[] args) {

        Car gas=Car.getCar("Gas");
        Car electric=Car.getCar("Electric");
        Car Hybrid=Car.getCar("Hybrid");
        Car car=Car.getCar("");

//        ((GasPoweredCar) gas).viewSpecs();
//        ((HybridCar) Hybrid).viewSpecs();
//        ((ElectricCar) electric).viewSpecs();

        ((GasPoweredCar) gas).startEngine();

        //((GasPoweredCar) gas).runEngine();
        ((GasPoweredCar) gas).drive();
    }
}
