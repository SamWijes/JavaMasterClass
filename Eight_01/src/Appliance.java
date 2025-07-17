public class Appliance {
    boolean hasWorkToDo;

    public void setHasWorkToDo(boolean hasWorkToDo){
        this.hasWorkToDo=hasWorkToDo;

    }
}

class Refrigerator extends Appliance{

    public void orderFood () {
       hasWorkToDo=true;
        System.out.println("order food");

    }
    public void doKitchenWork() {
        if (!hasWorkToDo) {
            System.out.println(getClass().getName() + " is starting");
            hasWorkToDo = true;

        } else System.out.println("Appliance currently utilized");
    }
    public boolean getHasWorkToDo(){
        return hasWorkToDo;
    }

}

class DishWasher extends Appliance{

    public void doDishes () {

    }
    public void doKitchenWork() {
        if (!hasWorkToDo) {
            System.out.println(getClass().getName() + " is starting");
            hasWorkToDo = true;

        } else System.out.println("Appliance currently utilized");
    }

}
class CoffeeMaker extends Appliance{

    public void brewCoffee () {

    }
    public void doKitchenWork() {
        if (!hasWorkToDo) {
            System.out.println(getClass().getName() + " is starting");
            hasWorkToDo = true;

        } else System.out.println("Appliance currently utilized");
    }
}