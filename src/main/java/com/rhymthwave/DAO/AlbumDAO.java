package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Album;

@Repository
public interface AlbumDAO extends JpaRepository<Album, Long> {
	@Query(value = "SELECT a.* FROM ALBUM a join artist on a.ARTISTID = ARTIST.ARTISTID "
			+ "WHERE a.ARTISTID= :artistId and NOT EXISTS (SELECT 1 FROM TRACK t WHERE t.ALBUMID = a.ALBUMID)", nativeQuery = true)
	List<Album> getAlbumNotTrack(@Param("artistId") Long artistId);

	@Query(value = "SELECT a.* FROM ALBUM a join artist on a.ARTISTID = ARTIST.ARTISTID \r\n"
			+ "WHERE a.ARTISTID= :artistId " + "and a.RELEASEDATE <  getDATE()"
			+ "and EXISTS (SELECT 1 FROM TRACK t WHERE t.ALBUMID = a.ALBUMID)", nativeQuery = true)
	List<Album> getListAlbumReleasedByArtist(@Param("artistId") Long artistId);
	
	@Query(value = "SELECT a.* FROM ALBUM a join artist on a.ARTISTID = ARTIST.ARTISTID \r\n"
			+ "WHERE a.ARTISTID= :artistId and EXISTS (SELECT 1 FROM TRACK t WHERE t.ALBUMID = a.ALBUMID)", nativeQuery = true)
	List<Album> getListAlbumByArtist(@Param("artistId") Long artistId);

	@Query(value = "SELECT AL.*, IMGAL.URL FROM ALBUM AL LEFT JOIN IMAGES IMGAL ON AL.COVERIMAGE = IMGAL.ACCESSID WHERE AL.ALBUMNAME LIKE %:keyword% AND AL.RELEASEDATE < GETDATE()", nativeQuery = true)
	List<Object> findByName(@Param("keyword") String keyword);
}
