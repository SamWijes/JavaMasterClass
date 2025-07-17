package RestaurantChall17;

public class Main {

    public static void main(String[] args) {
//        Burger burger=new Burger("Classical Burger");
//        Sides sides=new Sides("Chips,Mashed Potatoes");
//        Toppings toppings=new Toppings("Ketchup,Mustard");

        Meal meal=new Meal("Cheese Burger","Chips,Mashed Potatoes","Ketchup,Mustard",
                "Pepsi","Small");
        meal.calculatePrice();

    }



}
