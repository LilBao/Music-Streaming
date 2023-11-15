package com.rhymthwave.Service.Implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhymthwave.DAO.PlaylistDAO;
import com.rhymthwave.DAO.PlaylistRecordDAO;
import com.rhymthwave.Service.CRUD;
import com.rhymthwave.Service.PlaylistService;
import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.PlaylistRecord;
import com.rhymthwave.entity.Recording;
import com.rhymthwave.entity.UserType;

import jakarta.transaction.Transactional;

@Service
public class PlaylistServiceImpl implements PlaylistService, CRUD<Playlist, Long>{
	
	@Autowired
	PlaylistDAO dao;
	
	@Autowired
	PlaylistRecordDAO daoPR;

	@Override
	@Transactional
	public Playlist create(Playlist entity) {
		Playlist playlist = entity;
		playlist.setQuantity(0);
		if(playlist.getPlaylistName()==null) {
			playlist.setPlaylistName("My Playlist");
		}
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

	@Override
	@Transactional
	public Playlist createSimilarPodcast(Playlist playlist, List<Recording> list) {
		for (Recording recording : list) {
			PlaylistRecord playlistRecord = new PlaylistRecord();
			playlistRecord.setPlaylist(playlist);
			playlistRecord.setRecording(recording);
			daoPR.save(playlistRecord);
		}
		return playlist;
	}

	@Override
	public List<Playlist> findPublicPlaylist(UserType userType, Boolean isPublic) {
		return dao.findByUsertypeAndIsPublic(userType, isPublic);
	}
	
	
}
