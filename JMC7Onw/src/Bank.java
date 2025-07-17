public class Bank {

    private int accNum;
    private double accBal;
    private String customerName;
    private String email;
    private String phoneNo;

    // getters
    public int getAccNum(){
        return accNum;
    }
    public double getAccBal(){
        return accBal;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNo(){
        return phoneNo;
    }
    //setters
    public void setAccNum(int accNum){
        this.accNum =accNum;
    }
    public void setAccBal(int accBal){
        this.accBal =accBal;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccBal(double accBal) {
        this.accBal = accBal;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void withdraw(int withdrawAmount){
        if (withdrawAmount > accBal) {
            System.out.println("Insufficient Funds");
        }else accBal-=withdrawAmount;
    }

    public void deposit(int depAmount){
        this.accBal+=depAmount;

    }


}
