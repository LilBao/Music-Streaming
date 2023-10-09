package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Album;

@Repository
public interface AlbumDAO extends JpaRepository<Album, Integer>{
	@Query(value="SELECT a.* FROM ALBUM a WHERE NOT EXISTS (SELECT 1 FROM TRACK t WHERE t.TRACKID = a.ALBUMID)", nativeQuery = true)
	List<Album> getAlbumNotTrack();
}
