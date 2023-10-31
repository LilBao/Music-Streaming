package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.PodcastDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.PodcastService;
import com.rhymthwave.entity.Podcast;

import jakarta.transaction.Transactional;

@Service
public class PodcastServiceImpl implements PodcastService,CRUD<Podcast,Long>{

	@Autowired
	PodcastDAO dao;
	
	@Override
	@Transactional
	public Podcast create(Podcast entity) {
		if(entity!=null) {
			entity.setRate(0);
			
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public Podcast update(Podcast entity) {
		if(entity!=null) {
			dao.save(entity);
			return entity;
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean delete(Long key) {
		if(key instanceof Long && key >= 0) {
			dao.deleteById(key);
			return true;
		}
		return false;
	}

	@Override
	public Podcast findOne(Long key) {
		return dao.findById(key).get();
	}

	@Override
	public List<Podcast> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Podcast> findMyPodcast(String email) {
		return dao.findMyPobcast(email);
	}
	
}
