public class Customer {

    private String name;
    private String email;
    private double creditLimit;

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public Customer(String name ,String email,double creditLimit){
        this.name=name;
        this.email=email;
        this.creditLimit=creditLimit;
    }

    public Customer(){
        this("sam","mymail@gmail.com",200);

    }

    public Customer(String name ,String email){
        this(name,email,250);

    }
}
