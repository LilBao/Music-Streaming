package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.CultureDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.Culture;

import jakarta.transaction.Transactional;

@Service
public class CultureServiceImpl implements CRUD<Culture, Integer>{

	@Autowired
	CultureDAO dao;
	
	@Override
	@Transactional
	public Culture create(Culture entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public Culture update(Culture entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean delete(Integer key) {
		if(key instanceof Integer && key>0) {
			dao.deleteById(key);
			return true;
		}
		return false;
	}

	@Override
	public Culture findOne(Integer key) {
		if(key instanceof Integer && key>0) {
			return dao.findById(key).get();
		}
		return null;
	}

	@Override
	public List<Culture> findAll() {
		return dao.findAll();
	}

	
}
