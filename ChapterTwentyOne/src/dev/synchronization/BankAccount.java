package dev.synchronization;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class BankAccount {

	private double balance;

	private String name;

	private final Object lockName=new Object();
	private final Object lockBalance=new Object();

	public BankAccount(String name,double balance){
		this.balance=balance;
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public  void setName(String name) {
		synchronized (lockName) {
			this.name = name;
			System.out.println("Updated Name = " + this.name);
		}
	}

	public double getBalance() {
		return balance;
	}

	public  void deposit(double amount){
		try{
			System.out.println("Talking to the teller at the bank...");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		Double boxedBalance= balance;
		synchronized (lockBalance){
			double origBalance=balance;
			balance+=amount;
			System.out.printf("STARTING BALANCE: %.0f, DEPOSIT (%.0f)" +
					" : NEW BALANCE = %.0f%n", origBalance, amount, balance);
			adPromoDollars(amount);
		}

	}
	private void adPromoDollars(double amount){
		if (amount >= 5000) {
			synchronized (lockBalance) {
				System.out.println("Congrats you earned promo deposit");
				balance += 25;
			}
		}
	}

	public synchronized void withdraw(double amount){
		try{

			Thread.sleep(100);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		double origBalance=balance;

		if (amount <= balance) {
			balance -= amount;
			System.out.printf("STARTING BALANCE: %.0f, WITHDRAWAL (%.0f)" +
					" : NEW BALANCE = %.0f%n", origBalance, amount, balance);
		} else {
			System.out.printf("STARTING BALANCE: %.0f, WITHDRAWAL (%.0f)" +
					": INSUFFICIENT FUNDS!", origBalance, amount);
		}
	}




}
