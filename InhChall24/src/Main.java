public class Main {

    public static void main(String[] args) {


        SalariedEmployee joe=new SalariedEmployee("Sam","1955-10-10","2006-11-23",65000);
        Employee sam=new Employee("ssad","1895/15/10","2006-11-23");

        System.out.println(joe);
        joe.terminate("2024-10-5");
        joe.retire();
        joe.collectPay();
        joe.getAge();

        System.out.println(joe);
        //System.out.println(sam);
    }


}

