public class SalariedEmployee extends Employee{
    private  double annualSalary;
    protected boolean isRetired;


    public SalariedEmployee(String name, String birthDate, String hireDate,double annualSalary) {
        super(name, birthDate, hireDate);
        this.annualSalary=annualSalary;
    }

    public boolean retire(){
        return isRetired=true;
    }

    @Override
    public double collectPay() {
        System.out.println("collected Payment annual salary of   "+annualSalary);
        return annualSalary;
    }
}