package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.SubscriptionDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.SubscriptionService;
import com.rhymthwave.entity.Subscription;

@Service
public class SubscriptionServiceImpl implements SubscriptionService ,CRUD<Subscription, Integer>{

	@Autowired
	SubscriptionDAO dao;
	
	@Override
	public Subscription getSubByName(String name) {
		return dao.findBySubscriptionType(name);
	}

	@Override
	public Subscription create(Subscription entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscription update(Subscription entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(Integer key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscription findOne(Integer key) {
		return dao.findById(key).get();
	}

	@Override
	public List<Subscription> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
