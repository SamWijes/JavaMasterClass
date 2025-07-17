package dev.absch;

public abstract class ProductForSale {
    protected String type;
    protected double price;
    protected String description;

    public ProductForSale(String type,double price){
        this.type=type;
        this.price=price;
    }

    public ProductForSale() {

    }

    public double getSalesPrice(int quantity){
        return quantity*price;
    }

    public void PrintPricedItem(int quantity){
        System.out.println(quantity+"Nos of"+ getClass().getName()+"Price is"+getSalesPrice(quantity));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    public abstract void showDetails();


}

class OrderItem extends ProductForSale{
    private int quantity;
    protected ProductForSale productFSale;

    public OrderItem(ProductForSale productFSale,int quantity ) {
        this.quantity=quantity;
        this.productFSale=productFSale;
    }



    @Override
    public void showDetails() {
        System.out.printf("Type of item: %s %nItem Price %.2f: %nItem Details: %s %n",productFSale.type,productFSale.price,productFSale.description);
    }

    @Override
    public String toString() {
        return ""+productFSale+"--"+quantity+" PCS";
    }
}