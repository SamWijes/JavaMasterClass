public class MainBank {

    public static void main(String[] args) {
        Bank bank= new Bank();
        bank.setAccNum(785);
        bank.setAccBal(5000);
        bank.setEmail("a@gmail.com");
        bank.setPhoneNo("645611511616");
        bank.setCustomerName("sam");
        bank.withdraw(5500);
        bank.deposit(1000);



        int accNum=bank.getAccNum();
        double accBal=bank.getAccBal();
        String customerName=bank.getCustomerName();
        String email=bank.getEmail();
        String phoneNo=bank.getPhoneNo();
        System.out.println(accNum);
        System.out.println(accBal);
        System.out.println(customerName);
        System.out.println(email);
        System.out.println(phoneNo);
    }

}
