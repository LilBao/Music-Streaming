package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.MonitorDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.MonitorService;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Monitor;
import com.rhymthwave.entity.Recording;

import jakarta.transaction.Transactional;

@Service
public class MonitorServiceImpl implements CRUD<Monitor, Long>, MonitorService{

	@Autowired
	MonitorDAO dao;
	
	@Override
	@Transactional
	public Monitor create(Monitor entity) {
		Monitor monitor = entity;
		return dao.save(monitor);
	}

	@Override
	@Transactional
	public Monitor update(Monitor entity) {
		Monitor monitor = entity;
		return dao.save(monitor);
	}

	@Override
	@Transactional
	public Boolean delete(Long key) {
		dao.deleteById(key);
		return true;
	}

	@Override
	public Monitor findOne(Long key) {
		return dao.findById(key).get();
	}

	@Override
	public List<Monitor> findAll() {
		return dao.findAll();
	}

	@Override
	public Monitor checkExist(Recording recording, Account account) {
		Monitor monitor = dao.findByRecordingAndAccount(recording, account);
		if(monitor!=null) {
			return monitor;
		}
		return null;
	}
	
}
