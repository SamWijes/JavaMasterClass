public class Product {

    private String Model;
    private String Manufacturer;
    private int width;
    private int height;
    private int depth;


    public Product(String Model , String Manufacturer){

        this.Model=Model;
        this.Manufacturer=Manufacturer;

    }

}
class Monitor extends Product{

    public Monitor(String Model , String Manufacturer){
        super(Model,Manufacturer);
    }

}


class MotherBoard extends Product{

    public MotherBoard(String Model , String Manufacturer){
        super(Model,Manufacturer);
    }

}

class ComputerCase extends Product{

    public ComputerCase(String Model , String Manufacturer){
        super(Model,Manufacturer);
    }

}