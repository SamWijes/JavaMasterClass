package dev.imutCh;

public class Main {
    public static void main(String[] args) {
        Bank com = new Bank(8967);

        com.addCustomer("Sam", 4589.56, 78415.45);
        com.addCustomer("Joe", 784.45, 8715.45);
        com.addCustomer("Rogers", 4589.56, 78412.56);

        com.doTransaction("000000010000000", BankAccount.AccountType.SAVINGS,-80000);

        System.out.println(com.getCustomer("000000010000000").getAccount(BankAccount.AccountType.SAVINGS).getBalance());
    }

}
