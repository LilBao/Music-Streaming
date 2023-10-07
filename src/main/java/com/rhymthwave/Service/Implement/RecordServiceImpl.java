package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.RecordDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.RecordService;
import com.rhymthwave.entity.Recording;

@Service
public class RecordServiceImpl implements RecordService, CRUD<Recording, Integer>{

	@Autowired
	RecordDAO dao;
	
	@Override
	public Recording create(Recording entity) {
		if (entity != null) {
			entity.setLikes(0);
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	public Recording update(Recording entity) {
		if (entity != null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	public Boolean delete(Integer key) {
		if (key instanceof Integer && key >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public Recording findOne(Integer key) {
		if (key instanceof Integer && key >= 0) {
			return dao.findById(key).get();
		}
		return null;
	}

	@Override
	public List<Recording> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Recording> findRecordByCreater(String email) {
		return findRecordByCreater(email);
	}
	
}
