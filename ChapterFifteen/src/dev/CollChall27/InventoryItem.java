package dev.CollChall27;

public class InventoryItem {
    private Product product;
    private int qtyTotal;
    private int qtyReserved;
    private int qtyReorder;
    private int qtyLow;
    private double salesPrice;

    public Product getProduct() {
        return product;
    }

    //all item initialized to a total 500 low of 100

    @Override
    public String toString() {
        return "%12s %4d %.2f".formatted(product.manufacturer(), qtyTotal, salesPrice);
    }

    public InventoryItem(Product product, double salesPrice) {
        this.product = product;
        this.salesPrice = salesPrice;
        this.qtyTotal = 500;
        this.qtyReorder = 400;
        this.qtyLow = 100;
    }

    public void reserveItem(int reserve) {
        if (qtyTotal >= reserve) qtyReserved = reserve;
        else System.out.println("Total Qty Lower than Specified");
    }

    public void releaseItem(int releaseQty) {
        if (qtyReserved >= releaseQty) qtyReserved -= releaseQty;
        else System.out.println("Reserve Qty Lower than Specified");
    }

    public void sellItem(int sellQty) {
        if (qtyTotal >= sellQty) qtyTotal -= sellQty;
        else System.out.println("Total Qty Lower than Specified");

    }

    public void placeInventoryOrder() {
        if (qtyLow == qtyTotal) qtyTotal += qtyReorder;
        else System.out.println("Low Qty not Reached");
    }
}
