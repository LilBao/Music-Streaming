package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Recording;

@Repository
public interface RecordDAO extends JpaRepository<Recording, Long>{
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
	
	@Query(value="SELECT r.* FROM RECORDING r join SONGS s on r.SONGSID = s.SONGSID ORDER BY NEWID()",nativeQuery = true)
	List<Recording> findListRandom();
}
