public class MainAni {

    public static void main(String[] args) {
        Animal animal=new Animal("Some Animal","big",400.00);

        doAnimalStuff(animal,"slow");

        Dog dog =new Dog();
        doAnimalStuff(dog,"fast");

        Dog labrador=new Dog("Labrador",20);

        doAnimalStuff(labrador,"slow");

        Dog germanShepard=new Dog("shepard",35,"pointy","Swimmer");

        doAnimalStuff(germanShepard,"faster");

        Dog wolf =new Dog("Wolf",55);
        doAnimalStuff(wolf,"slow");

        Fish goldie=new Fish("Goldfish",10,2,6);
        doAnimalStuff(goldie,"fast");

    }

    public static void doAnimalStuff(Animal animal ,String speed){
        animal.makeNoise();
        animal.move(speed);
        System.out.println(animal);
        System.out.println("______");

    }
}
