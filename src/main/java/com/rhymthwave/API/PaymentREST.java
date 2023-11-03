package com.rhymthwave.API;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rhymthwave.DTO.payment.StripeTokenDTO;
import com.rhymthwave.DTO.payment.SubscriptionDTO;
import com.rhymthwave.Service.Payment.PaymentService;
import com.rhymthwave.Service.Payment.StripeService;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.payment.Payment;
import com.stripe.exception.StripeException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class PaymentREST {
	
	private final PaymentService paymentSer;
	
	private final StripeService stripeSer;
	
	private final GetHostByRequest host;
	
	@PostMapping("/api/v1/payment-vnpay")
	public ResponseEntity<Payment> createPaymentVNPay(@RequestParam("total") Integer total,@RequestParam("subcriptionId") Integer subcriptionId,HttpServletRequest req){
		String email = host.getEmailByRequest(req);
		return ResponseEntity.ok(paymentSer.vnpay(total, email,subcriptionId));
	}
	
	
	@PostMapping("/api/v1/payment-paypal")
	public ResponseEntity<Payment> createPaymetnPaypal(@RequestParam("total") BigDecimal total,@RequestParam("subscriptionId") Integer subscription,HttpServletRequest req,String pathReturn, String pathCancel){
		pathReturn = "/complete-payment-paypal";
		pathCancel = "/";
		String email = host.getEmailByRequest(req);
		return ResponseEntity.ok(paymentSer.createPaypal(total,subscription,email,req,pathReturn,pathCancel));
	}
	
	@PostMapping("/api/v1/payment-stripe")
	public ResponseEntity<Payment> createPaymentStripe(@RequestBody SubscriptionDTO subscription ,HttpServletRequest req){
		String owner = host.getEmailByRequest(req);
		return ResponseEntity.ok(stripeSer.checkoutPayment(subscription,owner,req));
	}
	
	@PostMapping("/api/v1/create-card-stripe")
	public ResponseEntity<StripeTokenDTO> createCard(@ModelAttribute StripeTokenDTO stripe){
		return ResponseEntity.ok(paymentSer.createCardStripe(stripe));
	}
	
	
	
}
