package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Album;

@Repository
public interface AlbumDAO extends JpaRepository<Album, Integer>{
	@Query(value="SELECT a.* FROM ALBUM a join artist on a.ARTISTID = ARTIST.ARTISTID "
				+ "WHERE a.ARTISTID= :artistId and NOT EXISTS (SELECT 1 FROM TRACK t WHERE t.TRACKID = a.ALBUMID)", nativeQuery = true)
	List<Album> getAlbumNotTrack(@Param("artistId") Long artistId);
	
	@Query(value="SELECT a.* FROM ALBUM a join artist on a.ARTISTID = ARTIST.ARTISTID \r\n"
				+ "WHERE a.ARTISTID= :artistId "
				+ "and a.RELEASEDATE <  getDATE()"
				+ "and EXISTS (SELECT 1 FROM TRACK t WHERE t.TRACKID = a.ALBUMID)", nativeQuery = true)
	List<Album> getListAlbumReleasedByArtist(@Param("artistId") Long artistId);
}
