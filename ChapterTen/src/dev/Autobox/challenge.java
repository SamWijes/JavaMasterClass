package dev.Autobox;

import java.util.ArrayList;

public class challenge {
    public static void main(String[] args) {
        Customer customer=new Customer("sam");

        customer.customerTx(20.5);
        customer.customerTx(254.5);
        customer.customerTx(103.55);
        customer.customerTx(-25.23);


        double lastTransaction= customer.customerTx(45.45);

        System.out.println(lastTransaction);
    }

}

class Customer extends Bank{
    public Customer(String name){
        super(name);
    }

    public double customerTx(Double amount){
        addTransaction(amount);
        System.out.println(getTransaction());

        return getTransaction().getLast();
    }

}

class Bank{
    protected String name;
    protected ArrayList<Double> transaction;

    public Bank(String name){
        this.name=name;
        this.transaction=new ArrayList<>();
    }

    public void addTransaction(Double amount){
        transaction.add(amount);
    }
    public ArrayList<Double> getTransaction() {
        return transaction;
    }
}