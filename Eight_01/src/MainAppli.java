public class MainAppli {

    public static void main(String[] args) {
        SmartKitchen sk= new SmartKitchen();

        sk.getIceBox().doKitchenWork();
        sk.getIceBox().setHasWorkToDo(false);
        sk.getIceBox().doKitchenWork();

    }
}
