package dev.imutCh;

import dev.imutCh.dto.Transaction;

import java.util.*;

public class BankAccount {

    public enum AccountType {CHECKING, SAVINGS}

    private final AccountType accountType;
    private double balance;

    private Map<Long, Transaction> transactionMap;

    BankAccount(AccountType accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
        transactionMap=new TreeMap<>();
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public TreeMap<Long, Transaction> getTransactionMap() {

        return new TreeMap<>(transactionMap);
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(accountType, balance);
    }


    public void commitTransaction(int routingNum,long transactionID,String customerID, double amount){
        if(amount>0) {
            balance += amount;
            transactionMap.put(transactionID, new Transaction(routingNum,
                                Integer.parseInt(customerID), amount, transactionID));
        }
        else if (amount+balance>=0) {
            balance+=amount;
            transactionMap.put(transactionID, new Transaction(routingNum,
                                Integer.parseInt(customerID), amount, transactionID));
        }
        else System.out.println("Insufficient Funds for Withdrawal");
    }
}
