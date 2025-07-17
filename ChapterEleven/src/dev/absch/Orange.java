package dev.absch;

public class Orange extends ProductForSale {

    public Orange(String type, double price) {
        super(type, price);
        this.description="Orange Big";
    }

    @Override
    public void showDetails() {

        System.out.printf("Oranges from Myanmar ctrate 45Ct%n");
    }
}

