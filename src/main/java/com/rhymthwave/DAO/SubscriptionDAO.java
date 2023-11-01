package com.rhymthwave.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Subscription;

@Repository
public interface SubscriptionDAO extends JpaRepository<Subscription, Integer>{
	Subscription findBySubscriptionType(String subscriptionType);
}
