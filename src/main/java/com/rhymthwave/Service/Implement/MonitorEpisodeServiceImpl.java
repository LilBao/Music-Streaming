package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.MonitorEpisodeDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.MonitorEpisodeService;
import com.rhymthwave.entity.Account;
import com.rhymthwave.entity.Episode;
import com.rhymthwave.entity.MonitorEpisode;

import jakarta.transaction.Transactional;

@Service
public class MonitorEpisodeServiceImpl implements CRUD<MonitorEpisode, Long>, MonitorEpisodeService{
	
	@Autowired
	MonitorEpisodeDAO dao;

	@Override
	@Transactional
	public MonitorEpisode create(MonitorEpisode entity) {
		MonitorEpisode monitor = entity;
		return dao.save(monitor);
	}

	@Override
	@Transactional
	public MonitorEpisode update(MonitorEpisode entity) {
		MonitorEpisode monitor = entity;
		return dao.save(monitor);
	}

	@Override
	@Transactional
	public Boolean delete(Long key) {
		dao.deleteById(key);
		return true;
	}

	@Override
	@Transactional
	public MonitorEpisode findOne(Long key) {
		return dao.findById(key).get();
	}

	@Override
	@Transactional
	public List<MonitorEpisode> findAll() {
		return dao.findAll();
	}

	@Override
	public MonitorEpisode checkExist(Episode episode, Account account) {
		MonitorEpisode monitorEp = dao.findByEpisodeAndAccount(episode, account);
		if(monitorEp!=null) {
			return monitorEp;
		}
		return null;
	}
}
