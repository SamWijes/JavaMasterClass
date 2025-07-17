package dev.absch;


import java.util.ArrayList;


class OrderList extends ProductForSale {
    private ArrayList<OrderItem> products;

    public OrderList() {
        this.products = new ArrayList<>();
    }

    public void add(ProductForSale product,int quantity){
        OrderItem order=new OrderItem(product,quantity);
        this.products.add(order);
    }
    public void printBill(){
        System.out.printf("%15s %5s%n","Item","Price");
        System.out.println("__".repeat(10));
        double total=0;
        for(OrderItem el:products){
            System.out.printf("%15s %5.2f$%n",el.productFSale.getClass().getSimpleName(),el.productFSale.price);
            total+=el.productFSale.price;
        }
        System.out.println("__".repeat(10));
        System.out.printf("%15s %5.2f$%n","Total",total);
    }

    @Override
    public void showDetails(){
        System.out.println("Order Item Details");
        System.out.println("__".repeat(10));
        for (OrderItem el:products) {
            el.showDetails();
        }
    }

    @Override
    public String toString(){
        return products.toString();
    }

}

public class Store {

    public static void main(String[] args) {
        Apple apple=new Apple("fruit",1.25);
        Biscuit cracker=new Biscuit("Biscuit",2);
        Orange orange=new Orange("Fruit",2.5);
//
//        apple.showDetails();
//        orange.showDetails();
//        cracker.showDetails();
//
//        OrderList order=new OrderList();
//        order.add(apple,4);
//        order.add(cracker,4);
//        order.add(orange,4);
//
//        order.printBill();
//
//
//        order.showDetails();












    }
}

