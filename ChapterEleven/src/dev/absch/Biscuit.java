package dev.absch;

public class Biscuit extends ProductForSale {

    public Biscuit(String type, double price) {
        super(type, price);
        this.description="Biscuit Box";
    }

    @Override
    public void showDetails() {

        System.out.printf("Biscuit box No 25458%n");
    }

}
