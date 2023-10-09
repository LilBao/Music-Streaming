package com.rhymthwave.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rhymthwave.entity.Song;

@Repository
public interface  SongDAO extends JpaRepository<Song, Integer>{
	@Query(value="select s.* from SONGS s where not exists ("
			+ "    select 1"
			+ "    from recording r"
			+ "    where r.songsid = s.songsid)",nativeQuery = true)
	List<Song> getSongNotRecord();
}
