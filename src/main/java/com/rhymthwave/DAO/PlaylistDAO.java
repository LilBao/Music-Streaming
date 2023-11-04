package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Playlist;
import com.rhymthwave.entity.UserType;

@Repository
public interface PlaylistDAO extends JpaRepository<Playlist, Long>{	
	List<Playlist> findByUsertype(UserType usertype);
	
	
}
