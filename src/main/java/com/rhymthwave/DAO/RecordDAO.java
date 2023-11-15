package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Recording;

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

	@Query(value = "select top 50 r.* from recording r " + "join songs s on r.songsid = s.songsid "
			+ "join songgenre sg on r.recordingid = sg.idrecord " + "join genre g on g.id = sg.idgenre "
			+ "where g.namegenre in (:nameGenre) " + "or r.culture like :culture or r.instrument like :instrument "
			+ "or r.mood like :mood or r.songstyle like :songstyle " 
			+ "and s.realeaseday < GETDATE() and r.isdeleted = 0" + "ORDER BY NEWID();", nativeQuery = true)
	List<Recording> findListRecordRandom(@Param("nameGenre") String nameGenre, @Param("culture") String culture,
			@Param("instrument") String instrument, @Param("mood") String mood, @Param("songstyle") String songstyle);
}
