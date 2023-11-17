package com.rhymthwave.Controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersCaptureRequest;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.UserTypeService;
import com.rhymthwave.Service.Payment.PaymentService;
import com.rhymthwave.entity.UserType;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentSer;

	private final CRUD<UserType, Long> crudUserType;

	private final UserTypeService usertypeSer;
	
	private final PayPalHttpClient payPalHttpClient;

	@GetMapping("/payment")
	public String completedPayment() {
		return "User/pay";
	}

	@GetMapping("/cancelled")
	public String cancelledPayment() {
		return "User/payfail";
	}

	@GetMapping("/complete-payment-vnpay")
	public String completePaymentVNPay(@RequestParam("subcriptionId") Integer subcriptionId, @RequestParam("email") String email) {
		crudUserType.create(usertypeSer.generateEntity(email, subcriptionId, 1));
		return "redirect:/payment";
	}

	@GetMapping("/complete-payment-paypal")
	public String completePayment(@RequestParam("token") String token, @RequestParam("email") String email,@RequestParam("total") Float total,
			@RequestParam("PayerID") String PayerID, @RequestParam("subcriptionId") Integer subscriptionId) {
		try {
			OrdersCaptureRequest request = new OrdersCaptureRequest(paymentSer.paypal(token).getToken());
			request.requestBody("{\"payer_id\": \"" + PayerID + "\"}");
			HttpResponse<Order> captureResponse = captureResponse = payPalHttpClient.execute(request);
			Order capturedOrder = captureResponse.result();
			crudUserType.create(usertypeSer.generateEntity(email, subscriptionId, 1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "redirect:/payment";
	}

	@GetMapping("/completed-payment-stripe")
	public String completePayment(@RequestParam("subscription") Integer subscriptionId,@RequestParam("email") String email
							,@RequestParam("total") Float total) {
		crudUserType.create(usertypeSer.generateEntity(email, subscriptionId, 1));
		return "redirect:/payment";
	}
}
