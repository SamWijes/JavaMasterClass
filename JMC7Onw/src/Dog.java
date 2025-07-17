public class Dog extends Animal{

    private String earShape;
    private String tailShape;
    public Dog(){
        super("Dog","Small",50);
    }

    public Dog(String type, double weight, String earShape, String tailShape) {
        super(type, weight <15 ? "Small":(weight<35 ? "Medium":"large"), weight);
        this.earShape = earShape;
        this.tailShape = tailShape;
    }

    public Dog(String type, double weight) {
        this(type,weight,"Perky","Curled");

    }

    @Override
    public String toString() {
        return "Dog{" +
                "earShape='" + earShape + '\'' +
                ", tailShape='" + tailShape + '\'' +
                "} " + super.toString();
    }


    @Override
    public void makeNoise() {
        if (type=="Wolf"){
            System.out.print("ow woooo");
        }
        else
            bark();
        System.out.println();

    }

    @Override
    public void move(String speed) {
        super.move(speed);
        //System.out.println("dogs walk run wag tail");
        if(speed=="slow"){
            walk();
            wagTail();

        } else  {
            run();
            bark();
        }
        System.out.println();

    }

    private void bark(){

        System.out.print("Woof");

    }
    private void run(){
        System.out.print("Dog Running ");

    }
    private void walk(){
        System.out.print("Dog Walking ");

    }
    private void wagTail(){
        System.out.print("Dog is wagging tail ");

    }
}
