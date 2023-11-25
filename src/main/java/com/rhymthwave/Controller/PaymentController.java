package com.rhymthwave.Controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Capture;
import com.paypal.orders.Order;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersGetRequest;
import com.paypal.orders.PurchaseUnit;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.HistoryPaymentService;
import com.rhymthwave.Service.UserTypeService;
import com.rhymthwave.Service.Payment.PaymentService;
import com.rhymthwave.Utilities.Cookie.CookiesUntils;
import com.rhymthwave.entity.Payment;
import com.rhymthwave.entity.UserType;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentSer;

	private final CRUD<UserType, Long> crudUserType;

	private final UserTypeService usertypeSer;
	
	private final CRUD<Payment, Long> crudPayment;
	
	private final HistoryPaymentService historyPayment;
	
	@GetMapping("/payment")
	public String completedPayment() {
		return "User/pay";
	}

	@GetMapping("/cancelled")
	public String cancelledPayment() {
		return "User/payfail";
	}

	@GetMapping("/complete-payment-vnpay")
	public String completePaymentVNPay(@RequestParam("subcriptionId") Integer subcriptionId,@RequestParam("paymentName") String paymentName,
			@RequestParam("email") String email) {
		crudUserType.create(usertypeSer.generateEntity(email, subcriptionId, 1));
		crudPayment.create(historyPayment.payment(email, subcriptionId, paymentName));
		return "redirect:/payment";
	}

	@GetMapping("/complete-payment-paypal")
	public String completePayment(@RequestParam("token") String token, @RequestParam("email") String email,@RequestParam("paymentName") String paymentName,
			@RequestParam("total") Float total, @RequestParam("PayerID") String PayerID,
			@RequestParam("subcriptionId") Integer subscriptionId) {
		Order order = paymentSer.billing(token);
		crudUserType.create(usertypeSer.generateEntity(email, subscriptionId, 1));
		crudPayment.create(historyPayment.payment(email, subscriptionId, paymentName));
		return "redirect:/payment";
	}

	@GetMapping("/completed-payment-stripe")
	public String completePayment(@RequestParam("subscription") Integer subscriptionId,@RequestParam("paymentName") String paymentName,
			@RequestParam("email") String email, @RequestParam("total") Float total,HttpServletRequest req) {
		crudUserType.create(usertypeSer.generateEntity(email, subscriptionId, 1));
		crudPayment.create(historyPayment.payment(email, subscriptionId, paymentName));
		return "redirect:/payment";
	}
}
