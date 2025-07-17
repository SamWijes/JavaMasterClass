package dev.CollChall27;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//can introduce an enum for category -> can use filters
record Product(String sku,String name,String manufacturer,String category){}
public class Store {
    public class Cart{
        private static int INIT_ID=1;
        private int id;
        private Map<Product,Integer> products;
        private LocalDate date;
        private String type;

        //cart type virtual or physical
        public Cart(String type){
            this.type=type;
            id=INIT_ID++;
            products=new HashMap<>();
            date=LocalDate.now();

        }

        @Override
        public String toString() {
            return "ID-%2d Products in Cart%s Type: %s".formatted(id,products,type);
        }

        public int getID() {
            return id;
        }
    }
    private Map<String,InventoryItem> inventory;
    private Map<Integer,Cart> carts;  //navigable set can be used for carts to get helper methods to abandon old carts
    //aisle inventory method->search by category ->then select products fruits->orange,apple,etc
    public Cart getCart(int id) {
        return carts.get(id);
    }
    public Store(Map<String, InventoryItem> inventory) {
        this.inventory = inventory;
        this.carts=new HashMap<>();
    }
    public void takeACart(String type) {
        Cart newCart=new Cart(type);
        carts.put(newCart.id,newCart);
    }
    public void displayCartDetail(int id) {
        System.out.println(carts.get(id));
    }
    public void displayCartDetail() {
        carts.forEach((k,v)-> System.out.println(v));
    }
    public void manageStoreCarts(int id,String product,int qty){

        //use this area to print a custom amount of carts cart1,type cart2 type
        //adding and removeing items to cart sone here
        //inv to string->pr
        Cart currentCart=getCart(id);
        Product currProduct=this.inventory.get(product).getProduct();
        if(currentCart.products.containsKey(currProduct)) {
            int currProductQty=currentCart.products.get(currProduct);
            if (currProductQty > Math.abs(qty))
                currentCart.products.compute(currProduct, (oldQty, newQty) -> currProductQty + qty);
            else if (currProductQty == qty) currentCart.products.remove(currProduct);
            else System.out.println("product qty insufficient");
        }else
            if(qty>0) currentCart.products.put(currProduct,qty);
            else System.out.println("Invalid Qty");

    }

    public void checkOutCarts(int id) {
        Cart currentCart=carts.get(id);
        currentCart.products.forEach((product,qty)->inventory.get(product.name()).sellItem(qty));
    }
    public void abandonCarts(int id){
        carts.remove(id);
    }
    public void listProductsByCategory() {
        inventory.forEach((itemName,invItem)-> System.out.println(invItem.getProduct().category()+invItem.getProduct().name()));
    }




}



/*
*  public void manageStoreCarts(int id,String product,int qty){

        //use this area to print a custom amount of carts cart1,type cart2 type
        Cart currentCart=getCart(id);
        Product currProduct=this.inventory.get(product).getProduct();
        if(currentCart.products.containsKey(currProduct)) {
            int currProductQty=currentCart.products.get(currProduct);
            if (currProductQty > Math.abs(qty))
                currentCart.products.compute(currProduct, (oldQty, newQty) -> currProductQty + qty);
            else if (currProductQty == qty) currentCart.products.remove(currProduct);
            else System.out.println("product qty insufficient");
        }else
            if(qty>0) currentCart.products.put(currProduct,qty);
            else System.out.println("Invalid Qty");

    }*/
