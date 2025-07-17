package dev.CollChall27;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Map<String,InventoryItem> keelsInventory=new HashMap<>();
        loadProductsToInv("Orange","OAgri","Fruit",5.75,keelsInventory);
        loadProductsToInv("Apples","OAgri","Fruit",2.75,keelsInventory);
        loadProductsToInv("Yoghurt","RodState","Dairy",3.75,keelsInventory);
        loadProductsToInv("Spinach","ReinMetall","Vegetable",2.15,keelsInventory);
        loadProductsToInv("ChocoChip IceCream","Ehouse","Dairy",3.15,keelsInventory);

        keelsInventory.forEach((k,v)-> System.out.println(k+":"+v));
        Store keels=new Store(keelsInventory);
        keels.takeACart("Physical");
        keels.takeACart("Virtual");
        keels.takeACart("Physical");
        keels.displayCartDetail();
        keels.manageStoreCarts(1,"Orange",3);
        keels.displayCartDetail(1);
        keels.manageStoreCarts(1,"Spinach",3);
        keels.displayCartDetail(1);
        keels.manageStoreCarts(1,"Orange",-2);
        keels.displayCartDetail(1);
        keels.displayCartDetail();
        keels.listProductsByCategory();

    }

    public static void loadProductsToInv(String name,String manufacturer,String category,double price,
                                         Map<String,InventoryItem> inventory ){
        String sku=manufacturer.substring(0,1).toUpperCase()+name.substring(0,1).toUpperCase()+"001";
        Product product=new Product(sku,name,manufacturer,category);
        InventoryItem invItem=new InventoryItem(product,price);
        inventory.put(product.name(),invItem);

    }

    public static void loadAisle(){

    }
}
