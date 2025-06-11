package com.payment.payment.paypal;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
public class PaymentController {
  
  private final PaypalService paypalService;
  
  
  @Value("${payment.cancel.url}")
  private String cancelUrl;
  
  @Value("${payment.return.url}")
  private String returnUrl;
  
  @GetMapping("/")
  public String index() {
    
    return "index";
  }
  
  @PostMapping("/create-payment")
  public RedirectView createPayment(String name, String email, Double amount, String currency, String method, String intent, String description, String cancelUrl, String returnUrl) {
    try {
       Payment payment = paypalService.createPayment(name, email, amount, currency, method, intent, description, cancelUrl, returnUrl);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
