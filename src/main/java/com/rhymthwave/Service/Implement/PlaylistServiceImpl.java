package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.PlaylistDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.PlaylistService;
import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.UserType;

import jakarta.transaction.Transactional;

@Service
public class PlaylistServiceImpl implements PlaylistService, CRUD<Playlist, Long>{
	
	@Autowired
	PlaylistDAO dao;

	@Override
	@Transactional
	public Playlist create(Playlist entity) {
		if(entity.getQuantity() > 0 || entity.getPlaylistName() != null) {
			return dao.save(entity);
		}
		Playlist playlist = entity;
		playlist.setQuantity(0);
		playlist.setPlaylistName("My Playlist");
		return dao.save(playlist);
	}

	@Override
	@Transactional
	public Playlist update(Playlist entity) {
		Playlist playlist = entity;
		return dao.save(playlist);
	}

	@Override
	@Transactional
	public Boolean delete(Long key) {
		dao.deleteById(key);
		return true;
	}

	@Override
	public Playlist findOne(Long key) {
		return dao.findById(key).get();
	}

	@Override
	public List<Playlist> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Playlist> findMyPlaylist(UserType usertype) {
		return dao.findByUsertype(usertype);
	}
	
	
}
