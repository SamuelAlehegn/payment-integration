package com.payment.payment.controller;

import com.payment.payment.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PaymentController {
  
  private final PaypalService paypalService;
  
  public PaymentController(PaypalService paypalService) {
    this.paypalService = paypalService;
  }
  
  
  @Value("${payment.cancel.url}")
  private String cancelUrl;
  
  @Value("${payment.return.url}")
  private String returnUrl;
  
  
  @GetMapping("/")
  public String index() {
    
    return "index";
  }
  
  
  @PostMapping("/create-payment")
  public RedirectView createPayment(
      @RequestParam("name") String name,
      @RequestParam("email") String email,
      @RequestParam("amount") Double amount,
      @RequestParam("currency") String currency,
      @RequestParam("method") String method,
      @RequestParam("intent") String intent,
      @RequestParam("description") String description
  ) {
    try {
       Payment payment = paypalService.createPayment(name, email, amount, currency, method, intent, description, cancelUrl, returnUrl);
       for (Links link: payment.getLinks()) {
         if (link.getRel().equals("approval_url")) {
           return new RedirectView(link.getHref());
         }
       }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new RedirectView("/error");
  }
  
  @GetMapping("/success")
  public String success(@RequestParam("paymentId") String paymentId , @RequestParam("payerId") String payerId) {
    try {
      Payment payment = paypalService.executePayment(paymentId, payerId);
      if (payment.getState().equals("approved")) {
        return "success";
      } else {
        return "error";
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }
  
  @GetMapping("/cancel")
  public String cancel() {
    return "cancel";
  }
  
  @GetMapping("/error")
  public String error() {
    return "error";
  }
  
  @GetMapping("transaction")
  public String lastTransaction() {
    return "lastTransaction";
  }

}
