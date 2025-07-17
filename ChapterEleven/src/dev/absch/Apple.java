package dev.absch;

public class Apple extends ProductForSale{
    public Apple(String type, double price) {
        super(type, price);
        this.description="Apple";
    }

    @Override
    public void showDetails() {

        System.out.printf("apple impoeted from NWx WH  %n");
    }


}
