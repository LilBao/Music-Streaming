package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Subscription;
import com.rhymthwave.entity.TypeEnum.ESubscription;

@Repository
public interface SubscriptionDAO extends JpaRepository<Subscription, Integer>{
	Subscription findBySubscriptionType(String subscriptionType);
	
	List<Subscription> findBySubscriptionCategoryAndActive(ESubscription category,Boolean active);
}
