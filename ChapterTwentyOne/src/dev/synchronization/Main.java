package dev.synchronization;

public class Main {
	public static void main(String[] args) {
		BankAccount companyAccount = new BankAccount("Tom",10000);

		Thread thread1 = new Thread(() -> companyAccount.withdraw(2500));
		Thread thread2 = new Thread(() -> companyAccount.deposit(5000));
		Thread thread3 = new Thread(() -> companyAccount.setName("Tim"));
		Thread thread4 = new Thread(() -> companyAccount.withdraw(5000));

		thread1.start();
		thread2.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		thread3.start();
		thread4.start();



		try {
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Final Balance: " + companyAccount.getBalance());

	}
}
