package dev.Junit;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
	private BankAccount account;
	private static int count;

	@org.junit.jupiter.api.BeforeAll
	public static void beforeClass() {
		System.out.println("This executes before any test cases.count = " + count++);
	}

	@org.junit.jupiter.api.BeforeEach
	public void setup() {
		account = new BankAccount("Sam", "Wij", 5000, BankAccount.CHECKING);
		System.out.println("Running Test...");
	}


	@org.junit.jupiter.api.Test
	void deposit() {
		double balance = account.deposit(200.00, true);
		assertEquals(5200, balance, 0);
	}

	@org.junit.jupiter.api.Test
	void withdraw_branch() {
		account.withdraw(600, true);
		assertEquals(4400,account.getBalance());
	}

	@org.junit.jupiter.api.Test
	void withdraw_notBranch() {
		assertThrows(IllegalArgumentException.class,()->{
			account.withdraw(600, false);

		});
	}

	@org.junit.jupiter.api.Test
	void getBalance_deposit() {
		account.deposit(200.00, true);
		assertEquals(5200, account.getBalance(), 0);
	}

	@org.junit.jupiter.api.Test
	void getBalance_withdraw() {
		account.withdraw(200.00, true);
		assertEquals(4800, account.getBalance(), 0);
	}

	@org.junit.jupiter.api.Test
	public void isChecking_true() {
//		assertEquals(true, account.isChecking());
		assertTrue(account.isChecking(), "The account is not a checking");

	}

	@org.junit.jupiter.api.AfterAll
	public static void afterClass() {
		System.out.println("This executes after nay test. Count= " + count++);
	}

	@org.junit.jupiter.api.AfterEach
	public void teardown() {
		System.out.println("Count = "+count++);
	}
}