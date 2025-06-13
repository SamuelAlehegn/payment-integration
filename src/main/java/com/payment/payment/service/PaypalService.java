package com.payment.payment.service;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class PaypalService {
  
  private final APIContext apiContext;
  
  public PaypalService(APIContext apiContext) {
    this.apiContext = apiContext;
  }
  
  public Payment createPayment(String name, String email, Double amount, String currency, String method, String intent, String description, String cancelUrl, String returnUrl) {
    Amount paymentAmount = new Amount();
    paymentAmount.setCurrency(currency);
    paymentAmount.setTotal(String.format(Locale.forLanguageTag(currency), "%.2f", amount)); // use Locale to format the amount correctly based on currency
    
    Transaction transaction = new Transaction();
    transaction.setDescription(description);
    transaction.setAmount(paymentAmount);
    
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(transaction);
    
    Payer payer = new Payer();
    payer.setPaymentMethod(method);
    
    Payment payment = new Payment();
    payment.setIntent(intent);
    payment.setPayer(payer);
    payment.setTransactions(transactions);
    
    RedirectUrls redirectUrls = new RedirectUrls();
    redirectUrls.setCancelUrl(cancelUrl);
    redirectUrls.setReturnUrl(returnUrl);
    
    payment.setRedirectUrls(redirectUrls);
    
    try {
      return payment.create(apiContext);
    } catch (Exception e) {
      throw new RuntimeException("Error creating payment: " + e.getMessage(), e);
    }
  }
  
  public Payment executePayment(String paymentId, String payerId) {
    try {
      Payment payment = new Payment();
      payment.setId(paymentId);
      
      PaymentExecution paymentExecution = new PaymentExecution();
      paymentExecution.setPayerId(payerId);
      
      return payment.execute(apiContext, paymentExecution);
    } catch (Exception e) {
      throw new RuntimeException("Error executing payment: " + e.getMessage(), e);
    }
  }

}
