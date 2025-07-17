package RestaurantChall17;

public class Meal {
    private Burger burger;

    private Sides sides;
    private Toppings toppings;
    private Drink drink;


    public Meal(String burger,String sides,String toppings,String drink,String drinkSize) {
        this.burger = new Burger(burger);
        this.sides = new Sides(sides);
        this.toppings = new Toppings(toppings);
        this.drink = new Drink(drink,drinkSize);
    }

    public Meal() {

    }


    public void calculatePrice(){
        double price=burger.burgerPrice()+ sides.sidesPrice()+toppings.toppingsPrice(burger)+drink.drinkPrice();
        System.out.printf("Price of the meal is %n" +
                        "Burgers : %.2f$%n" +
                        "Sides :%.2f$%n" +
                        "Toppings :%.2f$%n" +
                        "Drink :%.2f$%n",burger.burgerPrice(),
                sides.sidesPrice(),toppings.toppingsPrice(burger),drink.drinkPrice());
        System.out.println("Total Bill: "+price+"$");
    }
    @Override
    public String toString() {
        return "Meal->" +
                "burgerType=" + burger+
                ", sides=" + toppings +
                ", toppings=" + toppings ;
    }
}

class Burger extends Meal {
    protected String burgerType;
    private double burgerPrice;

    public String getBurgerType() {
        return burgerType;
    }

    public Burger(String burgerType){
        this.burgerType=burgerType;

    }

    public double burgerPrice() {
        return switch (burgerType){
            case "Classical Burger"->burgerPrice=10;
            case "Cheese Burger"->burgerPrice=15;
            case "Vege Burger"->burgerPrice=8;
            case "Deluxe Burger"->burgerPrice=20;
            default -> burgerPrice=0;
        };


    }
}

class Sides extends Meal {
    private String sides;
    private double sidesPrice;

    public Sides(String sides) {
        //super();
        this.sides = sides;
    }


    public double sidesPrice() {
        double price = 0;
        if (sides.contains("Chips")) {
            price+=5;

        }
        if (sides.contains("Mashed Potatoes")) {
            price+= 15.0;

        }
        if (sides.contains("Cornbread")) {
            price+= 8.0;

        }
        return price;

    }
}
class Toppings extends Meal{
    private String toppings;
    private double toppingsPrice;
    public Toppings( String toppings) {

        this.toppings=toppings;
    }

    public double toppingsPrice(Burger burger) {
        double price = 0;
        if (burger.burgerType.equals("Deluxe Burger")){return price;}
        if (toppings.contains("Ketchup")) {
            price+=5;

        }
        if (toppings.contains("Mustard")) {
            price+= 15.0;

        }
        if (toppings.contains("Mayo")) {
            price+= 8.0;

        }
        return price;

    }



}

class Drink extends Meal {
    protected String drinkType;
    protected String drinkSize;
    private double drinkPrice;

    public String getDrinkType() {
        return drinkType;
    }

    public Drink(String drinkType,String drinkSize){
        this.drinkType=drinkType;
        this.drinkSize=drinkSize;

    }

    public double drinkPrice() {
        if (drinkSize=="Large") {
            return switch (drinkType) {
                case "Soda" -> drinkPrice = 10;
                case "Pepsi" -> drinkPrice = 15;
                case "Coke" -> drinkPrice = 8;
                default -> drinkPrice = 0;
            };
        }
        else return switch (drinkType) {
            case "Soda" -> drinkPrice = 7;
            case "Pepsi" -> drinkPrice = 10;
            case "Coke" -> drinkPrice = 5;
            default -> drinkPrice = 0;
        };
    }
}