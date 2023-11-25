package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Recording;
import com.rhymthwave.entity.Song;

@Repository
public interface RecordDAO extends JpaRepository<Recording, Long> {
	@Query("Select o from Recording o where o.song is null and o.emailCreate = :creater")
	List<Recording> getListRecordNotRaw(@Param("creater") String creater);

	@Query("select o from Recording o where o.song is not null and o.emailCreate =:creater")
	List<Recording> getListRawRecord(@Param("creater") String creater);

	@Query("select o from Recording o where o.song.songId = :songId")
	List<Recording> getListRecordBySong(@Param("songId") Long songId);

	@Query("Select o from Recording o where o.emailCreate = :creater and o.isDeleted = false")
	List<Recording> getRecordByCreater(@Param("creater") String creater);

	@Query("Select o from Recording o where o.isDeleted = true and o.emailCreate = :creater")
	List<Recording> getRecordDelete(@Param("creater") String creater);

	@Query(value = "SELECT r.* FROM RECORDING r join SONGS s on r.SONGSID = s.SONGSID ORDER BY NEWID()", nativeQuery = true)
	List<Recording> findListRandom();

	@Query(value = "select * from recording where recordingid = :recordingId", nativeQuery = true)
	Recording findAllById(@Param("recordingId") Long recordingId);

	@Query(value = "select top 50 r.* from recording r "
			+ "join songs s on r.songsid = s.songsid "
			+ "join songgenre sg on r.recordingid = sg.idrecord join genre g on g.id = sg.idgenre "
			+ "where g.namegenre in (:nameGenre) or r.culture like :culture or r.instrument like :instrument "
			+ "or r.mood like :mood or r.songstyle like :songstyle " 
			+ "and s.realeaseday < GETDATE() and r.isdeleted = 0 "
			+ "group by r.recordingid, r.audiofileurl,r.culture,r.instrument,r.emailcreate, r.recordingname,r.recordingdate,r.songstyle,r.duration, "
			+ "r.idmv, r.isdeleted,r.listened,r.lyricsurl,r.mood, r.produce,r.publicidaudiofile,r.publicidlyrics,r.studio,r.versions,r.songsid "
			+ "ORDER BY NEWID();", nativeQuery = true)
	List<Recording> findListRecordRandom(@Param("nameGenre") String nameGenre, @Param("culture") String culture,
			@Param("instrument") String instrument, @Param("mood") String mood, @Param("songstyle") String songstyle);

	@Query(value="select top 50 r.* from recording r "
			+ "join songs s on r.songsid = s.songsid "
			+ "join songgenre sg on r.recordingid = sg.idrecord "
			+ "join genre g on g.id = sg.idgenre "
			+ "where g.namegenre in (:nameGenre) "
			+ "or r.culture like :culture or r.instrument like :instrument "
			+ "or r.mood like :mood or r.songstyle like :songstyle "
			+ "or r.versions like :versions "
			+ "and s.realeaseday < GETDATE() and r.isdeleted = 0 "
			+ "group by r.recordingid, r.audiofileurl,r.culture,r.instrument,r.emailcreate, r.recordingname,r.recordingdate,r.songstyle,r.duration, "
			+ "r.idmv, r.isdeleted,r.listened,r.lyricsurl,r.mood, r.produce,r.publicidaudiofile,r.publicidlyrics,r.studio,r.versions,r.songsid "
			+ "ORDER BY NEWID();",nativeQuery = true)
	
	List<Recording> findListRandomFavorite(@Param("nameGenre") String nameGenre, @Param("culture") String culture
										,@Param("instrument") String instrument,@Param("mood") String mood
										,@Param("songstyle") String songstyle,@Param("versions") String versions);
	
	@Query(value="select RECORDING.* from RECORDING "
			+ "join SONGS on RECORDING.SONGSID = SONGS.SONGSID "
			+ "join writter w on w.songsid = songs.songsid "
			+ "join artist a on a.artistid = w.artistid "
			+ "where a.artistid= :artistid and a.artistid != songs.artistcreate",nativeQuery = true)
	List<Recording> getMyProject(@Param("artistid") Long artistid);
	
	@Query(value="select r.* from recording r "
			+ "join songs s on s.songsid = r.songsid "
			+ "join writter w on w.songsid = r.songsid "
			+ "join artist a on a.artistid=w.artistid "
			+ "where a.artistid = :artistId and s.realeaseday < GETDATE() and r.isdeleted = 0 "
			+ "order by r.listened desc",nativeQuery = true)
	List<Recording> findListPopularByArtist(@Param("artistId") Long artistId);
	
	@Query(value="select RECORDING.* from RECORDING "
			+ "join SONGS on RECORDING.SONGSID = SONGS.SONGSID "
			+ "join writter w on w.songsid = songs.songsid "
			+ "join artist a on a.artistid = w.artistid "
			+ "where a.artistid= :artistid and a.artistid != songs.artistcreate and songs.realeaseday < GETDATE() and RECORDING.isdeleted = 0",nativeQuery = true)
	List<Recording> findListAppearOn(@Param("artistid") Long artistid);

}
