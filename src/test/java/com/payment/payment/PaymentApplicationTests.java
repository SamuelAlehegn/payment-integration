// File: src/test/java/com/payment/payment/PaymentApplicationTests.java
package com.payment.payment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PaymentApplicationTests {
	
	@Test
	void contextLoads() {
		// Verify that the application context loads successfully
		assertThat(true).isTrue();
	}
	
}