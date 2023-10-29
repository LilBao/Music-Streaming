package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Playlist;

@Repository
public interface PlaylistDAO extends JpaRepository<Playlist, Long>{
	@Query("select o from Playlist o where o.usertype.account = :email")
	List<Playlist> findMyPlaylist(@Param("email") String email);
}
