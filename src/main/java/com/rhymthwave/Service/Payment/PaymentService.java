package com.rhymthwave.Service.Payment;

import java.math.BigDecimal;

import com.rhymthwave.DTO.payment.StripeChargeDTO;
import com.rhymthwave.DTO.payment.StripeTokenDTO;
import com.rhymthwave.DTO.payment.SubscriptionDTO;
import com.rhymthwave.entity.payment.CompletedOrder;
import com.rhymthwave.entity.payment.Payment;

import jakarta.servlet.http.HttpServletRequest;

public interface PaymentService {
	Payment	vnpay(Integer total,String email,Integer subscriptionId);
	
	Payment createPaypal(BigDecimal fee,Long subscription, String email,HttpServletRequest req, String pathReturn, String pathCancel);
	CompletedOrder paypal(String token);
	
	StripeTokenDTO createCardStripe(StripeTokenDTO stripe);
	StripeChargeDTO chargeStripe(StripeChargeDTO chargeRequest);
	Payment checkoutStripe(SubscriptionDTO subscription,String email,HttpServletRequest req);
}
