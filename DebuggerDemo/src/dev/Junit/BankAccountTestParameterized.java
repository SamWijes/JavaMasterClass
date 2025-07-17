package dev.Junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;



public class BankAccountTestParameterized {
	private BankAccount account;

	@org.junit.jupiter.api.BeforeEach
	public void setup() {
		account = new BankAccount("Sam", "Wij", 1000, BankAccount.CHECKING);
		System.out.println("Running Test...");
	}

	static Stream<Arguments> depositData() {
		return Stream.of(
				arguments(100.00, true, 1100.00),
				arguments(200.00, true, 1200.00),
				arguments(325.14, true, 1325.14),
				arguments(489.33, true, 1489.33),
				arguments(1000.00, true, 2000.00)
		);
	}

	@ParameterizedTest
	@MethodSource("depositData")
	@DisplayName("Deposit parameterized test")

	void deposit(double amount,boolean branch,double expected) {
		account.deposit(amount, branch);
		assertEquals(expected, account.getBalance(), 0.01);
	}



}
