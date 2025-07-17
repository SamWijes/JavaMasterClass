package dev.imutCh.dto;

public class Transaction {
    //private static int INIT_ID=10000;
    private int routingNumber;
    private int customerID;
    private long transactionID;
    private double transactionAmount;


    public Transaction(int routingNumber, int customerID, double transactionAmount,long transactionID) {
        this.routingNumber = routingNumber;
        this.customerID = customerID;
        this.transactionID = transactionID;
        this.transactionAmount = transactionAmount;
    }

    public long getTransactionID() {
        return transactionID;
    }
}
