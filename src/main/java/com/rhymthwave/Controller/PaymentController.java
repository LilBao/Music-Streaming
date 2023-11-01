package com.rhymthwave.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.UserTypeService;
import com.rhymthwave.Service.Payment.PaymentService;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.UserType;
import com.rhymthwave.entity.payment.CompletedOrder;
import com.rhymthwave.entity.payment.Payment;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaymentController {
	private final GetHostByRequest host;

	private final PaymentService paymentSer;

	private final CRUD<UserType, Long> crudUserType;

	private final UserTypeService usertypeSer;

	@GetMapping("/payment")
	public String completedPayment() {
		return "";
	}

	@GetMapping("/cancelled")
	public String cancelledPayment() {
		return "";
	}

	@PostMapping("/complete-payment-vnpay")
	public String completePaymentVNPay(@RequestParam("total") Integer total,
			@RequestParam("subcriptionId") Integer subcriptionId, @RequestParam("email") String email,
			HttpServletRequest req) {
		
		crudUserType.create(usertypeSer.generateEntity(email, subcriptionId, 1));
		return "redirect:/payment";
	}

	@GetMapping("/complete-payment-paypal")
	public String completePayment(@RequestParam("token") String token, @RequestParam("email") String email,
			@RequestParam("subscription") Integer subscriptionId) {

		paymentSer.paypal(token);
		crudUserType.create(usertypeSer.generateEntity(email, subscriptionId, 1));
		return "redirect:/payment";
	}

	@GetMapping("/complete-payment-stripe")
	public String completePayment(HttpServletRequest req, @RequestParam("subscription") Integer subscriptionId,
			@RequestParam("email") String email) {
		crudUserType.create(usertypeSer.generateEntity(email, subscriptionId, 1));
		return "redirect:/payment";
	}
}
