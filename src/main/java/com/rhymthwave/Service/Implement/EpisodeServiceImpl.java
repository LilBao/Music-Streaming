package com.rhymthwave.Service.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.EpisodeDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.EpisodeService;
import com.rhymthwave.entity.Episode;

import jakarta.transaction.Transactional;

@Service
public class EpisodeServiceImpl implements EpisodeService, CRUD<Episode, Long>{

	@Autowired
	EpisodeDAO dao;
	
	@Override
	@Transactional
	public Episode create(Episode entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public Episode update(Episode entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean delete(Long key) {
		dao.deleteById(key);
		return true;
	}

	@Override
	public Episode findOne(Long key) {
		return dao.findById(key).get();
	}

	@Override
	public List<Episode> findAll() {
		return dao.findAll();
	}
	
}
