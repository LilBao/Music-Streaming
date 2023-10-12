package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.SongStyleDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.entity.SongStyle;

import jakarta.transaction.Transactional;

@Service
public class SongStyleServiceImpl implements CRUD<SongStyle, Integer>{

	@Autowired
	SongStyleDAO dao;
	
	@Override
	@Transactional
	public SongStyle create(SongStyle entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public SongStyle update(SongStyle entity) {
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
	public SongStyle findOne(Integer key) {
		if(key instanceof Integer && key>0) {
			return dao.findById(key).get();
		}
		return null;
	}

	@Override
	public List<SongStyle> findAll() {
		return dao.findAll();
	}

	
}
