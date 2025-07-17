import java.time.LocalDate;


public class Worker {
    private String name;
    private String birthDate;
    private String endDate;

    public Worker(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;

    }

    public int getAge(){
        LocalDate currDate= LocalDate.now();
        int age=currDate.getYear()-LocalDate.parse(birthDate).getYear();
        System.out.println("Employee's current age is "+age);
        return  age;

    }

    public double collectPay(){
        double pay=6000.00;
        return pay;
    }

    public void terminate(String endDate) {
        System.out.println("Employee Will be Terminated on " + endDate);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
