package dev.burger;

import java.util.ArrayList;
import java.util.List;

public class Meal {
    private double base=5.0;
    private Burger burger;
    private Item drink;
    private Item side;
    private double conversionRate;

    public Meal() {
        this(1);
    }

    public Meal(double conversionRate){
        this.conversionRate=conversionRate;
        burger=new Burger("regular",4);
        burger.addTopping("cheese","pickle","ketchup");
        System.out.println(burger.burgerPrice);
        drink=new Item("coke","Drink",2);
        System.out.println(drink.name);
        side=new Item("fries","side",4);
    }
    public double getTotal(){
        return Item.getPrice((drink.price+burger.burgerPrice+side.price),conversionRate);
    }
    @Override
    public String toString() {
        return "%5s%n%5s%n%5s%n%10sTotal Due:%10.2f ".formatted(burger,drink,side,"",getTotal());
    }
    private class Burger extends Item{
        private List<Item> toppings;
        private double burgerPrice;
        private String type;

        public Burger(String type, double price) {
            super("Burger",type, price);
            toppings=new ArrayList<>();
            this.burgerPrice=price;
            this.type=type;
        }

        public double toppingPrice(String topping){
            return switch (topping.toUpperCase()){
                case "KETCHUP"->1.5;
                case "CHEESE"->2.2;
                case "PICKLE"->1.15;
                default -> 0;
            };
        }
        public void addTopping(String... toppingName){
            for (var el:toppingName ) {
                Item topping=new Item(el,"Topping",toppingPrice(el));
                toppings.add(topping);
                burgerPrice+=topping.price;
            }
        }

        public String toppingList(){
            StringBuilder toppingList= new StringBuilder();
            for (var el:toppings ) {
                toppingList.append(" , ").append(el.name);
            }
            return "Topping List: "+toppingList;
        }
        @Override
        public String toString() {
            return  "%10s %8s %10.2f %s".
                    formatted("Burger",type,getPrice(burgerPrice,conversionRate),toppingList());
        }
    }
    private class Item {
        private String name;
        private String type;
        private double price;




        public Item(String name, String type) {
            this(name,type,(type.equals("burger"))? base:0);
        }
        public Item(String name, String type, double price) {
            this.name = name;
            this.type = type;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "%10s %8s %10.2f".formatted(type,name,getPrice(price,conversionRate));
        }

        public static double getPrice(double price ,double convRate){
            return price*convRate;
        }
    }


}
