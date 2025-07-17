package dev.intfChallenge;

public class Main {
    public static void main(String[] args) {
        UtilityLine fiber=new UtilityLine("GREEN DOT","BOB Fiber","FIBER_OPTIC");
        Building hospital=new Building("RED CROSS","Asiri Hospital","PRIVATE");


        fiber.toJSON();
        Mappable.detail(fiber);
        Mappable.detail(hospital);
    }

}
