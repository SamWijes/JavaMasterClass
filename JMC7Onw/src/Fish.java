public class Fish extends Animal{
    private int gills;
    private int fins;

    public Fish(String type ,double weight ,int gills,int fins){
        super(type, "small", weight);
        this.fins=fins;
        this.gills=gills;

    }


    public void moveMuscles(){
        System.out.print("moving muscles ");
    }


    public void moveBackFin(){
        System.out.print("moving fins ");
    }

    @Override
    public void move(String speed) {
        super.move(speed);
        moveMuscles();
        if (speed=="fast"){
            moveBackFin();
            System.out.println();
        }

    }

    @Override
    public String toString() {
        return "Fish{" +
                "gills=" + gills +
                ", fins=" + fins +
                "} " + super.toString();
    }


}
