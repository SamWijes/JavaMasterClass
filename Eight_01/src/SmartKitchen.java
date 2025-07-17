public class SmartKitchen extends Appliance{

    private Refrigerator iceBox;
    private CoffeeMaker brewMaster;
    private DishWasher dishWasher;

    public SmartKitchen(){
        iceBox=new Refrigerator();
        brewMaster=new CoffeeMaker();
        dishWasher=new DishWasher();
    }

    public Refrigerator getIceBox() {
        return iceBox;
    }

    public CoffeeMaker getBrewMaster() {
        return brewMaster;
    }

    public DishWasher getDishWasher() {
        return dishWasher;
    }

    public void addWater(){
        if (brewMaster.hasWorkToDo){
            System.out.println("Currently Making a Cofee");

        }else brewMaster.hasWorkToDo=true;
    }

    public void pourMilk(){
        if (iceBox.hasWorkToDo){
            System.out.println("Refrigerator Occupied");

        }else iceBox.hasWorkToDo=true;

    }

    public void loadDishWasher(){
        if (dishWasher.hasWorkToDo){
            System.out.println("Dishwasher Occupied");

        }else dishWasher.hasWorkToDo=true;

    }


}
