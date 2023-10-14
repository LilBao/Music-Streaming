package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Song;

@Repository
public interface  SongDAO extends JpaRepository<Song, Integer>{
	@Query(value="select s.* from SONGS s join WRITTER w on s.SONGSID = w.SONGSID "
				+ "where w.ARTISTID= :idArtist and not exists (select 1 from recording r where r.songsid = s.songsid)",nativeQuery = true)
	List<Song> getSongNotRecord(@Param("idArtist") Long idArtist);
	
	@Query(value="select SONGS.* from SONGS "
				+ "join WRITTER on SONGS.SONGSID = WRITTER.SONGSID "
				+ "join ARTIST on ARTIST.ARTISTID = WRITTER.SONGSID "
				+ "where songs.REALEASEDAY < GETDATE() and ARTIST.ARTISTID = :artistId and "
				+ "EXISTS (SELECT 1 FROM RECORDING WHERE RECORDING.SONGSID = SONGS.SONGSID) ",nativeQuery = true)
	List<Song> getListSongReleasedByArtist(@Param("artistId") Long artistId);
}	
