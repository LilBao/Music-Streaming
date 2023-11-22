package com.rhymthwave.Service;

import java.util.List;

import com.rhymthwave.entity.Subscription;
import com.rhymthwave.entity.TypeEnum.ESubscription;

public interface SubscriptionService {
	Subscription getSubByName(String name);
	
	List<Subscription> findByCategory(ESubscription cate);
	
}
