package dev.imutCh;

import dev.imutCh.dto.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Bank {

    private int routingNum;
    private long lastTransactionId;
    private Map<String ,BankCustomer> customers;

    public Bank(int routingNu) {
        this.routingNum = routingNu;
        this.customers = new HashMap<>();
    }

    public BankCustomer getCustomer(String id){
        return customers.get(id);
    }

    public void addCustomer(String name,double checkingInitDeposit,double savingInitDeposit){
        BankCustomer customer=new BankCustomer(name,checkingInitDeposit,savingInitDeposit);
        customers.put(customer.getCustomerId(),customer);
        System.out.println(customer.getCustomerId());
    }

    public  void doTransaction(String id, BankAccount.AccountType accountType,double amount){

        BankCustomer customer=getCustomer(id);
        BankAccount account = customer.getAccount(accountType);
        long curTransactionId=lastTransactionId+1;
        account.commitTransaction(routingNum,curTransactionId, customer.getCustomerId(), amount);
        lastTransactionId=curTransactionId;

    }
}
