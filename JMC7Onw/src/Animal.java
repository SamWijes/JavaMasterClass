public class Animal {
    private String size;
    protected String type;
    private double weight;

    public  Animal(){

    }


    public Animal( String type,String size, double weight){
        this.type=type;
        this.size=size;
        this.weight=weight;

    }

    @Override
    public String toString() {
        return "Animal{" +
                "size='" + size + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                '}';
    }

    public void move(String speed){
        System.out.println(type+ " moves " +speed);
    }
    public void makeNoise(){
        System.out.println(type +" makes some noise");
    }
}
