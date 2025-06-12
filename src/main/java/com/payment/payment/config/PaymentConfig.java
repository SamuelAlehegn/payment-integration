package com.payment.payment.config;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {
  
  @Value("${paypal.client.id}")
  private String clientId;
  
  @Value("${paypal.client.secret}")
  private String clientSecret;
  
  @Value("${payment.mode}")
  private String mode;
  
  @Bean
  public APIContext apiContext() {
    if (!"sandbox".equalsIgnoreCase(mode) && !"live".equalsIgnoreCase(mode)) {
      throw new IllegalArgumentException("Mode needs to be either `sandbox` or `live`.");
    }
    return new APIContext(clientId, clientSecret, mode);
  
  }
}