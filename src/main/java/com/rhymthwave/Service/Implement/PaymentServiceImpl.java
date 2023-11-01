package com.rhymthwave.Service.Implement;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DTO.payment.StripeChargeDTO;
import com.rhymthwave.DTO.payment.StripeTokenDTO;
import com.rhymthwave.DTO.payment.SubscriptionDTO;
import com.rhymthwave.Service.Payment.PaymentService;
import com.rhymthwave.Service.Payment.PaypalService;
import com.rhymthwave.Service.Payment.StripeService;
import com.rhymthwave.Service.Payment.VNPayService;
import com.rhymthwave.Utilities.GetHostByRequest;
import com.rhymthwave.entity.payment.CompletedOrder;
import com.rhymthwave.entity.payment.Payment;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PaymentServiceImpl implements PaymentService{
		
	@Autowired
	PaypalService paypalSer;
	
	@Autowired
	VNPayService vnpaySer;
	
	@Autowired
	StripeService stripeSer;
	
	@Override
	public Payment vnpay(Integer total, String email,Integer subscriptionId) {
		return vnpaySer.vnpay(total, email,subscriptionId);
	}
	

	@Override
	public Payment createPaypal(BigDecimal fee,Long subscription, String email ,HttpServletRequest req, String pathReturn, String pathCancel) {
		return paypalSer.createPayment(fee,subscription,email ,req, pathReturn, pathCancel);
	}


	@Override
	public CompletedOrder paypal(String token) {
		return paypalSer.completePayment(token);
	}


	@Override
	public StripeTokenDTO createCardStripe(StripeTokenDTO stripe) {
		return stripeSer.createCardToken(stripe);
	}


	@Override
	public StripeChargeDTO chargeStripe(StripeChargeDTO chargeRequest) {
		return stripeSer.chargeStripe(chargeRequest);
	}


	@Override
	public Payment checkoutStripe(SubscriptionDTO subscription, String email, HttpServletRequest req) {
		return stripeSer.checkoutPayment(subscription, email, req);
	}
	
}
